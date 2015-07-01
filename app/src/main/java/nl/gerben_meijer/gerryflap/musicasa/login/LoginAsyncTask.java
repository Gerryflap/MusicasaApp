package nl.gerben_meijer.gerryflap.musicasa.login;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.TextView;

import nl.gerben_meijer.gerryflap.musicasa.R;
import nl.gerben_meijer.gerryflap.musicasa.model.MusicasaAppContext;
import nl.gerben_meijer.gerryflap.musicasa.model.ServerCommunicator;

/**
 * Created by Gerryflap on 2015-06-14.
 */
public class LoginAsyncTask extends AsyncTask<String, Void, Boolean> {

    private LoginActivity loginActivity;
    String[] params;

    public LoginAsyncTask(LoginActivity loginActivity){
        this.loginActivity = loginActivity;
    }
    @Override
    protected Boolean doInBackground(String... params) {
        this.params = params;
        ServerCommunicator serverCommunicator = MusicasaAppContext.getInstance().getCommunicator();
        return serverCommunicator.logIn(params[0], params[1]);
    }

    protected void onPostExecute(Boolean result){
        if(result) {
            ((TextView) loginActivity.findViewById(R.id.statusView)).setText("Status: Logged in");
            SharedPreferences sharedPreferences = loginActivity.getSharedPreferences("nl.gerben_meijer.gerryflap.musicasa", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("email", params[0]);
            editor.putString("password", params[1]);
            editor.apply();

        } else {
            ((TextView) loginActivity.findViewById(R.id.statusView)).setText("Status: Login failed");
        }

    }
}
