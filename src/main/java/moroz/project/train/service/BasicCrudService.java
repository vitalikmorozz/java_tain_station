package moroz.project.train.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@RequiredArgsConstructor
public abstract class BasicCrudService<T> {
    final protected JpaRepository<T, Long> repository;

    public T findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<T> findAll() {
        return repository.findAll();
    }

    public T create(T entity) { return repository.save(entity); }

    public T update(Long id, T entity) {
        if(repository.existsById(id))
            return repository.save(entity);
        else
            return null;
    }

    public void delete(T entity) {
        repository.delete(entity);
    }

    public void deleteById(Long id) { repository.deleteById(id); }
}
