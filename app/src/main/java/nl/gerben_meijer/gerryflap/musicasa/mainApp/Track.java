package nl.gerben_meijer.gerryflap.musicasa.mainApp;

/**
 * Created by Gerryflap on 2015-06-12.
 */
public class Track {

    private String name;
    private String artist;

    public Track(String name, String artist) {
        this.name = name;
        this.artist = artist;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
}
