package Spotify.repository.user.mapper;

import Spotify.repository.user.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {

        final User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setFirstName(resultSet.getString("first_name"));
        user.setLastName(resultSet.getString("last_name"));
        user.setEmail(resultSet.getString("email"));
        user.setBirthDay(resultSet.getDate("birth_day"));
        user.setPaid(resultSet.getBoolean("Paid"));
        return user;
    }

}
