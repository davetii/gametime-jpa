package software.daveturner.gametimejpa.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import software.daveturner.gametimejpa.calc.*;
import software.daveturner.gametimejpa.domain.Player;
import software.daveturner.gametimejpa.domain.PlayerSkills;

@Service
public class SkillMapper {

    @Autowired
    private AcumenSkillCalculator acumenSkillCalculator;

    @Autowired
    private BallSecuritySkillCalculator ballSecuritySkillCalculator;

    @Autowired
    private DefenseReboundSkillCalculator defenseReboundSkillCalculator;

    @Autowired
    private DriveSkillCalculator driveSkillCalculator;

    @Autowired
    private FreeThrowSkillCalculator freeThrowSkillCalculator;

    @Autowired
    private IndividualDefenseSkillCalculator individualDefenseSkillCalculator;

    @Autowired
    private LongRangeSkillCalculator longRangeSkillCalculator;

    @Autowired
    private OffenseReboundSkillCalculator offenseReboundSkillCalculator;

    @Autowired
    private PassingSkillCalculator passingSkillCalculator;

    @Autowired
    private PerimeterScoringSkillCalculator perimeterScoringSkillCalculator;

    @Autowired
    private PostScoringSkillCalculator postScoringSkillCalculator;

    @Autowired
    private TeamDefenseSkillCalculator teamDefenseSkillCalculator;

    @Autowired
    private TeamOffenseSkillCalculator teamOffenseSkillCalculator;

    public PlayerSkills mapSkills(Player player) {
        PlayerSkills skills = new PlayerSkills();
        skills.setAcumen(acumenSkillCalculator.calc(player));
        skills.setBallSecurity(ballSecuritySkillCalculator.calc(player));
        skills.setDefenseRebound(defenseReboundSkillCalculator.calc(player));
        skills.setFreeThrows(freeThrowSkillCalculator.calc(player));
        skills.setIndividualDefense(individualDefenseSkillCalculator.calc(player));
        skills.setLongRange(longRangeSkillCalculator.calc(player));
        skills.setOffenseRebound(offenseReboundSkillCalculator.calc(player));
        skills.setPassing(passingSkillCalculator.calc(player));
        skills.setPerimeter(perimeterScoringSkillCalculator.calc(player));
        skills.setPost(postScoringSkillCalculator.calc(player));
        skills.setTeamDefense(teamDefenseSkillCalculator.calc(player));
        skills.setTeamOffense(teamOffenseSkillCalculator.calc(player));
        return skills;
    }
}
