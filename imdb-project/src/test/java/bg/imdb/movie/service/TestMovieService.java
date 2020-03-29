package bg.imdb.movie.service;


import bg.imdb.movies.model.MovieModel;
import bg.imdb.movies.service.MovieService;
import bg.imdb.users.model.UserModel;
import bg.imdb.users.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class TestMovieService {

    @Autowired
    private MovieService movieService;

    @Autowired
    private UserService userService;

    @Test
    public void testCRUDMovie() {
        final MovieModel movie = buildMovie();

        final MovieModel created = movieService.createMovie(movie);

        assertEquals(movie.getName(), created.getName());
        assertEquals(movie.getYear(), created.getYear());
        assertEquals(movie.getRating(), created.getRating(),1);
        assertEquals(movie.getActorList().length(), created.getActorList().length());
        assertEquals(movie.getGenre(), created.getGenre());
        assertEquals(movie.getImage(), created.getImage());
        assertEquals(movie.getVideoLink(), created.getVideoLink());
        assertEquals(movie.getUser().getId(), created.getUser().getId());

        final MovieModel byId = movieService.getById(created.getId());
        assertNotNull(byId);

        byId.setYear(2016);
        final MovieModel updated = movieService.updateMovie(byId);
        assertEquals(byId.getYear(), updated.getYear());

        final List<MovieModel> allMovies = movieService.getAllMovies();
        assertTrue(allMovies.size() > 0);

        movieService.deleteMovie(updated.getId());

        final MovieModel deleted = movieService.getById(updated.getId());
        assertNull(deleted);
    }

    private MovieModel buildMovie() {
        final UserModel user = new UserModel(null, "testUser", "1234", "Test", "User");
        final UserModel createdUser = userService.registerUser(user);

        final MovieModel movie = new MovieModel();
        movie.setName("Star Wars - The Force Awakens");
        movie.setYear(2015);
        movie.setRating(7.9);
        movie.setActorList("Harrison Ford,Mark Hamill,Carrie Fisher,Adam Driver");
        movie.setGenre("Action, Adventure, Sci-Fi");
        movie.setImage(setImageFile("static/images/star_wars.jpg"));
        movie.setVideoLink("https://www.youtube.com/embed/sGbxmsDFVnE");
        movie.setUser(createdUser);

        return movie;
    }

  private Byte[] setImageFile(String fileName) {
    MultipartFile multipartFile = new MockMultipartFile("imagefile", fileName, "text/plain",
            "Spring".getBytes());

      Byte[] byteObjects = new Byte[0];
      try {
          byteObjects = new Byte[multipartFile.getBytes().length];

          int i = 0;

          for (byte b : multipartFile.getBytes()){
              byteObjects[i++] = b;
          }

      } catch (IOException e) {

          e.printStackTrace();
      }
      return byteObjects;
  }

}
