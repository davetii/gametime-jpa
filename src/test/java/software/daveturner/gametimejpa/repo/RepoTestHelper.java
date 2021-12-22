package software.daveturner.gametimejpa.repo;

import org.springframework.data.repository.CrudRepository;
import software.daveturner.gametimejpa.entity.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class RepoTestHelper {

    public static final String TEST_CONFERENCE_ID = "bob";
    public static final String TEST_CONFERENCE_NAME = "bobby";

    public CoachEntity newCoach(String firstName, String lastName) {
        CoachEntity coach = new CoachEntity();
        coach.setFirstName(firstName);
        coach.setLastName(lastName);
        return coach;
    }

    public CoachEntity newCoach(Long l, String firstName, String lastName, TeamEntity t) {
        CoachEntity coach = new CoachEntity();
        coach.setId(l);
        coach.setFirstName(firstName);
        coach.setLastName(lastName);
        coach.setTeam(t);
        return coach;
    }

    public PlayerEntity newPlayer(String firstName, String lastName) {
        PlayerEntity player = new PlayerEntity();
        player.setFirstName(firstName);
        player.setLastName(lastName);
        return player;
    }

    public List findAll(CrudRepository<?, ?> repo) {
        return StreamSupport
                .stream(repo.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public TeamEntity newTeam(String id, String locale, String name) {
        TeamEntity team = new TeamEntity();
        team.setId(id);
        team.setLocale(locale);
        team.setName(name);
        return team;
    }

    public TeamEntity newTeam(String id, String locale, String name, ConferenceEntity conf) {
        TeamEntity team = new TeamEntity();
        team.setId(id);
        team.setLocale(locale);
        team.setName(name);
        team.setConference(conf);
        return team;
    }

    public GMEntity newGM(String firstName, String lastName) {
        GMEntity gm = new GMEntity();
        gm.setFirstName(firstName);
        gm.setLastName(lastName);
        return gm;
    }
}
