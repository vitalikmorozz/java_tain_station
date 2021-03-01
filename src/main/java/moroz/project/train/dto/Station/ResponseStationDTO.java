package moroz.project.train.dto.Station;

import lombok.Data;
import moroz.project.train.interfaces.IBaseDTO;

@Data
public class ResponseStationDTO implements IBaseDTO {
    private Long id;

    private String name;
}
