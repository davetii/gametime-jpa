package software.daveturner.gametimejpa.repo;

import org.junit.jupiter.api.*;
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
        coachBob = helper.newCoach("Bob", "Jones");
    }

    @AfterEach
    public void cleanup() {
        coachRepo.deleteAll();
        teamRepo.deleteAll();
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
    public void ensureMultipleCoachAssociatesTeamProperly() {

        Coach newCoach = coachRepo.save(coachBob);
        Long bobId = newCoach.getId();

        assertNull(coachRepo.findById(bobId).get().getTeam());

        Coach alSmith = helper.newCoach("Al", "Smith");
        alSmith.setTeam(panthers);
        Coach newCoach2 =  coachRepo.save(alSmith);
        assertNotNull(coachRepo.findById(newCoach2.getId()).get().getTeam());
        assertEquals(coachRepo.findById(newCoach2.getId()).get().getTeam(), panthers);
    }
}
