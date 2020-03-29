package bg.imdb.movie.rest;

import bg.imdb.exceptions.HttpForbiddenException;
import bg.imdb.movies.model.MovieModel;
import bg.imdb.movies.rest.MovieAccessValidator;
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

import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class TestMovieAccessValidator {

  @Autowired
  private UserService userService;

  @Autowired
  private MovieAccessValidator movieAccessValidator;

  @Autowired
  private MovieService movieService;

  @Test
  public void testMovieAccessValidator() {
    final MovieModel movie = buildMovie();

    final MovieModel created = movieService.createMovie(movie);

    movieAccessValidator.validateUserCanEditMovie(created.getUser().getId(), created.getId());

    assertThrows(
        HttpForbiddenException.class,
        () -> movieAccessValidator.validateUserCanEditMovie("bad id", created.getId()));
  }

  private MovieModel buildMovie() {
    final UserModel user = new UserModel(null, "testUser2", "1234", "Test", "User");
    final UserModel createdUser = userService.registerUser(user);

    final MovieModel movie = new MovieModel();
    movie.setName("Star Wars - The Force Awakens 2");
    movie.setYear(2015);
    movie.setRating(7.9);
    movie.setActorList("Harrison Ford, Mark Hamill, Carrie Fisher, Adam Driver");
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
