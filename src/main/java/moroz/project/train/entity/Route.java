package moroz.project.train.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import moroz.project.train.interfaces.IBaseEntity;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Route implements IBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "train_id", nullable = false)
    private Train train;

    @OneToMany(mappedBy = "route")
    private List<Stoppage> stoppages;

    @OneToMany(mappedBy = "route")
    private List<Ticket> tickets;
}
