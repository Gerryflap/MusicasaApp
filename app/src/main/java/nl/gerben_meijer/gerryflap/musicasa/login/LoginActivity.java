package nl.gerben_meijer.gerryflap.musicasa.login;

import java.util.Locale;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import nl.gerben_meijer.gerryflap.musicasa.R;
import nl.gerben_meijer.gerryflap.musicasa.model.MusicasaAppContext;
import nl.gerben_meijer.gerryflap.musicasa.model.ServerCommunicator;

public class LoginActivity extends ActionBarActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.findViewById(R.id.loginButton).setOnClickListener(this);
        this.findViewById(R.id.logoutButton).setOnClickListener(this);
        SharedPreferences sharedPreferences = getSharedPreferences("nl.gerben_meijer.gerryflap.musicasa", Context.MODE_PRIVATE);
        String domain = sharedPreferences.getString("domain", "http://acc.musi.casa:8080/api");
        String email = sharedPreferences.getString("email", "");
        String password = sharedPreferences.getString("password", "");
        ((TextView) this.findViewById(R.id.statusView)).setText(
                MusicasaAppContext.getInstance().getCommunicator().isLoggedIn()?"Status: Logged in":"Status: Not logged in"
        );
        ((EditText) this.findViewById(R.id.editServerAddr)).setText(domain);
        ((EditText) this.findViewById(R.id.editEmail)).setText(email);
        ((EditText) this.findViewById(R.id.editPassword)).setText(password);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.loginButton) {
            LoginAsyncTask task = new LoginAsyncTask(this);
            String domain = ((EditText) this.findViewById(R.id.editServerAddr)).getText().toString();
            String email = ((EditText) this.findViewById(R.id.editEmail)).getText().toString();
            String password = ((EditText) this.findViewById(R.id.editPassword)).getText().toString();
            task.execute(domain, email, password);
        }
        if(v.getId() == R.id.logoutButton){
            LogoutAsyncTask task = new LogoutAsyncTask();
            task.execute();
        }
    }
}
