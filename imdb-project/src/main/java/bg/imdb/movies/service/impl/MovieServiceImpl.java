package bg.imdb.movies.service.impl;

import bg.imdb.exceptions.HttpBadRequestException;
import bg.imdb.movies.entities.Movie;
import bg.imdb.movies.entities.MovieRepository;
import bg.imdb.movies.model.MovieModel;
import bg.imdb.movies.service.MovieService;
import bg.imdb.movies.service.converters.MovieConverter;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final MovieConverter movieConverter;

    public MovieServiceImpl(MovieRepository movieRepository, MovieConverter movieConverter) {
        this.movieRepository = movieRepository;
        this.movieConverter = movieConverter;
    }

    @Transactional
    @Override
    public MovieModel createMovie(MovieModel model) {

        log.info("Create movie BEGIN: {}", model);

        final Movie entity = movieConverter.convertToEntity(model);

        final Movie movie = movieRepository.save(entity);

        final MovieModel created = movieConverter.convertToModel(movie);

        log.info("Create movie END: {}", created);

        return created;
    }

    @Override
    public void deleteMovie(String id) {
        log.info("Delete movie by id BEGIN: {}", id);

        movieRepository.deleteById(id);

        log.info("Delete movie by id END: {}", id);
    }

    @Transactional
    @Override
    public MovieModel updateMovie(MovieModel model) {
        log.info("Update movie BEGIN: {}", model);

        if (!movieRepository.existsById(model.getId())) {
            throw new HttpBadRequestException("Movie does not exist for id: " + model.getId());
        }

        final Movie movie = movieConverter.convertToEntity(model);

        final MovieModel updated = movieConverter.convertToModel(movieRepository.save(movie));

        log.info("Update movie END: {}", updated);

        return updated;
    }

    @Override
    public MovieModel getById(String id) {
        log.info("Get movie by id BEGIN: {}", id);

        final Optional<Movie> movieOpt = movieRepository.findById(id);

        MovieModel movie = null;
        if (movieOpt.isPresent()) {
            movie = movieConverter.convertToModel(movieOpt.get());
        }

        log.info("Get movie by id END: {} {}", id, movie);

        return movie;
    }

    @Override
    public List<MovieModel> getAllMovies() {
        log.info("Get all movies BEGIN: ");

        final List<Movie> all = movieRepository.findAll();

        final List<MovieModel> movies = movieConverter.convertToModels(all);

        log.info("Get all movies END: {}", movies);

        return movies;
    }

    @Transactional
    @Override
    public void saveImageFile(String movieId, MultipartFile file) {
        log.info("Save image BEGIN: ");

        try {
            Movie movie = movieRepository.findById(movieId).get();

            Byte[] byteObjects = new Byte[file.getBytes().length];

            int i = 0;

            for (byte b : file.getBytes()){
                byteObjects[i++] = b;
            }

            movie.setImage(byteObjects);

            movieRepository.save(movie);
        } catch (IOException e) {
            log.error("Error occurred while saving image! ", e);

            e.printStackTrace();
        }

        log.info("Save image End: ");
    }
}
