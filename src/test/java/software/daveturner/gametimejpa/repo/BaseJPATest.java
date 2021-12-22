package software.daveturner.gametimejpa.repo;

import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BaseJPATest {

    @Autowired
    CoachRepo coachRepo;

    @Autowired
    GMRepo gmRepo;

    @Autowired
    TeamRepo teamRepo;

    @Autowired
    PlayerRepo playerRepo;

    @Autowired
    ConferenceRepo conferenceRepo;

    final RepoTestHelper helper = new RepoTestHelper();

    @AfterEach
    public void cleanup() {
        playerRepo.deleteAll();
        teamRepo.deleteAll();
        gmRepo.deleteAll();
        coachRepo.deleteAll();
        conferenceRepo.deleteAll();
    }


}
