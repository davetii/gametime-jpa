package software.daveturner.gametimejpa.repo;


import org.junit.jupiter.api.*;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import software.daveturner.gametimejpa.entity.CoachEntity;
import software.daveturner.gametimejpa.entity.GMEntity;
import software.daveturner.gametimejpa.entity.PlayerEntity;
import software.daveturner.gametimejpa.entity.TeamEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static software.daveturner.gametimejpa.domain.Position.PG;
import static software.daveturner.gametimejpa.domain.Role.STARTER;


public class PreLoadedDataTest extends BaseJPATest{
    @Test
    @Sql(scripts = {"/preloaded-data-tests.sql"},
            config = @SqlConfig(encoding = "utf-8", transactionMode = SqlConfig.TransactionMode.ISOLATED))
    public void ensurePreLoadedCoachDataExists() {

        Optional<CoachEntity> frankValcone = coachRepo.findById(2L);
        assertTrue(frankValcone.isPresent());
        assertEquals("Fastbacks", frankValcone.get().getTeam().getName());
    }

    @Test
    @Sql(scripts = {"/preloaded-data-tests.sql"},
            config = @SqlConfig(encoding = "utf-8", transactionMode = SqlConfig.TransactionMode.ISOLATED))
    public void ensurePreLoadedGMDataExists() {
        Optional<GMEntity> donSchmidt = gmRepo.findById(10L);
        assertTrue(donSchmidt.isPresent());
        assertEquals("Gators", donSchmidt.get().getTeam().getName());
    }

    @Test
    @Sql(scripts = {"/preloaded-data-tests.sql"},
            config = @SqlConfig(encoding = "utf-8", transactionMode = SqlConfig.TransactionMode.ISOLATED))
    public void ensurePreLoadedTeamDataExists() {
        Optional<TeamEntity> panthers = teamRepo.findById("MI");
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


    @Test
    @Sql(scripts = {"/preloaded-data-tests.sql"},
            config = @SqlConfig(encoding = "utf-8", transactionMode = SqlConfig.TransactionMode.ISOLATED))
    public void ensureTeamReturnsExpectedPlayers() {
        assertEquals(13, teamRepo.findById("MI").get().getPlayers().size());
        assertTrue(teamRepo.findById("MI").get().getPlayers().contains(playerRepo.findById(999l).get()));
    }

    @Test
    @Sql(scripts = {"/preloaded-data-tests.sql"},
            config = @SqlConfig(encoding = "utf-8", transactionMode = SqlConfig.TransactionMode.ISOLATED))
    public void ensurePlayerReturnsExpected() {
        PlayerEntity tonyHawk = playerRepo.findById(999L).get();
        assertEquals(tonyHawk.getPosition(), PG);
        assertEquals(tonyHawk.getRole(), STARTER);
    }
}
