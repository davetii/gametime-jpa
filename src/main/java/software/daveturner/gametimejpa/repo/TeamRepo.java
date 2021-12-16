package software.daveturner.gametimejpa.repo;

import org.springframework.data.repository.CrudRepository;
import software.daveturner.gametimejpa.domain.Team;

public interface TeamRepo extends CrudRepository<Team, String> {
}
