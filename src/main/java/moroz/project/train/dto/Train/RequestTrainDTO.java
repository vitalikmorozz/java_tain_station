package moroz.project.train.dto.Train;

import lombok.Data;
import moroz.project.train.interfaces.IBaseDTO;

@Data
public class RequestTrainDTO implements IBaseDTO {
    private Long number;

    private String description;

    private Integer seatsCount;
}
