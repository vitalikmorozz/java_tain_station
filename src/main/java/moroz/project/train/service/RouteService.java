package moroz.project.train.service;

import moroz.project.train.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class RouteService extends BasicCrudService<Route> {
    public RouteService(JpaRepository<Route, Long> repository) {
        super(repository);
    }
}
