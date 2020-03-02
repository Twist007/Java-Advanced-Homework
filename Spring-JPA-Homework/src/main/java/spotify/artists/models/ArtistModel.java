package spotify.artists.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArtistModel {

    private String id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String genre;
}
