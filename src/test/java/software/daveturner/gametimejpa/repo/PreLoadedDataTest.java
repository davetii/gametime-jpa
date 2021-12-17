package software.daveturner.gametimejpa.repo;

import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import software.daveturner.gametimejpa.GametimeJpaApplication;
import software.daveturner.gametimejpa.domain.Coach;
import software.daveturner.gametimejpa.domain.GM;
import software.daveturner.gametimejpa.domain.Team;

import java.util.Optional;

@SpringBootTest(classes = GametimeJpaApplication.class)
public class PreLoadedDataTest {


    @Autowired
    CoachRepo coachRepo;

    @Autowired
    TeamRepo teamRepo;

    @Autowired
    GMRepo gmRepo;

    RepoTestHelper helper = new RepoTestHelper();

    @AfterEach
    public void cleanup() {
        coachRepo.deleteAll();
        teamRepo.deleteAll();
        gmRepo.deleteAll();
    }


    @Test
    @Sql(scripts = {"/preloaded-data-tests.sql"},
            config = @SqlConfig(encoding = "utf-8", transactionMode = SqlConfig.TransactionMode.ISOLATED))
    public void ensurePreLoadedCoachDataExists() {
        Optional<Coach> daveTurner = coachRepo.findById(1l);
        Assertions.assertTrue(daveTurner.isPresent());
        Assertions.assertEquals("Panthers", daveTurner.get().getTeam().getName());
    }

    @Test
    @Sql(scripts = {"/preloaded-data-tests.sql"},
            config = @SqlConfig(encoding = "utf-8", transactionMode = SqlConfig.TransactionMode.ISOLATED))
    public void ensurePreLoadedGMDataExists() {
        Optional<GM> joeDumas = gmRepo.findById(1l);
        Assertions.assertTrue(joeDumas.isPresent());
        Assertions.assertEquals("Panthers", joeDumas.get().getTeam().getName());
    }

    @Test
    @Sql(scripts = {"/preloaded-data-tests.sql"},
            config = @SqlConfig(encoding = "utf-8", transactionMode = SqlConfig.TransactionMode.ISOLATED))
    public void ensurePreLoadedTeamDataExists() {
        Optional<Team> panthers = teamRepo.findById("MI");
        Assertions.assertTrue(panthers.isPresent());
        Assertions.assertEquals("Turner", panthers.get().getCoach().getLastName());
        Assertions.assertEquals("Dumas", panthers.get().getGm().getLastName());
    }

    @Test
    @Sql(scripts = {"/preloaded-data-tests.sql"},
            config = @SqlConfig(encoding = "utf-8", transactionMode = SqlConfig.TransactionMode.ISOLATED))
    public void ensureAddTeamWorks() {
        Team chicago = helper.newTeam("CHI", "Chicacgo", "Blackhawks");
        Team losAngeles = helper.newTeam("LA", "Los Angeles", "Kings");
        teamRepo.save(chicago);
        teamRepo.save(losAngeles);
        Assertions.assertEquals(3, teamRepo.count());
    }


}
