package bg.imdb.movies.service.converters;

import bg.imdb.actors.entities.Actor;
import bg.imdb.actors.service.ActorService;
import bg.imdb.movies.entities.Movie;
import bg.imdb.movies.model.MovieModel;
import bg.imdb.users.service.converters.UserConverter;
import com.mysql.cj.util.StringUtils;
import org.springframework.stereotype.Component;

import java.util.*;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

@Component
public class MovieConverter {

    private final UserConverter userConverter;
    private final ActorService actorService;

    public MovieConverter(final UserConverter userConverter,
                        final ActorService actorService) {
        this.userConverter = userConverter;
        this.actorService = actorService;
    }

    public MovieModel convertToModel(final Movie movie) {
        if (movie == null) {
            return null;
        }

        final MovieModel model = new MovieModel();
        model.setId(movie.getId());
        model.setName(movie.getName());
        model.setYear(movie.getYear());
        model.setRating(movie.getRating());
        model.setActorList(toActors(movie.getActorList()));
        model.setGenre(movie.getGenre());
        model.setImage(movie.getImage());
        model.setVideoLink(movie.getVideoLink());
        model.setUser(userConverter.convertToModel(movie.getUser()));

        return model;
    }
    public List<MovieModel> convertToModels(final List<Movie> movies) {
        if (movies == null || movies.isEmpty()) {
            return new ArrayList<>();
        }

        return movies.stream().map(this::convertToModel).collect(toList());
    }

    public Movie convertToEntity(final MovieModel model) {
        if (model == null) {
            return null;
        }

        final Movie movie = new Movie();
        movie.setId(model.getId());
        movie.setName(model.getName());
        movie.setYear(model.getYear());
        movie.setRating(model.getRating());
        movie.setActorList(createActorsIfMissing(model.getActorList()));
        movie.setGenre(model.getGenre());
        movie.setImage(model.getImage());
        movie.setUser(userConverter.convertToEntity(model.getUser()));
        movie.setVideoLink(model.getVideoLink());

        return movie;
    }

    private String toActors(final Set<Actor> actors) {
        if (actors == null || actors.isEmpty()) {
            return null;
        }

        return String.join(",", actors.stream().map(Actor::getName).collect(toSet()));
    }

    private Set<Actor> createActorsIfMissing(final String actor) {
        if (StringUtils.isNullOrEmpty(actor)) {
            return null;
        }

        final Set<String> actorsSet = new HashSet<>(Arrays.asList(actor.split(",")));
        final Set<Actor> entities = new HashSet<>();

        actorsSet.forEach(extra -> {
            final Actor byName = actorService.getByName(extra);
            if (byName != null) {
                entities.add(byName);
            } else {
                entities.add(actorService.createActor(extra));
            }
        });

        return entities;
    }
}
