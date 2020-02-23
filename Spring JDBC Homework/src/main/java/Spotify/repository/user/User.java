package Spotify.repository.user;

import Spotify.repository.song.Song;
import lombok.Data;
import java.util.Date;

@Data
public class User {

    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private Date birthDay;
    private boolean isPaid;

    public void playSong(Song song){
        song.playing();
    }
}
