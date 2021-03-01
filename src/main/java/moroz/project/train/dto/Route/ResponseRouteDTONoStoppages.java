package moroz.project.train.dto.Route;

import lombok.Data;
import moroz.project.train.dto.Train.ResponseTrainDTO;
import moroz.project.train.interfaces.IBaseDTO;

@Data
public class ResponseRouteDTONoStoppages implements IBaseDTO {
    private Long id;

    private ResponseTrainDTO train;
}
