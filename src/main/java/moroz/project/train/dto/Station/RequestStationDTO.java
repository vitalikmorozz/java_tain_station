package moroz.project.train.dto.Station;

import lombok.Data;
import moroz.project.train.interfaces.IBaseDTO;

@Data
public class RequestStationDTO implements IBaseDTO {
    private String name;
}
