package software.daveturner.gametimejpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.daveturner.gametimejpa.domain.Conference;
import software.daveturner.gametimejpa.domain.Player;
import software.daveturner.gametimejpa.domain.Team;
import software.daveturner.gametimejpa.repo.ConferenceRepo;
import software.daveturner.gametimejpa.repo.PlayerRepo;
import software.daveturner.gametimejpa.repo.TeamRepo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static java.lang.Long.valueOf;

@Service
public class V1ApiServiceImpl implements V1ApiService {

    @Autowired
    ConferenceRepo conferenceRepo;

    @Autowired
    TeamRepo teamRepo;

    @Autowired
    PlayerRepo playerRepo;

    @Override
    public List<Conference> getLeague() {
        return StreamSupport
                .stream(conferenceRepo.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Conference> getConference(String id) {
        return conferenceRepo.findById(id);
    }

    @Override
    public Optional<Team> getTeam(String teamId) {
        return teamRepo.findById(teamId);
    }

    @Override
    public Optional<Player> getPlayer(String playerId) {
        return playerRepo.findById(valueOf(playerId));
    }
}
