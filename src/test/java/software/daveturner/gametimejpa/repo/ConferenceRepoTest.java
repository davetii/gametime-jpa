package software.daveturner.gametimejpa.repo;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import software.daveturner.gametimejpa.GametimeJpaApplication;
import software.daveturner.gametimejpa.domain.Conference;
import software.daveturner.gametimejpa.domain.Team;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = GametimeJpaApplication.class)
public class ConferenceRepoTest {

    @Autowired
    ConferenceRepo conferenceRepo;

    @Autowired
    TeamRepo teamRepo;

    RepoTestHelper helper = new RepoTestHelper();

    private final String conferenceId = "bob";
    private final String conferenceName = "bobby";
    private Conference newConference;
    Team team1;
    Team team2;


    @BeforeEach
    public void setup() {
        newConference = new Conference();
        newConference.setId(conferenceId);
        newConference.setName(conferenceName);
        conferenceRepo.save(newConference);
    }

    @AfterEach
    public void cleanup() {
        teamRepo.deleteAll();
        conferenceRepo.deleteAll();
    }

    @Test
    public void ensureConferenceRepoInsertUpdateRead() {
        List<Conference> list = helper.findAll(conferenceRepo);
        assertEquals(list.get(0).getId(), conferenceId);
        assertEquals(conferenceRepo.findById(conferenceId).get().getName(), conferenceName);
    }

    @Test
    public void ensureConferenceIsAddedToTeam() {
        Assertions.assertNotNull(conferenceRepo.findById(conferenceId));

        team1 = helper.newTeam("MI", "Michigan","Panthers", newConference);
        teamRepo.save(team1);

        newConference.getTeams().add(team1);
        conferenceRepo.save(newConference);

        assertConferenceTeams(1, team1);
    }

    private void assertConferenceTeams(int conferenceSize, Team teamToFind) {
        assertNotNull(conferenceRepo.findById(conferenceId));
        assertEquals(conferenceSize, conferenceRepo.findById(conferenceId).get().getTeams().size());
        assertTrue(conferenceRepo.findById(conferenceId).get().getTeams().contains(teamToFind));
    }

    @Test
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
