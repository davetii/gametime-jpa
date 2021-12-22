package software.daveturner.gametimejpa.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import software.daveturner.gametimejpa.domain.ConferenceInfo;
import software.daveturner.gametimejpa.repo.BaseJPATest;

import java.util.*;
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
        asList(response).forEach( e -> e.getTeams().forEach(t -> {
            totalPlayers.addAndGet(t.getPlayers().size());
        }));
        assertEquals(558, totalPlayers.get(), "Count total number of players");
    }

}
