package software.daveturner.gametimejpa.repo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import software.daveturner.gametimejpa.GametimeJpaApplication;
import software.daveturner.gametimejpa.domain.GM;
import software.daveturner.gametimejpa.domain.Team;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = GametimeJpaApplication.class)
public class GMRepoTest {

    @Autowired
    private TeamRepo teamRepo;

    @Autowired
    private GMRepo gmRepo;

    RepoTestHelper helper = new RepoTestHelper();

    Team panthers;
    GM gmBob;

    @BeforeEach
    public void setup() {
        panthers = helper.newTeam("MI", "Michigan", "Panthers");
        gmBob = helper.newGM("Bob", "Jones");
    }

    @AfterEach
    public void cleanup() {
        gmRepo.deleteAll();
        teamRepo.deleteAll();
    }

    @Test
    public void ensureSuccessfulSaveReturnsExpected() {
        GM newGM = gmRepo.save(gmBob);
        List<GM> list = helper.findAll(gmRepo);
        assertEquals(list.get(0), newGM);
        assertEquals(gmRepo.findById(newGM.getId()).get(), newGM);
    }

    @Test
    public void ensureAddingToTeamReturnsExpected() {
        gmRepo.save(gmBob);
        panthers.setGm(gmBob);
        teamRepo.save(panthers);

        List<GM> gmList = helper.findAll(gmRepo);
        assertEquals(gmList.get(0).getTeam(), panthers);

        List<Team> teamList = helper.findAll(teamRepo);
        assertEquals(teamList.get(0).getGm(), gmBob);


    }

}
