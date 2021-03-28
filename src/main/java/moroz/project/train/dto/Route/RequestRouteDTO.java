package moroz.project.train.dto.Route;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import moroz.project.train.interfaces.IBaseDTO;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestRouteDTO implements IBaseDTO {
    private Long trainId;
}
