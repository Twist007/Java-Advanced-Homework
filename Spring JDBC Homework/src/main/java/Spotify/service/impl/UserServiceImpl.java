package Spotify.service.impl;

import Spotify.repository.user.User;
import Spotify.repository.user.UserRepository;
import Spotify.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void addUser(User user) {
        userRepository.add(user);
    }

    @Override
    public void deleteUser(String firstName) {
        userRepository.delete(firstName);
    }

    @Override
    public User getUser(long id) {
        return userRepository.get(id);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.getAll();
    }

    @Override
    public void updateUser(User user) {
        userRepository.update(user);
    }
}
