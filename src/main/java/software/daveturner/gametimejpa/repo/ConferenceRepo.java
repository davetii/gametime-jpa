package software.daveturner.gametimejpa.repo;

import org.springframework.data.repository.CrudRepository;
import software.daveturner.gametimejpa.entity.ConferenceEntity;


public interface ConferenceRepo extends CrudRepository<ConferenceEntity, String> {
}
