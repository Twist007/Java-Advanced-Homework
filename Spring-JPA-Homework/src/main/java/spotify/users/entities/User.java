package spotify.users.entities;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import java.time.LocalDate;

import static spotify.constants.Constants.UUID_SIZE;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", length = UUID_SIZE)
    private String id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "paid", nullable = false)
    private boolean isPaid;
}
