package moroz.project.train.dto.Station;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import moroz.project.train.interfaces.IBaseDTO;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseStationDTO implements IBaseDTO {
    private Long id;

    private String name;
}
