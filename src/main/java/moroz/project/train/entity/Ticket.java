package moroz.project.train.entity;

import lombok.Data;
import moroz.project.train.interfaces.IBaseEntity;

import javax.persistence.*;

@Data
@Entity
public class Ticket implements IBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
    private User user;

    private String passengerFirstName;

    private String passengerLastName;

    @ManyToOne
    @JoinColumn(name = "route_id", nullable = false)
    private Route route;
}
