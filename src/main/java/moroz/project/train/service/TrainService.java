package moroz.project.train.service;

import moroz.project.train.entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class TrainService extends BasicCrudService<Train> {
    public TrainService(JpaRepository<Train, Long> repository) {
        super(repository);
    }
}
