package software.daveturner.gametimejpa.service;

import org.springframework.stereotype.Service;
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
    private final DomainEntityMapper mapper;


    public V1ApiServiceImpl(ConferenceRepo conferenceRepo, TeamRepo teamRepo, PlayerRepo playerRepo, DomainEntityMapper mapper) {
        this.playerRepo = playerRepo;
        this.conferenceRepo = conferenceRepo;
        this.teamRepo = teamRepo;
        this.mapper = mapper;
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
        return entity.map(conferenceEntity -> mapper.entityToConferenceInfo(conferenceEntity));
    }

    @Override
    public Optional<TeamInfo> getTeam(String teamId) {
        Optional<TeamEntity> entity = teamRepo.findById(teamId);
        return entity.map(teamEntity -> mapper.entityToTeamInfo(teamEntity));
    }

    @Override
    public Optional<PlayerInfo> getPlayer(String playerId) {
        Optional<PlayerEntity> entity = playerRepo.findById(valueOf(playerId));
        return entity.map(playerEntity -> mapper.entityToPlayerInfo(playerEntity));
    }
}
