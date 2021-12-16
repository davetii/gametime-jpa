package software.daveturner.gametimejpa.repo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import software.daveturner.gametimejpa.GametimeJpaApplication;
import software.daveturner.gametimejpa.domain.Coach;
import software.daveturner.gametimejpa.domain.Team;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
        Team team = newTeam("MI", "Michigan", "Panthers");
        team.setCoach(coachBob);
        teamRepo.save(team);
        assertEquals(teamRepo.count(), 1);
        List<Team> list = helper.findAll(teamRepo);
        assertEquals(list.get(0), team);
    }

    public Team newTeam(String id, String locale, String name) {
        Team team = new Team();
        team.setId(id);
        team.setLocale(locale);
        team.setName(name);
        return team;
    }



    /*
    @Test
    public void ensureDataLoads() {
        Assertions.assertEquals(1, teamRepo.count());
        List<Team> list = StreamSupport
                .stream(teamRepo.findAll().spliterator(), false)
                .collect(Collectors.toList());
        Assertions.assertEquals("MI", list.get(0).getId());
    }
    */

}
