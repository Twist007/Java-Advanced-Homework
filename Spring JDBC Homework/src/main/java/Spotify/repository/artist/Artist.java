package Spotify.repository.artist;

import lombok.Data;
import java.util.Date;

@Data
public class Artist {

    private long id;
    private String firstName;
    private String lastName;
    private Date birthDay;
    private String genre;
}
