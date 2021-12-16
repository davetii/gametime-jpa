package software.daveturner.gametimejpa.repo;

import org.springframework.data.repository.CrudRepository;
import software.daveturner.gametimejpa.domain.Coach;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class RepoTestHelper {

    public Coach newCoach(String firstName, String lastName) {
        Coach coach = new Coach();
        coach.setFirstName(firstName);
        coach.setLastName(lastName);
        return coach;
    }

    public List findAll(CrudRepository<?, ?> repo) {
        return StreamSupport
                .stream(repo.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
}
