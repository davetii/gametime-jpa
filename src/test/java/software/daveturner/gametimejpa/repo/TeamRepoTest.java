package software.daveturner.gametimejpa.repo;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import software.daveturner.gametimejpa.GametimeJpaApplication;
import software.daveturner.gametimejpa.domain.Coach;
import software.daveturner.gametimejpa.domain.Team;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(classes = GametimeJpaApplication.class)
public class TeamRepoTest {

    @Autowired
    private TeamRepo teamRepo;

    @Autowired
    private CoachRepo coachRepo;

    RepoTestHelper helper = new RepoTestHelper();

    @Test
    public void ensureTeamLoadReturnsExpected() {
        Coach coachBob = coachRepo.save(helper.newCoach("Bob", "Jones"));
        Team team = helper.newTeam("MI", "Michigan", "Panthers");
        team.setCoach(coachBob);
        teamRepo.save(team);
        assertEquals(teamRepo.count(), 1);
        List<Team> list = helper.findAll(teamRepo);
        assertEquals(list.get(0), team);
        assertNotNull(team.getCoach());
        assertEquals(team.getCoach(), coachBob);
    }


}
