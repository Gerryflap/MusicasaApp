package nl.gerben_meijer.gerryflap.musicasa.model;

/**
 * Created by Gerryflap on 2015-06-14.
 */
public class MusicasaAppContext {

    private static MusicasaAppContext instance;

    private ServerCommunicator communicator;




    private MusicasaAppContext(){
        communicator = new ServerCommunicator("http://acc.musi.casa:8080/api");
    }

    public static MusicasaAppContext getInstance(){
        if (instance == null){
            instance = new MusicasaAppContext();
        }
        return instance;
    }

    public ServerCommunicator getCommunicator() {
        return communicator;
    }
}
