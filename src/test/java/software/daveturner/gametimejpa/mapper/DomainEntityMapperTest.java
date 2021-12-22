package software.daveturner.gametimejpa.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import software.daveturner.gametimejpa.domain.*;
import software.daveturner.gametimejpa.entity.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class DomainEntityMapperTest {

    public static final String GMFIRSTNAME = "gmfirstname";
    private static final String PLAYERFIRSTNAME = "TEST_FIRST_NAME";
    private static final String PLAYERLASTNAME = "TEST_LAST_NAME";
    private static final long TESTID = 999l;
    private static final String TEST_NAME = "TEST_NAME";
    private static final String TEST_ID_STRING = "TEST_ID_STRING";
    private static final String TEST_LOCALE = "TEST_LOCALE";
    private static final Integer TESTATTRIB = 10;
    private static final String CONFERENCEID = "TEST_CONFERENCE_ID";
    private static final String CONFERENCENAME = "TEST_CONFERENCE_NAME";
    public static final String COACHFIRSTNAME = "coachfirstname";
    public static final String COACHLASTNAME = "coachlastname";
    public static final String GMLASTNAME = "gmlastname";
    public static final String TEAMID = "TEAMID";
    public static final String TEAMLOCALE = "TEAM_LOCALE";
    public static final String TEAMNAME = "TEAM_NAME";


    DomainEntityMapper mapper;

    @BeforeEach
    public void setup() {
        mapper = new DomainEntityMapper();
    }

    @Test
    public void entityToTeamInfoWithNullValuesReturnsExpected() {
        TeamInfo teamInfo = mapper.entityToTeamInfo(testTeamEntity());
        assertTeam(teamInfo);
    }

    @Test
    public void entityToTeamInfoReturnsExpected() {
        TeamEntity teamEntity  = testTeamEntity();
        teamEntity.setConference(testConferenceEntity());
        teamEntity.setCoach(testCoachEntity());
        teamEntity.setGm(testGMEntity());

        teamEntity.getPlayers().add(testPLayerEntity());
        TeamInfo teamInfo = mapper.entityToTeamInfo(teamEntity);

        assertTeam(teamInfo);
        assertEquals(CONFERENCEID, teamInfo.getConference().getId());
        assertEquals(CONFERENCENAME, teamInfo.getConference().getName());
        assertEquals(COACHFIRSTNAME, teamInfo.getTeam().getCoach().getFirstName());
        assertEquals(COACHLASTNAME, teamInfo.getTeam().getCoach().getLastName());
        assertEquals(GMFIRSTNAME, teamInfo.getTeam().getGm().getFirstName());
        assertEquals(GMLASTNAME, teamInfo.getTeam().getGm().getLastName());
        assertEquals(1, teamInfo.getPlayers().size());
        assertEquals(true, teamInfo.getPlayers().stream().findFirst().isPresent());
        assertEquals(PLAYERFIRSTNAME, teamInfo.getPlayers().stream().findFirst().get().getFirstName());
        assertEquals(PLAYERLASTNAME, teamInfo.getPlayers().stream().findFirst().get().getLastName());

    }

    private void assertTeam(TeamInfo teamInfo) {
        assertEquals(TEAMLOCALE, teamInfo.getTeam().getLocale());
        assertEquals(TEAMNAME, teamInfo.getTeam().getName());
        assertEquals(TEAMID, teamInfo.getTeam().getId());
    }

    @Test
    public void ensureEntityToCoachReturnsExpected() {
        Coach coach = mapper.entityToCoach(testCoachEntity());
        assertEquals(COACHFIRSTNAME, coach.getFirstName());
        assertEquals(COACHLASTNAME, coach.getLastName());
        assertEquals(TESTID, coach.getId());
    }

    @Test
    public void ensureEntityToGMReturnsExpected() {
        GMEntity e = new GMEntity();
        e.setFirstName(PLAYERFIRSTNAME);
        e.setLastName(PLAYERLASTNAME);
        e.setId(TESTID);
        GM gm = mapper.entityToGm(e);
        assertEquals(PLAYERFIRSTNAME, gm.getFirstName());
        assertEquals(PLAYERLASTNAME, gm.getLastName());
        assertEquals(TESTID, gm.getId());
    }

    @Test
    public void ensureEntityToConferenceReturnsExpected() {
        ConferenceEntity e = new ConferenceEntity();
        e.setName(TEST_NAME);
        e.setId(TEST_ID_STRING);
        Conference conference = mapper.entityToConference(e);
        assertEquals(TEST_NAME, conference.getName());
        assertEquals(TEST_ID_STRING, conference.getId());
    }

    @Test
    public void ensureEntityToTeamReturnsExpectedWithNullGM() {
        TeamEntity entity = testTeamEntity();
        entity.setCoach(testCoachEntity());
        Team team = mapper.entityToTeam(entity);
        assertEquals(TEAMNAME, team.getName());
        assertEquals(TEAMID, team.getId());
        assertEquals(mapper.entityToCoach(testCoachEntity()).getId(), team.getCoach().getId());
        assertEquals(mapper.entityToCoach(testCoachEntity()).getFirstName(), team.getCoach().getFirstName());
        assertEquals(mapper.entityToCoach(testCoachEntity()).getLastName(), team.getCoach().getLastName());
    }

    @Test
    public void ensureEntityToTeamReturnsExpectedWithNullCoach() {
        TeamEntity entity = testTeamEntity();
        entity.setGm(testGMEntity());
        Team team = mapper.entityToTeam(entity);
        assertEquals(TEAMNAME, team.getName());
        assertEquals(TEAMID, team.getId());
        assertEquals(mapper.entityToGm(testGMEntity()).getId(), team.getGm().getId());
        assertEquals(mapper.entityToGm(testGMEntity()).getFirstName(), team.getGm().getFirstName());
        assertEquals(mapper.entityToGm(testGMEntity()).getLastName(), team.getGm().getLastName());
    }



    @Test
    public void ensureEntityToPlayerReturnsExpected() {
        assertPLayer(mapper.mapEntityToPlayer(testPLayerEntity()));
    }

    private void assertPLayer(Player player) {
        assertEquals(TESTATTRIB, player.getAthleticism());
        assertEquals(TESTATTRIB, player.getCharisma());
        assertEquals(TESTATTRIB, player.getCohesion());
        assertEquals(TESTATTRIB, player.getDetermination());
        assertEquals(TESTATTRIB, player.getEgo());
        assertEquals(TESTATTRIB, player.getEndurance());
        assertEquals(TESTATTRIB, player.getEgo());
        assertEquals(TESTATTRIB, player.getHandle());
        assertEquals(TESTATTRIB, player.getHealth());
        assertEquals(TESTATTRIB, player.getIntelligence());
        assertEquals(TESTATTRIB, player.getLuck());
        assertEquals(TESTATTRIB, player.getShotSelection());
        assertEquals(TESTATTRIB, player.getShotSkill());
        assertEquals(TESTATTRIB, player.getSize());
        assertEquals(TESTATTRIB, player.getStrength());
        assertEquals(TESTATTRIB, player.getSpeed());

        assertEquals(Role.STARTER, player.getRole());
        assertEquals(Position.PG, player.getPosition());

        assertEquals(PLAYERLASTNAME, player.getLastName());
        assertEquals(PLAYERFIRSTNAME, player.getFirstName());
        assertEquals(TESTID, player.getId());

    }

    @Test
    public void ensureEntityToPlayerInfoHandlesNull() {
        PlayerInfo playerInfo = mapper.entityToPlayerInfo(testPLayerEntity());
        assertEquals(PLAYERLASTNAME, playerInfo.getPlayer().getLastName());
        assertEquals(PLAYERFIRSTNAME, playerInfo.getPlayer().getFirstName());
        assertEquals(TESTID, playerInfo.getPlayer().getId());
        assertNull(playerInfo.getConference());
    }

    @Test
    public void ensureEntityToPlayerInfoReturnsExpected() {
        PlayerEntity playerEntity = testPLayerEntity();
        TeamEntity teamEntity = testTeamEntity();
        teamEntity.setConference(testConferenceEntity());
        teamEntity.setCoach(testCoachEntity());
        teamEntity.setGm(testGMEntity());
        playerEntity.setTeam(teamEntity);
        PlayerInfo playerInfo = mapper.entityToPlayerInfo(playerEntity);
        assertPLayer(playerInfo.getPlayer());
        assertEquals(COACHLASTNAME, playerInfo.getTeam().getCoach().getLastName());
        assertEquals(COACHFIRSTNAME, playerInfo.getTeam().getCoach().getFirstName());
        assertEquals(GMLASTNAME, playerInfo.getTeam().getGm().getLastName());
        assertEquals(GMFIRSTNAME, playerInfo.getTeam().getGm().getFirstName());
        assertEquals(CONFERENCENAME, playerInfo.getConference().getName());
        assertEquals(CONFERENCEID, playerInfo.getConference().getId());
        assertEquals(TEAMID, playerInfo.getTeam().getId());
        assertEquals(TEAMNAME, playerInfo.getTeam().getName());
        assertEquals(TEAMLOCALE, playerInfo.getTeam().getLocale());
    }

    private CoachEntity testCoachEntity() {
        CoachEntity entity = new CoachEntity();
        entity.setId(TESTID);
        entity.setFirstName(COACHFIRSTNAME);
        entity.setLastName(COACHLASTNAME);
        return entity;
    }

    private GMEntity testGMEntity() {
        GMEntity entity = new GMEntity();
        entity.setId(TESTID);
        entity.setFirstName(GMFIRSTNAME);
        entity.setLastName(GMLASTNAME);
        return entity;
    }

    private PlayerEntity testPLayerEntity() {
        PlayerEntity playerEntity = new PlayerEntity();
        playerEntity.setRole(Role.STARTER);
        playerEntity.setPosition(Position.PG);
        playerEntity.setLastName(PLAYERLASTNAME);
        playerEntity.setFirstName(PLAYERFIRSTNAME);
        playerEntity.setId(TESTID);
        //playerEntity.setHeight(e.getHeight());
        //playerEntity.setWeight(e.getWeight());
        //playerEntity.setOrigin(e.getOrigin());
        //playerEntity.setOriginDetails(e.getOriginDetails());
        playerEntity.setAthleticism(TESTATTRIB);
        playerEntity.setCharisma(TESTATTRIB);
        playerEntity.setCohesion(TESTATTRIB);
        playerEntity.setDetermination(TESTATTRIB);
        playerEntity.setEgo(TESTATTRIB);
        playerEntity.setEndurance(TESTATTRIB);
        playerEntity.setEgo(TESTATTRIB);
        playerEntity.setHandle(TESTATTRIB);
        playerEntity.setHealth(TESTATTRIB);
        playerEntity.setIntelligence(TESTATTRIB);
        playerEntity.setLuck(TESTATTRIB);
        playerEntity.setShotSelection(TESTATTRIB);
        playerEntity.setShotSkill(TESTATTRIB);
        playerEntity.setSize(TESTATTRIB);
        playerEntity.setStrength(TESTATTRIB);
        playerEntity.setSpeed(TESTATTRIB);
        return playerEntity;
    }

    public ConferenceEntity testConferenceEntity() {
        ConferenceEntity entity = new ConferenceEntity();
        entity.setId(CONFERENCEID);
        entity.setName(CONFERENCENAME);
        return entity;
    }

    private TeamEntity testTeamEntity() {
        TeamEntity teamEntity  = new TeamEntity();
        teamEntity.setId(TEAMID);
        teamEntity.setLocale(TEAMLOCALE);
        teamEntity.setName(TEAMNAME);
        return teamEntity;

    }


}


