package bg.imdb.movies.service;

import bg.imdb.movies.model.MovieModel;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MovieService {

    MovieModel createMovie(MovieModel model);

    void deleteMovie(String id);

    MovieModel updateMovie(MovieModel model);

    MovieModel getById(String id);

    List<MovieModel> getAllMovies();

    void saveImageFile(String recipeId, MultipartFile file);
}
