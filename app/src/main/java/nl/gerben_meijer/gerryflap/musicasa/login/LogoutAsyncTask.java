package nl.gerben_meijer.gerryflap.musicasa.login;

import android.os.AsyncTask;

import nl.gerben_meijer.gerryflap.musicasa.model.MusicasaAppContext;
import nl.gerben_meijer.gerryflap.musicasa.model.ServerCommunicator;

/**
 * Created by Gerryflap on 2015-06-14.
 */
public class LogoutAsyncTask extends AsyncTask<Void, Void, Void> {
    @Override
    protected Void doInBackground(Void... params) {
        ServerCommunicator serverCommunicator = MusicasaAppContext.getInstance().getCommunicator();
        serverCommunicator.logOut();
        return null;
    }

}
