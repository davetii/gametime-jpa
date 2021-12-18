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

@SpringBootTest
public class PreLoadedDataTest {


    @Autowired
    CoachRepo coachRepo;

    @Autowired
    TeamRepo teamRepo;

    @Autowired
    GMRepo gmRepo;

    @Autowired
    ConferenceRepo conferenceRepo;

    RepoTestHelper helper = new RepoTestHelper();

    @AfterEach
    public void cleanup() {
        teamRepo.deleteAll();
        gmRepo.deleteAll();
        coachRepo.deleteAll();
        conferenceRepo.deleteAll();
    }


    @Test
    @Sql(scripts = {"/preloaded-data-tests.sql"},
            config = @SqlConfig(encoding = "utf-8", transactionMode = SqlConfig.TransactionMode.ISOLATED))
    public void ensurePreLoadedCoachDataExists() {
        Optional<Coach> frankValcone = coachRepo.findById(2l);
        Assertions.assertTrue(frankValcone.isPresent());
        Assertions.assertEquals("Fastbacks", frankValcone.get().getTeam().getName());
    }

    @Test
    @Sql(scripts = {"/preloaded-data-tests.sql"},
            config = @SqlConfig(encoding = "utf-8", transactionMode = SqlConfig.TransactionMode.ISOLATED))
    public void ensurePreLoadedGMDataExists() {
        Optional<GM> donSchmidt = gmRepo.findById(10l);
        Assertions.assertTrue(donSchmidt.isPresent());
        Assertions.assertEquals("Gators", donSchmidt.get().getTeam().getName());
    }

    @Test
    @Sql(scripts = {"/preloaded-data-tests.sql"},
            config = @SqlConfig(encoding = "utf-8", transactionMode = SqlConfig.TransactionMode.ISOLATED))
    public void ensurePreLoadedTeamDataExists() {
        Optional<Team> panthers = teamRepo.findById("MI");
        Assertions.assertTrue(panthers.isPresent());
        Assertions.assertEquals("Jones", panthers.get().getCoach().getLastName());
        Assertions.assertEquals("Becken", panthers.get().getGm().getLastName());
    }

    @Test
    @Sql(scripts = {"/preloaded-data-tests.sql"},
            config = @SqlConfig(encoding = "utf-8", transactionMode = SqlConfig.TransactionMode.ISOLATED))
    public void ensureALLEntitiesAreLoaded() {
        Assertions.assertEquals(40, teamRepo.count());
        Assertions.assertEquals(40, coachRepo.count());
        Assertions.assertEquals(40, gmRepo.count());
        Assertions.assertEquals(4, conferenceRepo.count());
    }


}
