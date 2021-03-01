package moroz.project.train.service.V1;

import moroz.project.train.entity.Stoppage;
import moroz.project.train.repository.StoppageRepository;
import moroz.project.train.service.BasicCrudService;
import org.springframework.stereotype.Service;

@Service
public class StoppageService extends BasicCrudService<Stoppage> {
    public StoppageService(StoppageRepository repository) {
        super(repository);
    }
}
