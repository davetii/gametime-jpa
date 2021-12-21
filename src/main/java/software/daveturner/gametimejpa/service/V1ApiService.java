package software.daveturner.gametimejpa.service;

import software.daveturner.gametimejpa.domain.Conference;
import software.daveturner.gametimejpa.domain.Player;
import software.daveturner.gametimejpa.domain.Team;

import java.util.List;
import java.util.Optional;


public interface V1ApiService {
    List<Conference> getLeague();
    Optional<Conference> getConference(String conferenceId);
    Optional<Team> getTeam(String teamId);
    Optional<Player> getPlayer(String playerId);
}
