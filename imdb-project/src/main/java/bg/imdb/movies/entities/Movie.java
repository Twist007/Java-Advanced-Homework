package bg.imdb.movies.entities;

import bg.imdb.actors.entities.Actor;
import bg.imdb.users.entities.User;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

import static bg.imdb.constants.Constants.UUID_SIZE;

@Data
@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", length = UUID_SIZE)
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int year;

    @Column(nullable = false)
    private double rating;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "movie_actors",
            joinColumns = {@JoinColumn(name = "movie_id", nullable = false,
                    foreignKey = @ForeignKey(name = "fk_movie_id"))},
            inverseJoinColumns = {@JoinColumn(name = "actor_id", nullable = false,
                    foreignKey = @ForeignKey(name = "fk_actor_id"))})
    private Set<Actor> actorList;

    @Column(nullable = false)
    private String genre;

    @Lob
    @Column(nullable = false)
    private Byte[] image;

    @Column(nullable = false)
    private String videoLink;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_movies_users"))
    private User user;

}
