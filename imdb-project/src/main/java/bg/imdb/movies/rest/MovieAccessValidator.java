package bg.imdb.movies.rest;

import bg.imdb.exceptions.HttpForbiddenException;
import bg.imdb.movies.model.MovieModel;
import bg.imdb.movies.service.MovieService;
import org.springframework.stereotype.Component;

@Component
public class MovieAccessValidator {
    private final MovieService movieService;

    public MovieAccessValidator(final MovieService movieService) {
        this.movieService = movieService;
    }

    void validateUserCanEditMovie(final String userId, final String movieId) {
        if (userId == null || movieId == null) {
            throw new HttpForbiddenException();
        }

        final MovieModel movie = movieService.getById(movieId);

        if (!userId.equals(movie.getUser().getId())) {
            final String message = String
                    .format("Movie with id: %s does not belong to user with id: %s", movieId, userId);
            throw new HttpForbiddenException(message);
        }
    }
}
