package bg.imdb.actors.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import static bg.imdb.constants.Constants.UUID_SIZE;

@Data
@Entity
@Table(name = "actors")
@AllArgsConstructor
@NoArgsConstructor
public class Actor {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", length = UUID_SIZE)
    private String id;

    @Column(nullable = false)
    private String name;

}
