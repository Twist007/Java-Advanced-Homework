package Spotify.repository.artist.mapper;

import Spotify.repository.artist.Artist;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ArtistRowMapper  implements RowMapper<Artist> {
    @Override
    public Artist mapRow(ResultSet resultSet, int i) throws SQLException {
        
            final Artist artist = new Artist();
            artist.setId(resultSet.getLong("id"));
            artist.setFirstName(resultSet.getString("first_name"));
            artist.setLastName(resultSet.getString("last_name"));
            artist.setBirthDay(resultSet.getDate("birth_day"));
            artist.setGenre(resultSet.getString("genre"));
            return artist;
        }
}
