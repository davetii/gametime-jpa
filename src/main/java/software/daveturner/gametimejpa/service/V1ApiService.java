package software.daveturner.gametimejpa.service;

import software.daveturner.gametimejpa.domain.Conference;
import software.daveturner.gametimejpa.domain.ConferenceInfo;
import software.daveturner.gametimejpa.domain.PlayerInfo;
import software.daveturner.gametimejpa.domain.TeamInfo;
import software.daveturner.gametimejpa.entity.PlayerEntity;
import software.daveturner.gametimejpa.entity.TeamEntity;

import java.util.Optional;
import java.util.Set;


public interface V1ApiService {
    Set<ConferenceInfo> getLeague();
    Optional <ConferenceInfo> getConference(String conferenceId);
    Optional<TeamInfo> getTeam(String teamId);
    Optional<PlayerInfo> getPlayer(String playerId);
}
