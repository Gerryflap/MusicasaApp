package nl.gerben_meijer.gerryflap.musicasa;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.widget.MediaController;

import org.json.JSONObject;

import java.io.IOException;

import nl.gerben_meijer.gerryflap.musicasa.model.MusicasaAppContext;

/**
 * Created by Gerryflap on 2015-07-01.
 */
public class MusicPlayer {
    public final MediaPlayer mediaPlayer = new MediaPlayer();
    public static MusicPlayer instance;

    public MusicPlayer(){
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
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
            JSONObject songinfo = MusicasaAppContext.getInstance().getCommunicator().getPage("/song/"+params[0]);
            System.out.println(songinfo);
            String url = MusicasaAppContext.getInstance().getCommunicator().getUrl()+"/song/download/"+params[0]; // your URL here
            System.out.println("Loading music");
            try {
                mediaPlayer.setDataSource(url);
                mediaPlayer.prepare(); // might take long! (for buffering, etc)
                System.out.println("Loading done");

            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
            mediaPlayer.start();
            return true;
        }
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }
}
