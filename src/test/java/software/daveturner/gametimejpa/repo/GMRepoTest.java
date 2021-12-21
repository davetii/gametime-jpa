package software.daveturner.gametimejpa.repo;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import software.daveturner.gametimejpa.domain.GM;
import software.daveturner.gametimejpa.domain.Team;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GMRepoTest extends BaseJPATest{

    Team panthers;
    GM gmBob;

    @BeforeEach
    public void setup() {
        panthers = helper.newTeam("MI", "Michigan", "Panthers");
        gmBob = helper.newGM("Bob", "Jones");
    }

    @Test
    public void ensureSuccessfulSaveReturnsExpected() {
        GM newGM = gmRepo.save(gmBob);
        assertEquals(gmRepo.findById(newGM.getId()).get(), newGM);
    }

    @Test
    public void ensureAddingToTeamReturnsExpected() {
        gmRepo.save(gmBob);
        panthers.setGm(gmBob);
        teamRepo.save(panthers);
        assertEquals(gmRepo.findById(gmBob.getId()).get().getTeam(), panthers);
    }
}
