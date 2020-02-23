package Spotify.repository.user;

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
}
