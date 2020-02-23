package Spotify.repository.song;


import Spotify.repository.song.mapper.SongRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SongRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public SongRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void add(final Song song) {
        final String sql = "INSERT INTO song (name, publication_date, duration, artist_id) "
                + "VALUES (:name, :publication_date, :duration, :artist_id)";

        final Map<String, Object> params = new HashMap<>();
        params.put("name", song.getName());
        params.put("publication_date", song.getPublicationDate());
        params.put("duration", song.getDuration());
        params.put("artist_id", song.getArtist().getId());

        jdbcTemplate.update(sql, params);
    }

    public void delete(final long id) {
        final String sql = "DELETE FROM songs WHERE id = " + id;

        jdbcTemplate.update(sql, new HashMap<>());
    }

    public Song get(final long id) {
        final String sql = "SELECT * FROM songs WHERE id = " + id;

        return jdbcTemplate.queryForObject(sql, new HashMap<>(), new SongRowMapper());
    }

    public List<Song> getAll() {
        final String sql = "SELECT * FROM songs";

        return jdbcTemplate.query(sql, new SongRowMapper());
    }

    public void update(final Song song) {
        final String sql = "UPDATE songs "
                + "SET name = :name,"
                + "publication_date = :publication_date,"
                + "duration = :duration,"
                + "birth_day = :birth_day,"
                + "paid = :paid"
                + "WHERE id = :id";

        final Map<String, Object> params = new HashMap<>();
        params.put("id", song.getId());
        params.put("name", song.getName());
        params.put("publication_date", song.getPublicationDate());
        params.put("duration", song.getDuration());
        params.put("artist_id", song.getArtist().getId());

        jdbcTemplate.update(sql, params);
    }
}
