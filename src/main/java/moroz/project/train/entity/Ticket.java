package moroz.project.train.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Ticket {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String passengerFirstName;

    private String passengerLastName;

    @ManyToOne
    @JoinColumn(name = "route_id", nullable = false)
    private Route route;
}
