package software.daveturner.gametimejpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.daveturner.gametimejpa.domain.Conference;
import software.daveturner.gametimejpa.domain.ConferenceInfo;
import software.daveturner.gametimejpa.domain.PlayerInfo;
import software.daveturner.gametimejpa.domain.TeamInfo;
import software.daveturner.gametimejpa.entity.ConferenceEntity;
import software.daveturner.gametimejpa.entity.PlayerEntity;
import software.daveturner.gametimejpa.entity.TeamEntity;
import software.daveturner.gametimejpa.mapper.DomainEntityMapper;
import software.daveturner.gametimejpa.repo.ConferenceRepo;
import software.daveturner.gametimejpa.repo.PlayerRepo;
import software.daveturner.gametimejpa.repo.TeamRepo;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static java.lang.Long.valueOf;

@Service
public class V1ApiServiceImpl implements V1ApiService {

    private final ConferenceRepo conferenceRepo;
    private final TeamRepo teamRepo;
    private final PlayerRepo playerRepo;

    @Autowired
    private DomainEntityMapper mapper;

    public V1ApiServiceImpl(ConferenceRepo conferenceRepo, TeamRepo teamRepo, PlayerRepo playerRepo) {
        this.playerRepo = playerRepo;
        this.conferenceRepo = conferenceRepo;
        this.teamRepo = teamRepo;
    }

    @Override
    public Set<ConferenceInfo> getLeague() {
        List<ConferenceEntity> conferenceEntities = StreamSupport
                .stream(conferenceRepo.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return mapper.mapLeague(conferenceEntities);
    }

    @Override
    public Optional<ConferenceInfo> getConference(String id) {
        Optional<ConferenceEntity> entity = conferenceRepo.findById(id);
        if(entity.isPresent()) {
             return Optional.of(mapper.entityToConferenceInfo(entity.get()));
        }
        return Optional.empty();
    }

    @Override
    public Optional<TeamInfo> getTeam(String teamId) {
        Optional<TeamEntity> entity = teamRepo.findById(teamId);
        if(entity.isPresent()) {
            return Optional.of(mapper.entityToTeamInfo(entity.get()));
        }
        return Optional.empty();
    }

    @Override
    public Optional<PlayerInfo> getPlayer(String playerId) {
        Optional<PlayerEntity> entity = playerRepo.findById(valueOf(playerId));
        if(entity.isPresent()) {
            return Optional.of(mapper.entityToPlayerInfo(entity.get()));
        }
        return Optional.empty();
    }
}
