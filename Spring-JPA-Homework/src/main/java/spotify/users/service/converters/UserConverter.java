package spotify.users.service.converters;

import org.springframework.stereotype.Component;
import spotify.users.entities.User;
import spotify.users.models.UserModel;

@Component
public class UserConverter {

    public User convertToEntity(final UserModel model) {
        if (model == null) {
            return null;
        }

        final User user = new User();

        user.setId(model.getId());
        user.setFirstName(model.getFirstName());
        user.setLastName(model.getLastName());
        user.setEmail(model.getEmail());
        user.setBirthDate(model.getBirthDate());
        user.setPaid(model.isPaid());

        return user;
    }

    public UserModel convertToModel(final User user) {
        if (user == null) {
            return null;
        }

        final UserModel model = new UserModel();

        model.setId(user.getId());
        model.setFirstName(user.getFirstName());
        model.setLastName(user.getLastName());
        model.setEmail(user.getEmail());
        model.setBirthDate(user.getBirthDate());
        model.setPaid(user.isPaid());

        return model;
    }
}
