package Spotify.repository.user;

import Spotify.repository.song.Song;
import Spotify.repository.user.mapper.UserRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public UserRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void add(final User user) {
        final String sql = "INSERT INTO users (first_name, last_name, email, birth_day, paid) "
                + "VALUES (:first_name, :last_name, :email, :birth_day, :paid)";

        final Map<String, Object> params = new HashMap<>();
        params.put("first_name", user.getFirstName());
        params.put("last_name", user.getLastName());
        params.put("email", user.getEmail());
        params.put("birth_day", user.getBirthDay());
        params.put("paid", user.isPaid());

        jdbcTemplate.update(sql, params);
    }

    public void delete(final String firstName) {
        final String sql = "DELETE FROM users WHERE first_name = " + firstName;

        jdbcTemplate.update(sql, new HashMap<>());
    }

    public User get(final long id) {
        final String sql = "SELECT * FROM users WHERE id = " + id;

        return jdbcTemplate.queryForObject(sql, new HashMap<>(), new UserRowMapper());
    }

    public List<User> getAll() {
        final String sql = "SELECT * FROM users";

        return jdbcTemplate.query(sql, new UserRowMapper());
    }

    public void update(final User user) {
        final String sql = "UPDATE users "
                + "SET first_name = :first_name,"
                + "last_name = :last_name,"
                + "email = :email,"
                + "birth_day = :birth_day,"
                + "paid = :paid"
                + "WHERE id = :id";

        final Map<String, Object> params = new HashMap<>();
        params.put("id", user.getId());
        params.put("first_name", user.getFirstName());
        params.put("last_name", user.getLastName());
        params.put("email", user.getEmail());
        params.put("birth_day", user.getBirthDay());
        params.put("paid", user.isPaid());

        jdbcTemplate.update(sql, params);
    }
}
