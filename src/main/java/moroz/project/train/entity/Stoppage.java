package moroz.project.train.entity;

import lombok.Data;
import moroz.project.train.interfaces.IBaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class Stoppage implements IBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer stoppageOrder;

    private LocalDateTime arrivalTime;

    private LocalDateTime departureTime;

    @ManyToOne
    @JoinColumn(name = "station_id", nullable = false)
    private Station station;

    @ManyToOne
    @JoinColumn(name = "route_id", nullable = true)
    private Route route;
}
