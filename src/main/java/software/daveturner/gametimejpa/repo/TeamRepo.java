package software.daveturner.gametimejpa.repo;

import org.springframework.data.repository.CrudRepository;
import software.daveturner.gametimejpa.entity.TeamEntity;

public interface TeamRepo extends CrudRepository<TeamEntity, String> {
}
