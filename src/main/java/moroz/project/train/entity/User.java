package moroz.project.train.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import moroz.project.train.interfaces.IBaseEntity;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name = "users")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements IBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    @OneToMany(mappedBy = "user")
    private List<Ticket> tickets;
}
