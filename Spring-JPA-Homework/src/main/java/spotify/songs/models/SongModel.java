package spotify.songs.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SongModel {

    private String id;
    private String name;
    private LocalDate releaseDate;
    private int lengthInSeconds;
    private String artistId;
}
