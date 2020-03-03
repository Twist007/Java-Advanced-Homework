package spotify.songs.entities;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import spotify.artists.entities.Artist;

import javax.persistence.*;

import java.time.LocalDate;

import static spotify.constants.Constants.UUID_SIZE;

@Data
@Entity
@Table(name = "songs")
public class Song {

    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", length = UUID_SIZE)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "release_date", nullable = false)
    private LocalDate releaseDate;

    @Column(name = "length", nullable = false)
    private int length;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "artist_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_songs_artists"))
    private Artist artist;
}
