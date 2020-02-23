package Spotify.repository.song.mapper;

import Spotify.repository.artist.Artist;
import Spotify.repository.song.Song;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SongRowMapper implements RowMapper<Song> {

    @Override
    public Song mapRow(ResultSet resultSet, int i) throws SQLException {

        final Song song = new Song();
        song.setId(resultSet.getLong("id"));
        song.setName(resultSet.getString("name"));
        song.setPublicationDate(resultSet.getDate("publication_date"));
        song.setDuration(resultSet.getInt("duration"));
        song.setArtist((Artist) resultSet.getObject("artist"));
        return song;
    }
}
