package bg.imdb.users.service.impl;

import bg.imdb.exceptions.HttpUnauthorizedException;
import bg.imdb.users.entities.User;
import bg.imdb.users.entities.UserRepository;
import bg.imdb.users.model.UserModel;
import bg.imdb.users.rest.LoginResponse;
import bg.imdb.users.service.UserService;
import bg.imdb.users.service.converters.UserConverter;
import com.auth0.jwt.JWT;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

import static bg.imdb.config.security.PasswordEncoder.checkPassword;
import static bg.imdb.config.security.PasswordEncoder.hashPassword;
import static bg.imdb.config.security.SecurityConstants.EXPIRATION_TIME;
import static bg.imdb.config.security.SecurityConstants.SECRET;
import static com.auth0.jwt.algorithms.Algorithm.HMAC512;


@Service
@Log4j2
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final UserConverter userConverter;

  public UserServiceImpl(final UserRepository userRepository,
      final UserConverter userConverter) {
    this.userRepository = userRepository;
    this.userConverter = userConverter;
  }

  @Override
  public UserModel registerUser(final UserModel model) {
    log.info("Register user BEGIN: {}", model);

    model.setPassword(hashPassword(model.getPassword()));

    final User user = userConverter.convertToEntity(model);

    final User saved = userRepository.save(user);

    log.info("Register user END: {}", saved);

    return userConverter.convertToModel(saved);
  }

  @Override
  public LoginResponse loginUser(final String username, final String password) {
    log.info("Login user BEGIN: {}", username);

    final User user = getUser(username);

    if (!checkPassword(password, user.getPassword())) {
      throw new HttpUnauthorizedException();
    }

    final String jwtToken = JWT.create()
        .withSubject(username)
        .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
        .sign(HMAC512(SECRET.getBytes()));

    final UserModel userModel = userConverter.convertToModel(user);

    final LoginResponse response = new LoginResponse(userModel, jwtToken);

    log.info("Login user END: {}", response);

    return response;
  }

  private User getUser(final String username) {
    final Optional<User> userOpt = userRepository
        .findByUsername(username);

    return userOpt.orElse(null);
  }

}
