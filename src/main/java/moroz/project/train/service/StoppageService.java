package moroz.project.train.service;

import moroz.project.train.entity.Stoppage;
import moroz.project.train.repository.StoppageRepository;
import org.springframework.stereotype.Service;

@Service
public class StoppageService extends BasicCrudService<Stoppage> {
    public StoppageService(StoppageRepository repository) {
        super(repository);
    }
}
