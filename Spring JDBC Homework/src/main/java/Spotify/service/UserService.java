package Spotify.service;

import Spotify.repository.user.User;

import java.util.List;

public interface UserService {

    void addUser(User user);

    void deleteUser(String firstName);

    User getUser(long id);

    List<User> getAllUser();

    void update(User user);
}
