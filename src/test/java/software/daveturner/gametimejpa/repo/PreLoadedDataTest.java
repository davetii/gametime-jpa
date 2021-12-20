package software.daveturner.gametimejpa.repo;


import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import software.daveturner.gametimejpa.domain.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static software.daveturner.gametimejpa.domain.Position.PG;
import static software.daveturner.gametimejpa.domain.Role.STARTER;

@SpringBootTest
public class PreLoadedDataTest {


    @Autowired
    CoachRepo coachRepo;

    @Autowired
    TeamRepo teamRepo;

    @Autowired
    GMRepo gmRepo;

    @Autowired
    PlayerRepo playerRepo;

    @Autowired
    ConferenceRepo conferenceRepo;

    RepoTestHelper helper = new RepoTestHelper();

    @AfterEach
    public void cleanup() {
        playerRepo.deleteAll();
        teamRepo.deleteAll();
        gmRepo.deleteAll();
        coachRepo.deleteAll();
        conferenceRepo.deleteAll();
    }

    @Test
    @Sql(scripts = {"/preloaded-data-tests.sql"},
            config = @SqlConfig(encoding = "utf-8", transactionMode = SqlConfig.TransactionMode.ISOLATED))
    public void ensurePreLoadedCoachDataExists() {

        Optional<Coach> frankValcone = coachRepo.findById(2L);
        assertTrue(frankValcone.isPresent());
        assertEquals("Fastbacks", frankValcone.get().getTeam().getName());
    }

    @Test
    @Sql(scripts = {"/preloaded-data-tests.sql"},
            config = @SqlConfig(encoding = "utf-8", transactionMode = SqlConfig.TransactionMode.ISOLATED))
    public void ensurePreLoadedGMDataExists() {
        Optional<GM> donSchmidt = gmRepo.findById(10L);
        assertTrue(donSchmidt.isPresent());
        assertEquals("Gators", donSchmidt.get().getTeam().getName());
    }

    @Test
    @Sql(scripts = {"/preloaded-data-tests.sql"},
            config = @SqlConfig(encoding = "utf-8", transactionMode = SqlConfig.TransactionMode.ISOLATED))
    public void ensurePreLoadedTeamDataExists() {
        Optional<Team> panthers = teamRepo.findById("MI");
        assertTrue(panthers.isPresent());
        assertEquals("Jones", panthers.get().getCoach().getLastName());
        assertEquals("Becken", panthers.get().getGm().getLastName());
    }

    @Test
    @Sql(scripts = {"/preloaded-data-tests.sql"},
            config = @SqlConfig(encoding = "utf-8", transactionMode = SqlConfig.TransactionMode.ISOLATED))
    public void ensureALLEntitiesAreLoaded() {
        assertEquals(40, teamRepo.count());
        assertEquals(40, coachRepo.count());
        assertEquals(40, gmRepo.count());
        assertEquals(4, conferenceRepo.count());
        assertEquals(558, playerRepo.count());
    }

    /* not sure adding this tests breaks everything else
    @Test
    @Sql(scripts = {"/preloaded-data-tests.sql"},
            config = @SqlConfig(encoding = "utf-8", transactionMode = SqlConfig.TransactionMode.ISOLATED))
    public void ensureTeamReturnsExpectedPlayers() {
        assertEquals(13, teamRepo.findById("MI").get().getPlayers().size());
        assertTrue(teamRepo.findById("MI").get().getPlayers().contains(playerRepo.findById(999l).get()));
    }

     */

    @Test
    @Sql(scripts = {"/preloaded-data-tests.sql"},
            config = @SqlConfig(encoding = "utf-8", transactionMode = SqlConfig.TransactionMode.ISOLATED))
    public void ensurePlayerReturnsExpected() {
        Player tonyHawk = playerRepo.findById(999L).get();
        assertEquals(tonyHawk.getPosition(), PG);
        assertEquals(tonyHawk.getRole(), STARTER);

    }




}
