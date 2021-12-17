package software.daveturner.gametimejpa.repo;

import org.springframework.data.repository.CrudRepository;
import software.daveturner.gametimejpa.domain.Conference;

public interface ConferenceRepo extends CrudRepository<Conference, String> {
}
