package software.daveturner.gametimejpa.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import software.daveturner.gametimejpa.domain.ConferenceInfo;
import software.daveturner.gametimejpa.domain.TeamInfo;
import software.daveturner.gametimejpa.repo.BaseJPATest;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class V1ApiControllerTest extends BaseJPATest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @Sql(scripts = {"/preloaded-data-tests.sql"},
            config = @SqlConfig(encoding = "utf-8", transactionMode = SqlConfig.TransactionMode.ISOLATED))
    public void ensureGetLeagueReturnsExpected() {
        ConferenceInfo[] response = this.restTemplate.getForObject("http://localhost:" + port + "/api/v1/gametime/league", ConferenceInfo[].class);
        assertEquals(4, response.length, "League Has 4 Conferences");
        assertEquals(10, response[0].getTeams().size(), "Conference 1 has 10 teams");
        assertEquals(10, response[1].getTeams().size(), "Conference 2 has 10 teams");
        assertEquals(10, response[2].getTeams().size(), "Conference 3 has 10 teams");
        assertEquals(10, response[3].getTeams().size(), "Conference 4 has 10 teams");
        AtomicInteger totalPlayers = new AtomicInteger();
        asList(response).forEach( e -> e.getTeams().forEach(t -> totalPlayers.addAndGet(t.getPlayers().size())));
        assertEquals(558, totalPlayers.get(), "Count total number of players");
    }

    @Test
    @Sql(scripts = {"/preloaded-data-tests.sql"},
            config = @SqlConfig(encoding = "utf-8", transactionMode = SqlConfig.TransactionMode.ISOLATED))
    public void ensureGetConferenceReturnsExpected() {
        ConferenceInfo response = this.restTemplate.getForObject("http://localhost:" + port + "/api/v1/gametime/conference/EAST", ConferenceInfo.class);
        assertEquals(10,response.getTeams().size(), "Conference has 10 teams");
    }

    @Test
    @Sql(scripts = {"/preloaded-data-tests.sql"},
            config = @SqlConfig(encoding = "utf-8", transactionMode = SqlConfig.TransactionMode.ISOLATED))
    public void ensureNotFoundConferenceReturnsExpected() {
        ResponseEntity<String> response = this.restTemplate.getForEntity("http://localhost:" + port + "/api/v1/gametime/conference/BOB", String.class);
        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND, "bad conference returns not found");
    }

    @Test
    @Sql(scripts = {"/preloaded-data-tests.sql"},
            config = @SqlConfig(encoding = "utf-8", transactionMode = SqlConfig.TransactionMode.ISOLATED))
    public void ensureFoundConferenceReturnsExpected() {
        ResponseEntity<String> response = this.restTemplate.getForEntity("http://localhost:" + port + "/api/v1/gametime/conference/EAST", String.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK, "eastern conference returns not found");
    }

    @Test
    @Sql(scripts = {"/preloaded-data-tests.sql"},
            config = @SqlConfig(encoding = "utf-8", transactionMode = SqlConfig.TransactionMode.ISOLATED))
    public void ensureFoundTeamReturns200() {
        ResponseEntity<String> response = this.restTemplate.getForEntity("http://localhost:" + port + "/api/v1/gametime/team/NY", String.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK, "found team retruns expected status code");
    }

    @Test
    @Sql(scripts = {"/preloaded-data-tests.sql"},
            config = @SqlConfig(encoding = "utf-8", transactionMode = SqlConfig.TransactionMode.ISOLATED))
    public void ensureNotFoundTeamReturns404() {
        ResponseEntity<String> response = this.restTemplate.getForEntity("http://localhost:" + port + "/api/v1/gametime/team/BOB", String.class);
        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND, "NOT found team retruns expected status code");
    }

    @Test
    @Sql(scripts = {"/preloaded-data-tests.sql"},
            config = @SqlConfig(encoding = "utf-8", transactionMode = SqlConfig.TransactionMode.ISOLATED))
    public void ensureFoundTeamReturnsExpectedValues() {
        TeamInfo response = this.restTemplate.getForObject("http://localhost:" + port + "/api/v1/gametime/team/NY", TeamInfo.class);
        assertEquals(response.getTeam().getId(), "NY");
        assertEquals(response.getTeam().getLocale(), "New York");
        assertEquals(response.getTeam().getName(), "Fastbacks");
        assertEquals(response.getTeam().getCoach().getId(), 2L);
        assertEquals(response.getTeam().getCoach().getFirstName(), "Frank");
        assertEquals(response.getTeam().getCoach().getLastName(), "Valcone");
        assertEquals(response.getTeam().getGm().getId(), 2L);
        assertEquals(response.getTeam().getGm().getFirstName(), "JD");
        assertEquals(response.getTeam().getGm().getLastName(), "Davison");
        assertEquals(response.getConference().getId(), "EAST");
        assertEquals(response.getPlayers().size(), 12);

    }

}

