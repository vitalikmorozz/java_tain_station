package moroz.project.train.controller.api;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import moroz.project.train.interfaces.IBaseEntity;
import moroz.project.train.service.BasicCrudService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public abstract class BasicCrudApiController<TEntity extends IBaseEntity> {
    final BasicCrudService<TEntity> service;

    @ApiOperation(value = "Get list of entities")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<TEntity> getAll(
            @RequestParam(required = false, defaultValue = "10") Integer size,
            @RequestParam(required = false, defaultValue = "1") Integer page
    ) {
        List<TEntity> list = service.findAll();
        final int listSize = list.size();
        int start = 0;
        int end = listSize;
        if (listSize > size) {
            start = Math.min(listSize, size * (page - 1));
            end = Math.min(listSize, page * size);
        }
        return list.subList(start, end);
    }

    @ApiOperation(value = "Get single entity by id")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public TEntity getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @ApiOperation(value = "Delete entity by id")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

    @ApiOperation(value = "Update entity by id")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public TEntity update(@PathVariable Long id, @RequestBody TEntity entity) {
        return service.update(id, entity);
    }

    @ApiOperation(value = "Create entity")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public TEntity create(@RequestBody TEntity entity) {
        return service.create(entity);
    }
}
