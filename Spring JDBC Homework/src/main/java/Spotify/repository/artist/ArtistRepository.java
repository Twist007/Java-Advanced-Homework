package Spotify.repository.artist;

import Spotify.repository.artist.mapper.ArtistRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArtistRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public ArtistRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void add(final Artist artist) {
        final String sql = "INSERT INTO artists (first_name, last_name, birth_day, genre) "
                + "VALUES (:first_name, :last_name, :birth_day, :genre)";

        final Map<String, Object> params = new HashMap<>();
        params.put("first_name", artist.getFirstName());
        params.put("last_name", artist.getLastName());
        params.put("birth_day", artist.getBirthDay());
        params.put("genre", artist.getGenre());

        jdbcTemplate.update(sql, params);
    }

    public void delete(final long id) {
        final String sql = "DELETE FROM artists WHERE id = " + id;

        jdbcTemplate.update(sql, new HashMap<>());
    }

    public Artist get(final long id) {
        final String sql = "SELECT * FROM artists WHERE id = " + id;

        return jdbcTemplate.queryForObject(sql, new HashMap<>(), new ArtistRowMapper());
    }

    public List<Artist> getAll() {
        final String sql = "SELECT * FROM artists";

        return jdbcTemplate.query(sql, new ArtistRowMapper());
    }

    public void update(final Artist artist) {
        final String sql = "UPDATE artists "
                + "SET first_name = :first_name,"
                + "last_name = :last_name,"
                + "birth_day = :birth_day,"
                + "genre = :genre"
                + "WHERE id = :id";

        final Map<String, Object> params = new HashMap<>();
        params.put("id", artist.getId());
        params.put("first_name", artist.getFirstName());
        params.put("last_name", artist.getLastName());
        params.put("birth_day", artist.getBirthDay());
        params.put("genre", artist.getGenre());

        jdbcTemplate.update(sql, params);
    }
}
