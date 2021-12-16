package software.daveturner.gametimejpa.repo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import software.daveturner.gametimejpa.GametimeJpaApplication;
import software.daveturner.gametimejpa.domain.Coach;
import software.daveturner.gametimejpa.domain.Team;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = GametimeJpaApplication.class)
public class CoachRepoTest {

    @Autowired
    private CoachRepo coachRepo;

    @Autowired
    private TeamRepo teamRepo;

    RepoTestHelper helper = new RepoTestHelper();

    Team panthers;
    Coach coachBob;

    @BeforeEach
    public void setup() {
        panthers = helper.newTeam("MI", "Michigan", "Panthers");
        coachBob = coachRepo.save(helper.newCoach("Bob", "Jones"));
    }

    @Test
    public void ensureSuccessfulSaveReturnsExpected() {
        Coach newCoach = coachRepo.save(helper.newCoach("Bob", "Jones"));
        List<Coach> list = helper.findAll(coachRepo);
        assertEquals(list.get(0), newCoach);
        assertEquals(coachRepo.findById(newCoach.getId()).get(), newCoach);
    }

    @Test
    public void ensureTeamFieldIsPopulated() {
        Coach newCoach = coachRepo.save(coachBob);
        assertNull(coachRepo.findById(newCoach.getId()).get().getTeam());

        coachBob.setTeam(panthers);
        newCoach = coachRepo.save(coachBob);
        assertEquals(coachRepo.findById(newCoach.getId()).get().getTeam(), panthers);
    }

    @Test
    public void ensureTeamFieldIsPopulatedProperly() {

        coachRepo.save(coachBob);
        Long newId = 2l;
        Coach alSmith = helper.newCoach(newId, "Al", "Smith", panthers);
        coachRepo.save(alSmith);

        assertNull(coachRepo.findById(coachBob.getId()).get().getTeam());
        assertNotNull(coachRepo.findById(newId).get().getTeam());
        assertEquals(coachRepo.findById(newId).get().getTeam(), panthers);
    }
}
