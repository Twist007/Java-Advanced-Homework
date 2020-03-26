package bg.imdb.movies.model;

import bg.imdb.actor.entities.Actor;
import bg.imdb.users.model.UserModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieModel {

    private String id;

    private String name;

    private int year;

    private double rating;

    private Set<Actor> actorList;

    private String genre;

    private Byte[] image;

    private String videoLink;

    private UserModel user;
}
