package moroz.project.train.service.V1;

import moroz.project.train.entity.Route;
import moroz.project.train.repository.RouteRepository;
import moroz.project.train.service.BasicCrudService;
import org.springframework.stereotype.Service;

@Service
public class RouteService extends BasicCrudService<Route> {
    public RouteService(RouteRepository repository) {
        super(repository);
    }
}
