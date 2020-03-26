package bg.imdb.movies.entities;

import bg.imdb.users.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, String> {

    List<Movie> findAllByUser(User user);
}
