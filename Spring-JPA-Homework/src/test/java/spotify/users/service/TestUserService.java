package spotify.users.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import spotify.users.models.UserModel;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class TestUserService {

    @Autowired
    private UserService userService;

    @Test
    public void testCRUDUser() {
        final UserModel model =
                new UserModel("", "Stefan", "Dimitrov", "sdimitrov@prg.com", LocalDate.now(), false);

        final UserModel created = userService.createUser(model);

        assertEquals(model.getFirstName(), created.getFirstName());
        assertEquals(model.getLastName(), created.getLastName());
        assertEquals(model.getEmail(), created.getEmail());
        assertEquals(model.getBirthDate(), created.getBirthDate());
        assertEquals(model.isPaid(), created.isPaid());

        created.setLastName("Ivanov");
        final UserModel updated = userService.updateUser(created);

        assertEquals(created.getLastName(), updated.getLastName());

        userService.deleteUser(updated.getId());

        assertNull(userService.getUser(updated.getId()));
    }

}
