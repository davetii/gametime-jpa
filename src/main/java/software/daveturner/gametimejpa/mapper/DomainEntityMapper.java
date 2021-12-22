package software.daveturner.gametimejpa.mapper;

import org.springframework.stereotype.Component;
import software.daveturner.gametimejpa.domain.*;
import software.daveturner.gametimejpa.entity.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class DomainEntityMapper {

    public Set<ConferenceInfo> mapLeague(List<ConferenceEntity> entities) {
        Set<ConferenceInfo> set = new HashSet<>();
        for (ConferenceEntity entity: entities) {
            set.add(entityToConferenceInfo(entity));
        }
        return set;
    }

    public ConferenceInfo entityToConferenceInfo(ConferenceEntity entity) {
        ConferenceInfo conferenceInfo = new ConferenceInfo();
        conferenceInfo.setConference(entityToConference(entity));
        for(TeamEntity teamEntity : entity.getTeams()) {
            TeamInfo teamInfo =entityToTeamInfo(teamEntity);
            conferenceInfo.getTeams().add(teamInfo);
        }
        return conferenceInfo;
    }

    Conference entityToConference(ConferenceEntity entity) {
        if(entity == null) { return new Conference(); }
        Conference conference = new Conference();
        conference.setName(entity.getName());
        conference.setId(entity.getId());
        return conference;
    }

    void mapEntityPlayerList(TeamInfo teamInfo, Set<PlayerEntity> entities) {

        entities.stream().forEach( e -> {
            Player player =mapEntityToPlayer(e);
            teamInfo.getPlayers().add(player);
        });
    }

    Player mapEntityToPlayer(PlayerEntity e) {
        Player player = new Player();
        player.setRole(e.getRole());
        player.setPosition(e.getPosition());
        player.setLastName(e.getLastName());
        player.setFirstName(e.getFirstName());
        player.setId(e.getId());
        player.setHeight(e.getHeight());
        player.setWeight(e.getWeight());
        player.setOrigin(e.getOrigin());
        player.setOriginDetails(e.getOriginDetails());
        player.setAthleticism(e.getAthleticism());
        player.setCharisma(e.getCharisma());
        player.setCohesion(e.getCohesion());
        player.setDetermination(e.getDetermination());
        player.setEgo(e.getEgo());
        player.setEndurance(e.getEndurance());
        player.setEgo(e.getEgo());
        player.setHandle(e.getHandle());
        player.setHealth(e.getHealth());
        player.setIntelligence(e.getIntelligence());
        player.setLuck(e.getLuck());
        player.setShotSelection(e.getShotSelection());
        player.setShotSkill(e.getShotSkill());
        player.setSize(e.getSize());
        player.setStrength(e.getStrength());
        player.setSpeed(e.getSpeed());
        player.setSkills(new PlayerSkills(player));
        return player;
    }

    public TeamInfo entityToTeamInfo(TeamEntity teamEntity) {
        TeamInfo teaminfo = new TeamInfo();
        teaminfo.setTeam(entityToTeam(teamEntity));
        teaminfo.setConference(entityToConference(teamEntity.getConference()));
        mapEntityPlayerList(teaminfo, teamEntity.getPlayers());
        return teaminfo;
    }

    Team entityToTeam(TeamEntity teamEntity) {
        if(teamEntity == null) { return new Team(); }
        Team team = new Team();
        if(teamEntity.getCoach() != null) {
            team.setCoach(entityToCoach(teamEntity.getCoach()));
        }

        if(teamEntity.getGm() != null) {
            team.setGm(entityToGm(teamEntity.getGm()));
        }
        team.setLocale(teamEntity.getLocale());
        team.setName(teamEntity.getName());
        team.setId(teamEntity.getId());
        return team;
    }

    GM entityToGm(GMEntity gmEntity) {
        if(gmEntity == null) { return new GM();}
        GM gm = new GM();
        gm.setLastName(gmEntity.getLastName());
        gm.setFirstName(gmEntity.getFirstName());
        gm.setId(gmEntity.getId());
        return gm;
    }

    Coach entityToCoach(CoachEntity coachEntity) {
        if(coachEntity == null) { return new Coach();}
        Coach coach = new Coach();
        coach.setLastName(coachEntity.getLastName());
        coach.setFirstName(coachEntity.getFirstName());
        coach.setId(coachEntity.getId());
        return coach;
    }

    public PlayerInfo entityToPlayerInfo(PlayerEntity entity) {

        PlayerInfo playerInfo = new PlayerInfo();
        playerInfo.setPlayer(mapEntityToPlayer(entity));
        if(entity.getTeam() !=  null) {
            playerInfo.setConference(entityToConference(entity.getTeam().getConference()));
        }
        playerInfo.setTeam(entityToTeam(entity.getTeam()));
        return playerInfo;

    }
}
