package moroz.project.train.stabs;

import moroz.project.train.dto.Stoppage.RequestStoppageDTO;
import moroz.project.train.dto.Stoppage.ResponseStoppageDTO;
import moroz.project.train.entity.Stoppage;

import java.time.LocalDateTime;

public final class StoppageStab {
    public static final Long ID = 1L;
    public static final LocalDateTime arrivalTime = LocalDateTime.now();
    public static final LocalDateTime departureTime = LocalDateTime.now();
    public static Stoppage getStoppage() {
        return Stoppage.builder().id(ID).stoppageOrder(1).arrivalTime(arrivalTime).departureTime(departureTime).station(StationStab.getStation()).build();
    }

    public static RequestStoppageDTO getRequestDto() {
        return RequestStoppageDTO.builder().stoppageOrder(1).arrivalTime(arrivalTime).departureTime(departureTime).stationId(1L).build();
    }

    public static ResponseStoppageDTO getResponseDto() {
        return ResponseStoppageDTO.builder().id(ID).stoppageOrder(1).arrivalTime(arrivalTime).departureTime(departureTime).station(StationStab.getResponseDto()).build();
    }
}
