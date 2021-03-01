package moroz.project.train.service.V1;

import moroz.project.train.entity.Station;
import moroz.project.train.repository.StationRepository;
import moroz.project.train.service.BasicCrudService;
import org.springframework.stereotype.Service;

@Service
public class StationService extends BasicCrudService<Station> {
    public StationService(StationRepository repository) {
        super(repository);
    }
}
