package moroz.project.train.repository;

import moroz.project.train.entity.Stoppage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoppageRepository extends JpaRepository<Stoppage, Long> {
}
