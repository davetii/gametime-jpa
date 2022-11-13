package software.daveturner.gametimejpa.repo;

import org.springframework.data.repository.CrudRepository;
import software.daveturner.gametimejpa.entity.PlayerEntity;

public interface PlayerRepo extends CrudRepository<PlayerEntity, String> {
}
