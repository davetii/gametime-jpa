package software.daveturner.gametimejpa.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import software.daveturner.gametimejpa.GametimeJpaApplication;
import software.daveturner.gametimejpa.domain.Conference;
import software.daveturner.gametimejpa.domain.ConferenceInfo;
import software.daveturner.gametimejpa.domain.TeamInfo;
import software.daveturner.gametimejpa.repo.BaseJPATest;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class V1ApiServiceImplTest extends BaseJPATest {

    @Autowired
    V1ApiServiceImpl service;


    @Test
    @Sql(scripts = {"/preloaded-data-tests.sql"},
            config = @SqlConfig(encoding = "utf-8", transactionMode = SqlConfig.TransactionMode.ISOLATED))
    public void ensureALLEntitiesAreLoaded() {
        Set<ConferenceInfo> league = service.getLeague();
        assertEquals(4, service.getLeague().size());
    }

    @Test
    @Sql(scripts = {"/preloaded-data-tests.sql"},
            config = @SqlConfig(encoding = "utf-8", transactionMode = SqlConfig.TransactionMode.ISOLATED))
    public void ensureConferenceIsLoaded() {
        Optional<ConferenceInfo> conferenceInfo = service.getConference("EAST");
        assertEquals(10, conferenceInfo.get().getTeams().size());
        assertEquals("Eastern", conferenceInfo.get().getConference().getName());
        assertEquals("EAST", conferenceInfo.get().getConference().getId());
    }

    @Test
    @Sql(scripts = {"/preloaded-data-tests.sql"},
            config = @SqlConfig(encoding = "utf-8", transactionMode = SqlConfig.TransactionMode.ISOLATED))
    public void ensureConferenceNotFoundReturnsExpected() {
        Optional<ConferenceInfo> conferenceInfo = service.getConference("BBO");
        assertEquals(true, conferenceInfo.isEmpty());
    }

    @Test
    @Sql(scripts = {"/preloaded-data-tests.sql"},
            config = @SqlConfig(encoding = "utf-8", transactionMode = SqlConfig.TransactionMode.ISOLATED))
    public void ensureNotFoundTeamReturnsTrue() {
        Optional<TeamInfo> teamInfo = service.getTeam("BOB");
        assertEquals(true, teamInfo.isEmpty());
    }

    @Test
    @Sql(scripts = {"/preloaded-data-tests.sql"},
            config = @SqlConfig(encoding = "utf-8", transactionMode = SqlConfig.TransactionMode.ISOLATED))
    public void ensureTeamReturnsExpected() {
        Optional<TeamInfo> teamInfo = service.getTeam("MI");
        assertEquals(true, teamInfo.isPresent());
        assertEquals(13, teamInfo.get().getPlayers().size());
        assertEquals("Michigan", teamInfo.get().getTeam().getLocale());
        assertEquals("Panthers", teamInfo.get().getTeam().getName());
        assertEquals("NORTH", teamInfo.get().getConference().getId());
    }
}
