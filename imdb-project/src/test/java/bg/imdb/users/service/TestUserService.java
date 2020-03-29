package bg.imdb.users.service;


import bg.imdb.exceptions.HttpUnauthorizedException;
import bg.imdb.users.model.UserModel;
import bg.imdb.users.rest.LoginResponse;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Log4j2
public class TestUserService {

    @Autowired
    private UserService userService;

    @Test
    public void testRegisterUser() {

        final UserModel model = new UserModel(null, "test", "password", "Test", "User");

        final UserModel created = userService.registerUser(model);

        assertEquals(model.getUsername(), created.getUsername());
        assertEquals(model.getFirstName(), created.getFirstName());
        assertEquals(model.getLastName(), created.getLastName());
    }

    @Test
    public void testLoginUser() {
        final UserModel model = new UserModel(null, "test2", "password", "Test2", "User");

        final UserModel created = userService.registerUser(model);

        assertThrows(HttpUnauthorizedException.class,
                () -> userService.loginUser(created.getUsername(), "root"));

        final LoginResponse userLogin = userService.loginUser(created.getUsername(), "password");
        assertNotNull(userLogin.getUser());
        assertNotNull(userLogin.getJwtToken());
    }
}
