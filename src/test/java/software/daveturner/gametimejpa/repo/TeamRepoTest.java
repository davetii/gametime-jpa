package software.daveturner.gametimejpa.repo;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import software.daveturner.gametimejpa.GametimeJpaApplication;
import software.daveturner.gametimejpa.domain.Coach;
import software.daveturner.gametimejpa.domain.Conference;
import software.daveturner.gametimejpa.domain.Player;
import software.daveturner.gametimejpa.domain.Team;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(classes = GametimeJpaApplication.class)
public class TeamRepoTest {

    @Autowired
    private TeamRepo teamRepo;

    @Autowired
    private CoachRepo coachRepo;

    @Autowired
    private PlayerRepo playerRepo;



    RepoTestHelper helper = new RepoTestHelper();

    private Conference newConference;

    @BeforeEach
    public void setup() {

    }

    @AfterEach
    public void cleanup() {
        helper.cleanupAllRepos();
        coachRepo.deleteAll();
        playerRepo.deleteAll();
        teamRepo.deleteAll();
    }

    @Test
    public void verifyAPIWorksAsExpected() {
        Coach coachBob = coachRepo.save(helper.newCoach("Bob", "Jones"));
        Team newTeam = teamRepo.save( helper.newTeam("MI", "Michigan", "Panthers"));
        assertEquals(teamRepo.count(), 1);
        Assertions.assertNull(newTeam.getCoach());
        newTeam.setCoach(coachBob);
        newTeam = teamRepo.save(newTeam);
        Assertions.assertNotNull(newTeam.getCoach());

        List<Team> list = helper.findAll(teamRepo);
        assertEquals(list.get(0), newTeam);
        assertNotNull(list.get(0));
        assertEquals(list.get(0).getCoach(), coachBob);

        Optional<Team> sameTeam = teamRepo.findById("MI");
        assertTrue(sameTeam.isPresent());
        assertEquals(sameTeam.get(), newTeam);
    }

    @Test
    public void ensurePlayersAreReturnedForTeams() {
        Team newTeam = teamRepo.save( helper.newTeam("MI", "Michigan", "Panthers"));
        Player player1 = helper.newPlayer("Test", "Player1");
        player1.setTeam(newTeam);
        playerRepo.save(player1);

        Player player2 = helper.newPlayer("Test", "Player2");
        player2.setTeam(newTeam);
        playerRepo.save(player2);

        newTeam.getPlayers().add(player1);
        newTeam.getPlayers().add(player2);

        Team updatedTeam = teamRepo.save(newTeam);
        Assertions.assertEquals(2, updatedTeam.getPlayers().size());
    }
}
