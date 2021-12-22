package software.daveturner.gametimejpa.repo;

import org.springframework.data.repository.CrudRepository;
import software.daveturner.gametimejpa.entity.CoachEntity;

public interface CoachRepo extends CrudRepository<CoachEntity, Long> {

}
