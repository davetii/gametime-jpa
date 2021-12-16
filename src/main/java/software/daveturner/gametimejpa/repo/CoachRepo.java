package software.daveturner.gametimejpa.repo;

import org.springframework.data.repository.CrudRepository;
import software.daveturner.gametimejpa.domain.Coach;

public interface CoachRepo extends CrudRepository<Coach, Long> {

}
