package software.daveturner.gametimejpa.repo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import software.daveturner.gametimejpa.GametimeJpaApplication;
import software.daveturner.gametimejpa.domain.Team;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@SpringBootTest(classes = GametimeJpaApplication.class)
public class TeamRepoTest {

    @Autowired
    private TeamRepo teamRepo;

    @Test
    public void ensureDataLoads() {
        Assertions.assertEquals(1, teamRepo.count());
        List<Team> list = StreamSupport
                .stream(teamRepo.findAll().spliterator(), false)
                .collect(Collectors.toList());
        Assertions.assertEquals("MI", list.get(0).getId());
    }
}
