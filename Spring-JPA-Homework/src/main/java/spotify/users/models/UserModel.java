package spotify.users.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthDate;
    private boolean isPaid;
}
