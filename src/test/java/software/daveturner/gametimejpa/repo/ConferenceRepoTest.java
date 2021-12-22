package software.daveturner.gametimejpa.repo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import software.daveturner.gametimejpa.entity.ConferenceEntity;
import software.daveturner.gametimejpa.entity.TeamEntity;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static software.daveturner.gametimejpa.repo.RepoTestHelper.TEST_CONFERENCE_ID;
import static software.daveturner.gametimejpa.repo.RepoTestHelper.TEST_CONFERENCE_NAME;

@SpringBootTest
public class ConferenceRepoTest extends BaseJPATest {

    private ConferenceEntity newConference;
    TeamEntity team1;
    TeamEntity team2;


    @BeforeEach
    public void setup() {
        newConference = new ConferenceEntity();
        newConference.setId(TEST_CONFERENCE_ID);
        newConference.setName(TEST_CONFERENCE_NAME);
        conferenceRepo.save(newConference);
    }

    @Test
    public void ensureConferenceRepoInsertUpdateRead() {
        List<ConferenceEntity> list = helper.findAll(conferenceRepo);
        assertEquals(conferenceRepo.findById(TEST_CONFERENCE_ID).get().getName(), TEST_CONFERENCE_NAME);
    }

    @Test
    @Transactional
    public void ensureConferenceIsAddedToTeam() {
        Assertions.assertNotNull(conferenceRepo.findById(TEST_CONFERENCE_ID));

        team1 = helper.newTeam("MI", "Michigan","Panthers", newConference);
        teamRepo.save(team1);

        newConference.getTeams().add(team1);
        conferenceRepo.save(newConference);

        assertConferenceTeams(1, team1);
    }

    private void assertConferenceTeams(int conferenceSize, TeamEntity teamToFind) {
        assertNotNull(conferenceRepo.findById(TEST_CONFERENCE_ID));
        assertEquals(conferenceSize, conferenceRepo.findById(TEST_CONFERENCE_ID).get().getTeams().size());
        assertTrue(conferenceRepo.findById(TEST_CONFERENCE_ID).get().getTeams().contains(teamToFind));
    }

    @Test
    @Transactional
    public void ensureConferenceWithTeamsReturnsAll() {
        team1 = helper.newTeam("MI", "Michigan","Panthers", newConference);
        team2 = helper.newTeam("CHI", "Chicago","Blackhawks", newConference);
        teamRepo.save(team1);
        teamRepo.save(team2);
        newConference.getTeams().add(team1);
        newConference.getTeams().add(team2);
        conferenceRepo.save(newConference);
        assertConferenceTeams(2, team2);
    }

}
