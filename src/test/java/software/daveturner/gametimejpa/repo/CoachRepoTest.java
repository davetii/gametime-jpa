package software.daveturner.gametimejpa.repo;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import software.daveturner.gametimejpa.domain.Coach;
import software.daveturner.gametimejpa.domain.Team;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CoachRepoTest extends BaseJPATest{

    Team panthers;
    Coach coachBob;

    @BeforeEach
    public void setup() {
        panthers = helper.newTeam("MI", "Michigan", "Panthers");
        teamRepo.save(panthers);
        coachBob = helper.newCoach("Bob", "Jones");
        coachRepo.save(coachBob);
    }

    @AfterEach
    public void cleanup() {
        playerRepo.deleteAll();
        teamRepo.deleteAll();
        coachRepo.deleteAll();
    }

    @Test
    public void ensureSaveReturnsExpected() {
        Coach newCoach = coachRepo.save(helper.newCoach("Terry", "Stoudamire"));
        assertEquals(coachRepo.findById(newCoach.getId()).get(), newCoach);
    }

    @Test
    public void ensureAddTeamReturnsExpected() {

        assertNull(coachRepo.findById(coachBob.getId()).get().getTeam());
        panthers.setCoach(coachBob);
        coachBob.setTeam(panthers);
        teamRepo.save(panthers);
        coachRepo.save(coachBob);
        assertEquals(coachRepo.findById(coachBob.getId()).get().getTeam(), panthers);
    }

    @Test
    public void ensureCoachSwitchReturnsExpected() {

        panthers.setCoach(coachBob);
        coachBob.setTeam(panthers);
        teamRepo.save(panthers);
        coachRepo.save(coachBob);
        assertEquals(coachRepo.findById(coachBob.getId()).get().getTeam(), panthers);

        coachBob.setTeam(null);
        panthers.setCoach(null);

        coachRepo.save(coachBob);
        teamRepo.save(panthers);

        assertNull(coachRepo.findById(coachBob.getId()).get().getTeam());

        Coach alSmith = helper.newCoach("Al", "Smith");
        coachRepo.save(alSmith);

        panthers.setCoach(alSmith);
        alSmith.setTeam(panthers);
        coachRepo.save(alSmith);
        teamRepo.save(panthers);

        assertEquals(coachRepo.findById(alSmith.getId()).get().getTeam(), panthers);
    }


}
