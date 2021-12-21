package software.daveturner.gametimejpa.repo;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import software.daveturner.gametimejpa.domain.Player;
import software.daveturner.gametimejpa.domain.Team;

import javax.transaction.Transactional;

import static software.daveturner.gametimejpa.domain.Position.PG;
import static software.daveturner.gametimejpa.domain.Role.STARTER;

public class PlayerRepoTest extends BaseJPATest{

    @Test
    public void ensureAPIReturnsExpected() {
        long currentCount = (int) playerRepo.count();

        Player player = helper.newPlayer("Test", "Player1");
        player.setPosition(PG);
        player.setRole(STARTER);
        player = playerRepo.save(player);
        currentCount += 1;
        Assertions.assertEquals(currentCount, playerRepo.count());
        Assertions.assertEquals(PG, playerRepo.findById(player.getId()).get().getPosition());

    }

    @Test
    @Transactional
    public void ensureAddingTeamReturnsExpected() {

        Player player = helper.newPlayer("Test", "Player1");
        player.setPosition(PG);
        player.setRole(STARTER);
        playerRepo.save(player);

        Team panthers = helper.newTeam("MI", "Michigan", "Panthers");

        player.setTeam(panthers);
        panthers.getPlayers().add(player);

        playerRepo.save(player);
        teamRepo.save(panthers);
        Assertions.assertEquals(1, teamRepo.findById(panthers.getId()).get().getPlayers().size());
        Assertions.assertEquals("MI", playerRepo.findById(player.getId()).get().getTeam().getId());
    }
}
