package software.daveturner.gametimejpa.repo;

import org.springframework.data.repository.CrudRepository;
import software.daveturner.gametimejpa.domain.GM;

public interface GMRepo extends CrudRepository<GM, Long> {
}
