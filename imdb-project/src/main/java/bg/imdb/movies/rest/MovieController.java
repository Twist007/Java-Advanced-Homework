package bg.imdb.movies.rest;

import bg.imdb.movies.model.MovieModel;
import bg.imdb.movies.service.MovieService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;
    private final MovieAccessValidator movieAccessValidator;

    public MovieController(final MovieService movieService,
                         final MovieAccessValidator movieAccessValidator) {
        this.movieService = movieService;
        this.movieAccessValidator = movieAccessValidator;
    }

    @PostMapping
    public MovieModel createMovie(@RequestBody final MovieModel movie) {
        return movieService.createMovie(movie);
    }

    @GetMapping("/all")
    public List<MovieModel> getAllMovies() {
        return movieService.getAllMovies();
    }

    @PutMapping
    public MovieModel updateMovie(@RequestBody final MovieModel movie) {
        movieAccessValidator.validateUserCanEditMovie(movie.getUser().getId(), movie.getId());

        return movieService.updateMovie(movie);
    }

    @DeleteMapping("/{id}/{userId}")
    public void deleteMovie(@PathVariable final String id, @PathVariable final String userId) {
        movieAccessValidator.validateUserCanEditMovie(userId, id);

        movieService.deleteMovie(id);
    }

    @PostMapping("recipe/{id}/image")
    public String handleImagePost(@PathVariable String id, @RequestParam("imagefile") MultipartFile file){

        movieService.saveImageFile(Long.valueOf(id), file);

        return "redirect:/recipe/" + id + "/show";
    }
}
