package software.daveturner.gametimejpa.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import software.daveturner.gametimejpa.domain.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class RepoTestHelper {


    @Autowired
    ConferenceRepo conferenceRepo;

    @Autowired
    PlayerRepo playerRepo;

    @Autowired
    TeamRepo teamRepo;

    @Autowired
    private CoachRepo coachRepo;

    @Autowired
    private GMRepo gmRepo;



    public static final String TEST_CONFERENCE_ID = "bob";
    public static final String TEST_CONFERENCE_NAME = "bobby";

    public Coach newCoach(String firstName, String lastName) {
        Coach coach = new Coach();
        coach.setFirstName(firstName);
        coach.setLastName(lastName);
        return coach;
    }

    public Coach newCoach(Long l, String firstName, String lastName, Team t) {
        Coach coach = new Coach();
        coach.setId(l);
        coach.setFirstName(firstName);
        coach.setLastName(lastName);
        coach.setTeam(t);
        return coach;
    }

    public Player newPlayer(String firstName, String lastName) {
        Player player = new Player();
        player.setFirstName(firstName);
        player.setLastName(lastName);
        return player;
    }

    public List findAll(CrudRepository<?, ?> repo) {
        return StreamSupport
                .stream(repo.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Team newTeam(String id, String locale, String name) {
        Team team = new Team();
        team.setId(id);
        team.setLocale(locale);
        team.setName(name);
        return team;
    }

    public Team newTeam(String id, String locale, String name, Conference conf) {
        Team team = new Team();
        team.setId(id);
        team.setLocale(locale);
        team.setName(name);
        team.setConference(conf);
        return team;
    }

    public GM newGM(String firstName, String lastName) {
        GM gm = new GM();
        gm.setFirstName(firstName);
        gm.setLastName(lastName);
        return gm;
    }

    public void cleanupAllRepos() {

        if(playerRepo != null ) {
           playerRepo.deleteAll();
        }

        if(teamRepo != null) {
            teamRepo.deleteAll();
        }

        if(coachRepo != null) {
            coachRepo.deleteAll();
        }

        if(gmRepo != null) {
            gmRepo.deleteAll();
        }

        if(conferenceRepo != null) {
            conferenceRepo.deleteAll();
        }






    }
}
