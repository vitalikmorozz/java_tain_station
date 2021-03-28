package moroz.project.train.stabs;

import moroz.project.train.dto.Route.RequestRouteDTO;
import moroz.project.train.dto.Route.ResponseRouteDTO;
import moroz.project.train.entity.Route;

import java.time.LocalDateTime;
import java.util.ArrayList;

public final class RouteStab {
    public static final Long ID = 1L;
    public static final LocalDateTime arrivalTime = LocalDateTime.now();
    public static final LocalDateTime departureTime = LocalDateTime.now();
    public static Route getRoute() {
        return Route.builder().id(ID)
                .train(TrainStab.getTrain())
                .stoppages(new ArrayList<>())
                .tickets(new ArrayList<>())
                .build();
    }

    public static RequestRouteDTO getRequestDto() {
        return RequestRouteDTO.builder()
                .trainId(TrainStab.ID)
                .build();
    }

    public static ResponseRouteDTO getResponseDto() {
        return ResponseRouteDTO.builder().id(ID)
                .train(TrainStab.getResponseDto())
                .stoppages(new ArrayList<>())
                .tickets(new ArrayList<>())
                .build();
    }
}
