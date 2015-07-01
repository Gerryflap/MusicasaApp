package nl.gerben_meijer.gerryflap.musicasa.model;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

/**
 * Created by Gerryflap on 2015-06-14.
 */
public class ServerCommunicator {

    private String url;
    private int id;
    private boolean loggedIn = false;
    private HashMap<String, String> cookies;


    public ServerCommunicator(String url) {
        this.url = url;
        CookieManager cookieManager = new CookieManager();
        CookieHandler.setDefault(cookieManager);
        cookies = new HashMap<>();


    }


    public void setCookies(HttpURLConnection conn) {
        String headerName;
        for (int i = 1; (headerName = conn.getHeaderFieldKey(i)) != null; i++) {
            if (headerName.equals("Set-Cookie")) {
                String cookie = conn.getHeaderField(i);
                cookie = cookie.substring(0, cookie.indexOf(";"));
                String cookieName = cookie.substring(0, cookie.indexOf("="));
                String cookieValue = cookie.substring(cookie.indexOf("=") + 1, cookie.length());
                cookies.put(cookieName, cookieValue);
            }
        }
    }

    public void addCookies(HttpURLConnection conn) {
        String cookieString = "";
        for(String cookieName: cookies.keySet()){
            cookieString += String.format("%s:%s; ", cookieName, cookies.get(cookieName));

        }

        conn.setRequestProperty("Cookie", cookieString);
    }


    public JSONObject getPage(String path) {
        return get(path, "GET");
    }

    public JSONObject get(String path, String method) {
        try {


            URL url = new URL(this.url + path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod(method);
            addCookies(conn);
            conn.connect();
            System.out.println(conn.getResponseCode());
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            conn.getInputStream()
                    ));

            String out = "";
            String cursor = "";
            while ((cursor = reader.readLine()) != null) {
                out += cursor;
            }
            setCookies(conn);
            reader.close();
            conn.disconnect();

            JSONObject jsonOut = new JSONObject(out);
            return jsonOut;


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public JSONObject postToPage(String path, String message) {
        try {


            URL url = new URL(this.url + path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");
            addCookies(conn);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length", Integer.toString(message.getBytes().length));
            conn.getOutputStream().write(message.getBytes());

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            conn.getInputStream()
                    ));

            String out = "";
            String cursor;
            while ((cursor = reader.readLine()) != null) {
                out += cursor;
            }
            setCookies(conn);
            reader.close();
            System.out.println(conn.getResponseCode());
            conn.disconnect();

            JSONObject jsonOut = new JSONObject(out);
            return jsonOut;


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean logIn(String email, String password) {
        String message = String.format("email=%s&password=%s", email, password);
        JSONObject data = postToPage("/session", message);
        if (data != null) {
            try {
                id = (int) data.get("id");
                loggedIn = true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        System.out.println(data);
        return data != null;
    }

    public void logOut(){
        get("/session", "DELETE");
    }

    public String getUrl() {
        return url;
    }

    public int getId() {
        return id;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public HashMap<String, String> getCookies() {
        return cookies;
    }
}

   