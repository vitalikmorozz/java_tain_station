package moroz.project.train.controller.api;

import lombok.RequiredArgsConstructor;
import moroz.project.train.service.BasicCrudService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public abstract class BasicCrudApiController<T> {
    final BasicCrudService<T> service;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<T> getAll() {
        return service.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public T getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public T update(@PathVariable Long id, @RequestBody T entity) {
        return service.update(id, entity);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public T create(@RequestBody T entity) {
        return service.create(entity);
    }
}
