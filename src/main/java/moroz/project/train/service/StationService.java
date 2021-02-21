package moroz.project.train.service;

import moroz.project.train.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class StationService extends BasicCrudService<Station> {
    public StationService(JpaRepository<Station, Long> repository) {
        super(repository);
    }
}
