package software.daveturner.gametimejpa.repo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import software.daveturner.gametimejpa.GametimeJpaApplication;
import software.daveturner.gametimejpa.domain.Coach;
import software.daveturner.gametimejpa.domain.Team;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest(classes = GametimeJpaApplication.class)
public class CoachRepoTest {

    @Autowired
    private CoachRepo coachRepo;

    @Autowired
    private TeamRepo teamRepo;

    RepoTestHelper helper = new RepoTestHelper();

    @Test
    public void ensureSuccessfulSaveReturnsExpected() {
        Coach coachResponse = coachRepo.save(helper.newCoach("Bob", "Jones"));
        List<Coach> list = helper.findAll(coachRepo);
        assertEquals(list.get(0), coachResponse);
        assertEquals(coachRepo.findById(coachResponse.getId()).get(), coachResponse);
    }

    @Test
    public void ensureTeamReturnsExpected() {
        Coach coachBob = coachRepo.save(helper.newCoach("Bob", "Jones"));
        Coach coach = coachRepo.save(coachBob);
        assertNull(coachRepo.findById(coachBob.getId()).get().getTeam());

        Team panthers = helper.newTeam("MI", "Michigan", "Panthers");
        coachBob.setTeam(panthers);
        coachRepo.save(coachBob);
        assertEquals(coachRepo.findById(coach.getId()).get().getTeam(), panthers);
    }
}
