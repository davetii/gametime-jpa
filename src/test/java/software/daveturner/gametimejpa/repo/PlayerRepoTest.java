package software.daveturner.gametimejpa.repo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import software.daveturner.gametimejpa.GametimeJpaApplication;
import software.daveturner.gametimejpa.domain.Player;
import software.daveturner.gametimejpa.domain.Position;
import software.daveturner.gametimejpa.domain.Team;

@SpringBootTest(classes = GametimeJpaApplication.class)
public class PlayerRepoTest {

    @Autowired
    PlayerRepo playerRepo;

    @Autowired
    TeamRepo teamRepo;

    Player player;

    RepoTestHelper helper = new RepoTestHelper();

    @BeforeEach
    public void setup() {
        player = helper.newPlayer("Test", "Player1");
        player.setPosition(Position.PG);
        player = playerRepo.save(player);
    }

    @AfterEach
    public void cleanup() {
        playerRepo.deleteAll();
        teamRepo.deleteAll();
    }

    @Test
    public void ensureAPIReturnsExpected() {

        Assertions.assertEquals(1, playerRepo.count());
        Assertions.assertEquals(Position.PG, playerRepo.findById(player.getId()).get().getPosition());

    }

    @Test
    public void ensureAddingTeamReturnsExpected() {
        Team panthers = helper.newTeam("MI", "Michigan", "Panthers");
        panthers.getPlayers().add(player);
        teamRepo.save(panthers);
        player.setTeam(panthers);
        playerRepo.save(player);
        Assertions.assertEquals(1, teamRepo.findById(panthers.getId()).get().getPlayers().size());
        Assertions.assertEquals("MI", playerRepo.findById(player.getId()).get().getTeam().getId());
    }
}
