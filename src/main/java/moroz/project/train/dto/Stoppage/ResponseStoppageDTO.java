package moroz.project.train.dto.Stoppage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import moroz.project.train.dto.Route.ResponseRouteDTONoStoppages;
import moroz.project.train.dto.Station.ResponseStationDTO;
import moroz.project.train.interfaces.IBaseDTO;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseStoppageDTO implements IBaseDTO {
    private Long id;

    private Integer stoppageOrder;

    private LocalDateTime arrivalTime;

    private LocalDateTime departureTime;

    private ResponseStationDTO station;

    private ResponseRouteDTONoStoppages route;
}
