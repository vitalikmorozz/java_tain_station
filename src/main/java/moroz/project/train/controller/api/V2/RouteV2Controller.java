package moroz.project.train.controller.api.V2;

import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import moroz.project.train.controller.api.DtoCrudApiController;
import moroz.project.train.dto.Route.RequestRouteDTO;
import moroz.project.train.dto.Route.ResponseRouteDTO;
import moroz.project.train.entity.Route;
import moroz.project.train.service.V2.RouteV2Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/route")
public class RouteV2Controller extends DtoCrudApiController<Route, RequestRouteDTO, ResponseRouteDTO> {
    final RouteV2Service service;
    public RouteV2Controller(RouteV2Service service) {
        super(service);
        this.service = service;
    }

    @ApiOperation(value = "Add stoppage to route")
    @RequestMapping(value = "/stoppage", method = RequestMethod.POST)
    public ResponseRouteDTO addStoppageToRoute(
            @RequestParam Long routeId,
            @RequestParam Long stoppageId
    ) throws NotFoundException {
        return service.addStoppageById(routeId, stoppageId);
    }
}
