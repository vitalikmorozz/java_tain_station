package moroz.project.train.dto.Stoppage;

import lombok.Data;
import moroz.project.train.interfaces.IBaseDTO;

import java.time.LocalDateTime;

@Data
public class RequestStoppageDTO implements IBaseDTO {
    private Integer stoppageOrder;

    private LocalDateTime arrivalTime;

    private LocalDateTime departureTime;

    private Long stationId;
}
