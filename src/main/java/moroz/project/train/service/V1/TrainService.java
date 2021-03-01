package moroz.project.train.service.V1;

import moroz.project.train.entity.Train;
import moroz.project.train.repository.TrainRepository;
import moroz.project.train.service.BasicCrudService;
import org.springframework.stereotype.Service;

@Service
public class TrainService extends BasicCrudService<Train> {
    public TrainService(TrainRepository repository) {
        super(repository);
    }
}
