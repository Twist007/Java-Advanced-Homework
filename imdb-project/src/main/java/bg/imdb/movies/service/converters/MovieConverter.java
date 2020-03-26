package bg.imdb.movies.service.converters;

import bg.imdb.actor.entities.Actor;
import bg.imdb.movies.entities.Movie;
import bg.imdb.movies.model.MovieModel;
import com.mysql.cj.util.StringUtils;

import java.util.*;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

public class MovieConverter {

    public MovieModel convertToModel(final Movie movie) {
        if (movie == null) {
            return null;
        }

        final MovieModel model = new MovieModel();
        model.setId(movie.getId());
        model.setName(movie.getName());
        model.setYear(movie.getYear());
        model.setRating(movie.getRating());
        model.setActorList(movie.getActorList());
        model.setGenre(movie.getGenre());
        model.setImage(movie.getImage());
        model.setVideoLink(movie.getVideoLink());

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
        movie.setActorList(model.getActorList());
        movie.setGenre(model.getGenre());
        movie.setImage(model.getImage());
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
                entities.add(actorService.create(extra));
            }
        });

        return entities;
    }
}
