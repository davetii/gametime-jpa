package software.daveturner.gametimejpa.repo;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import software.daveturner.gametimejpa.domain.Coach;
import software.daveturner.gametimejpa.domain.Player;
import software.daveturner.gametimejpa.domain.Team;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class TeamRepoTest extends BaseJPATest {

    @Test
    public void ensureAddTeamReturnsExpected() {
        long currentTeamCount = teamRepo.count();
        teamRepo.save( helper.newTeam("TEST2", "Nowhere", "Testteam2"));
        assertEquals(teamRepo.count(), ++currentTeamCount);
    }

    @Test
    public void ensureAddCoachReturnsExpected() {
        Coach coachBob = coachRepo.save(helper.newCoach("Dirk", "Diggler"));
        Team newTeam = teamRepo.save( helper.newTeam("TEST2", "Nowhere", "Testteam2"));
        Assertions.assertNull(newTeam.getCoach());

        newTeam.setCoach(coachBob);
        coachBob.setTeam(newTeam);

        teamRepo.save(newTeam);
        coachRepo.save(coachBob);
        Assertions.assertNotNull(newTeam.getCoach());
        Optional<Team> sameTeam = teamRepo.findById("TEST2");
        assertTrue(sameTeam.isPresent());
        assertEquals(sameTeam.get(), newTeam);
        assertEquals(sameTeam.get().getCoach(), coachBob);
    }

    @Test
    public void ensurePlayersAreAddedToTeam() {
        Team newTeam = teamRepo.save( helper.newTeam("TEST", "Nowhere", "testeteam"));
        teamRepo.save(newTeam);

        Player player1 = helper.newPlayer("Test", "Player1");
        playerRepo.save(player1);

        Player player2 = helper.newPlayer("Test", "Player2");
        playerRepo.save(player2);

        player1.setTeam(newTeam);
        player2.setTeam(newTeam);

        newTeam.getPlayers().add(player1);
        newTeam.getPlayers().add(player2);

        teamRepo.save(newTeam);
        playerRepo.save(player1);
        playerRepo.save(player2);


        Assertions.assertEquals(2, newTeam.getPlayers().size());
    }
}
