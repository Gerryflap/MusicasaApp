package nl.gerben_meijer.gerryflap.musicasa;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;

import java.io.IOException;

import nl.gerben_meijer.gerryflap.musicasa.model.MusicasaAppContext;

/**
 * Created by Gerryflap on 2015-07-01.
 */
public class MusicPlayer {
    MediaPlayer mediaPlayer;
    public static MusicPlayer instance;

    public MusicPlayer(){

    }

    public static MusicPlayer getInstance(){
        if(instance == null){
            instance = new MusicPlayer();
        }
        return instance;
    }

    public void loadAndPlayTrack(int index){
        if(MusicasaAppContext.getInstance().getCommunicator().isLoggedIn()) {
            MusicLoader musicLoader = new MusicLoader();
            musicLoader.execute(index);
        }
    }

    public class MusicLoader extends AsyncTask<Integer, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Integer... params) {
            String url = "http://acc.musi.casa:8080/api/song/download/"+params[0]; // your URL here
            MediaPlayer mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            try {
                mediaPlayer.setDataSource(url);
                mediaPlayer.prepare(); // might take long! (for buffering, etc)
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
            mediaPlayer.start();
            return true;
        }
    }
}
