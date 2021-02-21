package moroz.project.train.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class Stoppage {
    @Id
    @GeneratedValue
    private Long id;

    private Integer stoppageOrder;

    private LocalDateTime arrivalTime;

    private LocalDateTime departureTime;

    @ManyToOne
    @JoinColumn(name = "station_id", nullable = false)
    private Station station;

    @ManyToOne
    @JoinColumn(name = "route_id", nullable = false)
    private Route route;


}
