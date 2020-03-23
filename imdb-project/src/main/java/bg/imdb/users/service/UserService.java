package bg.imdb.users.service;

import bg.imdb.users.model.UserModel;
import bg.imdb.users.rest.LoginResponse;

public interface UserService {

  UserModel registerUser(UserModel model);

  LoginResponse loginUser(String userName, String password);

}
