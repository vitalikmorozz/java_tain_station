package moroz.project.train.service;

import moroz.project.train.entity.Route;
import moroz.project.train.repository.RouteRepository;
import org.springframework.stereotype.Service;

@Service
public class RouteService extends BasicCrudService<Route> {
    public RouteService(RouteRepository repository) {
        super(repository);
    }
}
