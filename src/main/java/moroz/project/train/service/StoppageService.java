package moroz.project.train.service;

import moroz.project.train.entity.Stoppage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class StoppageService extends BasicCrudService<Stoppage> {
    public StoppageService(JpaRepository<Stoppage, Long> repository) {
        super(repository);
    }
}
