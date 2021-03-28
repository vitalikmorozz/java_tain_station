package moroz.project.train.stabs;

import moroz.project.train.dto.Train.RequestTrainDTO;
import moroz.project.train.dto.Train.ResponseTrainDTO;
import moroz.project.train.entity.Train;

import java.util.ArrayList;

public final class TrainStab {
    public static final Long ID = 1L;
    public static Train getTrain() {
        return Train.builder().id(ID).description("Train 1").number(123L).seatsCount(15).routes(new ArrayList<>()).build();
    }

    public static RequestTrainDTO getRequestDto() {
        return RequestTrainDTO.builder().description("Train 1").number(123L).seatsCount(15).build();
    }

    public static ResponseTrainDTO getResponseDto() {
        return ResponseTrainDTO.builder().id(ID).description("Train 1").number(123L).seatsCount(15).build();
    }
}
