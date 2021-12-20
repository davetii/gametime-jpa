package software.daveturner.gametimejpa.repo;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import software.daveturner.gametimejpa.GametimeJpaApplication;
import software.daveturner.gametimejpa.domain.Coach;
import software.daveturner.gametimejpa.domain.Team;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
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
    public void ensureSaveReturnsExpected() {
        Coach newCoach = coachRepo.save(helper.newCoach("Bob", "Jones"));
        List<Coach> list = helper.findAll(coachRepo);
        assertEquals(list.get(0), newCoach);
        assertEquals(coachRepo.findById(newCoach.getId()).get(), newCoach);
    }



    @Test
    public void ensureAddTeamReturnsExpected() {

        Coach newCoach = coachRepo.save(coachBob);
        assertNull(coachRepo.findById(newCoach.getId()).get().getTeam());
        panthers.setCoach(newCoach);
        coachBob.setTeam(panthers);
        teamRepo.save(panthers);
        Coach updatedCoach = coachRepo.save(coachBob);
        assertEquals(coachRepo.findById(updatedCoach.getId()).get().getTeam(), panthers);
    }

    @Test
    public void ensureMultipleCoachAddsToTeamReturnsExpected() {

        Coach stillCoachBob = coachRepo.save(coachBob);
        assertNull(coachRepo.findById(stillCoachBob.getId()).get().getTeam());

        Coach alSmith = helper.newCoach("Al", "Smith");
        alSmith.setTeam(panthers);
        panthers.setCoach(alSmith);

        Coach newCoach2 =  coachRepo.save(alSmith);
        teamRepo.save(panthers);
        assertEquals(coachRepo.findById(newCoach2.getId()).get().getTeam(), panthers);
        assertNull(coachRepo.findById(stillCoachBob.getId()).get().getTeam());
    }


}
