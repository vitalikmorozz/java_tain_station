package moroz.project.train.dto.Route;

import lombok.Data;
import moroz.project.train.interfaces.IBaseDTO;

@Data
public class RequestRouteDTO implements IBaseDTO {
    private Long trainId;
}
