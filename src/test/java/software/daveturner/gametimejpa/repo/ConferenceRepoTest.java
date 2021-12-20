package software.daveturner.gametimejpa.repo;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import software.daveturner.gametimejpa.GametimeJpaApplication;
import software.daveturner.gametimejpa.domain.Conference;
import software.daveturner.gametimejpa.domain.Team;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static software.daveturner.gametimejpa.repo.RepoTestHelper.TEST_CONFERENCE_ID;
import static software.daveturner.gametimejpa.repo.RepoTestHelper.TEST_CONFERENCE_NAME;

@SpringBootTest(classes = GametimeJpaApplication.class)
@Transactional // needed to avoid dumbass LazyLoad JPA/Hibernate issue
public class ConferenceRepoTest {

    @Autowired
    ConferenceRepo conferenceRepo;

    @Autowired
    TeamRepo teamRepo;

    RepoTestHelper helper = new RepoTestHelper();


    private Conference newConference;
    Team team1;
    Team team2;


    @BeforeEach
    public void setup() {
        newConference = new Conference();
        newConference.setId(TEST_CONFERENCE_ID);
        newConference.setName(TEST_CONFERENCE_NAME);
        conferenceRepo.save(newConference);
    }

    @AfterEach
    public void cleanup() {
        helper.cleanupAllRepos();
        teamRepo.deleteAll();
        conferenceRepo.deleteAll();
    }

    @Test
    @Transactional
    public void ensureConferenceRepoInsertUpdateRead() {
        List<Conference> list = helper.findAll(conferenceRepo);
        assertEquals(list.get(0).getId(), TEST_CONFERENCE_ID);
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

    private void assertConferenceTeams(int conferenceSize, Team teamToFind) {
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
