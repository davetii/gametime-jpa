package software.daveturner.gametimejpa.repo;

import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import software.daveturner.gametimejpa.GametimeJpaApplication;
import software.daveturner.gametimejpa.domain.Coach;
import software.daveturner.gametimejpa.domain.Team;

import java.util.Optional;

@SpringBootTest(classes = GametimeJpaApplication.class)
public class PreLoadedDataTest {


    @Autowired
    CoachRepo coachRepo;

    @Autowired
    TeamRepo teamRepo;

    RepoTestHelper helper = new RepoTestHelper();

    @AfterEach
    public void cleanup() {
        coachRepo.deleteAll();
        teamRepo.deleteAll();
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
    public void ensurePreLoadedTeamDataExists() {
        Optional<Team> panthers = teamRepo.findById("MI");
        Assertions.assertTrue(panthers.isPresent());
        Assertions.assertEquals("Turner", panthers.get().getCoach().getLastName());
    }

    @Test
    @Sql(scripts = {"/preloaded-data-tests.sql"},
            config = @SqlConfig(encoding = "utf-8", transactionMode = SqlConfig.TransactionMode.ISOLATED))
    public void ensureAddTeamWorks() {
        Team chicago = helper.newTeam("CHI", "Chicacgo", "Blackhawks");
        teamRepo.save(chicago);
        Assertions.assertEquals(2, teamRepo.count());
    }


}
