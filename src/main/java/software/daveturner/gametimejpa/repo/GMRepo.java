package software.daveturner.gametimejpa.repo;

import org.springframework.data.repository.CrudRepository;
import software.daveturner.gametimejpa.entity.GMEntity;

public interface GMRepo extends CrudRepository<GMEntity, Long> {
}
