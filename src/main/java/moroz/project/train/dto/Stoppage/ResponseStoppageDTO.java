package moroz.project.train.dto.Stoppage;

import lombok.Data;
import moroz.project.train.dto.Route.ResponseRouteDTONoStoppages;
import moroz.project.train.dto.Station.ResponseStationDTO;
import moroz.project.train.interfaces.IBaseDTO;

import java.time.LocalDateTime;

@Data
public class ResponseStoppageDTO implements IBaseDTO {
    private Long id;

    private Integer stoppageOrder;

    private LocalDateTime arrivalTime;

    private LocalDateTime departureTime;

    private ResponseStationDTO station;

    private ResponseRouteDTONoStoppages route;
}
