package moroz.project.train.dto.Train;

import lombok.Data;
import moroz.project.train.interfaces.IBaseDTO;

@Data
public class ResponseTrainDTO implements IBaseDTO {
    private Long id;

    private Long number;

    private String description;

    private Integer seatsCount;
}
