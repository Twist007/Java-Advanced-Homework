package spotify.users.service;

import spotify.users.models.UserModel;

public interface UserService {

    UserModel createUser(UserModel model);

    void deleteUser(String id);

    UserModel getUser(String id);

    UserModel updateUser(UserModel model);
}
