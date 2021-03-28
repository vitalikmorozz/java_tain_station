package moroz.project.train.dto.Route;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import moroz.project.train.dto.Stoppage.ResponseStoppageDTO;
import moroz.project.train.dto.Train.ResponseTrainDTO;
import moroz.project.train.interfaces.IBaseDTO;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseRouteDTONoTickets implements IBaseDTO {
    private Long id;

    private ResponseTrainDTO train;

    private List<ResponseStoppageDTO> stoppages;
}
