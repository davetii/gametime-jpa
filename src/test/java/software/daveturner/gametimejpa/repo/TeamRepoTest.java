package software.daveturner.gametimejpa.repo;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import software.daveturner.gametimejpa.entity.CoachEntity;
import software.daveturner.gametimejpa.entity.PlayerEntity;
import software.daveturner.gametimejpa.entity.TeamEntity;
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
        CoachEntity coachBob = coachRepo.save(helper.newCoach("Dirk", "Diggler"));
        TeamEntity newTeam = teamRepo.save( helper.newTeam("TEST2", "Nowhere", "Testteam2"));
        Assertions.assertNull(newTeam.getCoach());

        newTeam.setCoach(coachBob);
        coachBob.setTeam(newTeam);

        teamRepo.save(newTeam);
        coachRepo.save(coachBob);
        Assertions.assertNotNull(newTeam.getCoach());
        Optional<TeamEntity> sameTeam = teamRepo.findById("TEST2");
        assertTrue(sameTeam.isPresent());
        assertEquals(sameTeam.get(), newTeam);
        assertEquals(sameTeam.get().getCoach(), coachBob);
    }

    @Test
    public void ensurePlayersAreAddedToTeam() {
        TeamEntity newTeam = teamRepo.save( helper.newTeam("TEST", "Nowhere", "testeteam"));
        teamRepo.save(newTeam);

        PlayerEntity player1 = helper.newPlayer("123", "Test", "Player1");
        playerRepo.save(player1);

        PlayerEntity player2 = helper.newPlayer("124", "Test", "Player2");
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
