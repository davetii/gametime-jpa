package software.daveturner.gametimejpa.repo;

import org.springframework.data.repository.CrudRepository;
import software.daveturner.gametimejpa.domain.Player;

public interface PlayerRepo extends CrudRepository<Player, Long> {
}
