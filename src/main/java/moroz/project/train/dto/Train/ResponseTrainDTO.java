package moroz.project.train.dto.Train;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import moroz.project.train.interfaces.IBaseDTO;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTrainDTO implements IBaseDTO {
    private Long id;

    private Long number;

    private String description;

    private Integer seatsCount;
}
