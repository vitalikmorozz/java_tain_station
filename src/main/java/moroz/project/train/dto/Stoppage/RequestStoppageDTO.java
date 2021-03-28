package moroz.project.train.dto.Stoppage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import moroz.project.train.interfaces.IBaseDTO;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestStoppageDTO implements IBaseDTO {
    private Integer stoppageOrder;

    private LocalDateTime arrivalTime;

    private LocalDateTime departureTime;

    private Long stationId;
}
