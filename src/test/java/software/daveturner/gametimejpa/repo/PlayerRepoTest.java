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
import software.daveturner.gametimejpa.domain.Role;
import software.daveturner.gametimejpa.domain.Team;

import javax.transaction.Transactional;

import java.util.LinkedHashSet;
import java.util.Set;

import static software.daveturner.gametimejpa.domain.Position.PG;
import static software.daveturner.gametimejpa.domain.Role.STARTER;

@SpringBootTest(classes = GametimeJpaApplication.class)
public class PlayerRepoTest {

    @Autowired
    PlayerRepo playerRepo;

    @Autowired
    TeamRepo teamRepo;

    RepoTestHelper helper = new RepoTestHelper();

    @BeforeEach
    public void setup() {

    }

    @AfterEach
    public void cleanup() {
        playerRepo.deleteAll();
        teamRepo.deleteAll();
    }

    @Test
    public void ensureAPIReturnsExpected() {

        Player player = helper.newPlayer("Test", "Player1");
        player.setPosition(PG);
        player.setRole(STARTER);
        player = playerRepo.save(player);
        Assertions.assertEquals(1, playerRepo.count());
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
        //teamRepo.save(panthers);

        player.setTeam(panthers);
        panthers.getPlayers().add(player);

        playerRepo.save(player);
        teamRepo.save(panthers);




        //







        //teamRepo.save(panthers);

        Assertions.assertEquals(1, teamRepo.findById(panthers.getId()).get().getPlayers().size());
        Assertions.assertEquals("MI", playerRepo.findById(player.getId()).get().getTeam().getId());
    }
}
