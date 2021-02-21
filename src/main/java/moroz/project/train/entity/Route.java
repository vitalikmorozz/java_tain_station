package moroz.project.train.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Route {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "train_id", nullable = false)
    private Train train;

    @OneToMany(mappedBy = "route")
    private List<Stoppage> stoppages;

    @OneToMany(mappedBy = "route")
    private List<Ticket> tickets;
}
