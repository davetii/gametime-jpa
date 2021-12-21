package software.daveturner.gametimejpa.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.transaction.annotation.Transactional;
import software.daveturner.gametimejpa.GametimeJpaApplication;
import software.daveturner.gametimejpa.domain.Conference;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = GametimeJpaApplication.class)
public class V1ApiServiceImplTest {

    @Autowired
    V1ApiServiceImpl service;


    @Test
    @Sql(scripts = {"/preloaded-data-tests.sql"},
            config = @SqlConfig(encoding = "utf-8", transactionMode = SqlConfig.TransactionMode.ISOLATED))
    public void ensureALLEntitiesAreLoaded() {
        List<Conference> league = service.getLeague();
        for(Conference c: league) {
            System.out.println(c.getId());
            System.out.println(c.getName());
            System.out.println("teams: " + c.getTeams().size());
        }
        assertEquals(4, service.getLeague().size());
        assertTrue(service.getConference("NORTH").isPresent());
    }
}
