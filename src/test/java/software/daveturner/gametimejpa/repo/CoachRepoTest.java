package software.daveturner.gametimejpa.repo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import software.daveturner.gametimejpa.GametimeJpaApplication;
import software.daveturner.gametimejpa.domain.Coach;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = GametimeJpaApplication.class)
public class CoachRepoTest {

    @Autowired
    private CoachRepo coachRepo;

    RepoTestHelper helper = new RepoTestHelper();
    @Test
    public void ensureSuccessfulSaveReturnsExpected() {
        Coach coachResponse = coachRepo.save(helper.newCoach("Bob", "Jones"));
        List<Coach> list = helper.findAll(coachRepo);
        assertEquals(list.get(0), coachResponse);
        assertEquals(coachRepo.findById(coachResponse.getId()).get(), coachResponse);
    }
}
