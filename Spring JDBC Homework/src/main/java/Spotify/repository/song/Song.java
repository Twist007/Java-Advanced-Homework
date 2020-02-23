package Spotify.repository.song;

import Spotify.repository.artist.Artist;
import lombok.Data;

import java.util.Date;

@Data
public class Song {

    private long id;
    private String name;
    private Date publicationDate;
    private int duration;
    private Artist artist;

    public void playing(){
        System.out.println("Playing the song!");
    }
}
