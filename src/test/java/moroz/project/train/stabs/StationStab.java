package moroz.project.train.stabs;

import moroz.project.train.dto.Station.RequestStationDTO;
import moroz.project.train.dto.Station.ResponseStationDTO;
import moroz.project.train.entity.Station;

public final class StationStab {
    public static final Long ID = 1L;
    public static Station getStation() {
        return Station.builder().id(ID).name("Station 1").build();
    }

    public static RequestStationDTO getRequestDto() {
        return RequestStationDTO.builder().name("Station 1").build();
    }

    public static ResponseStationDTO getResponseDto() {
        return ResponseStationDTO.builder().id(ID).name("Station 1").build();
    }
}
