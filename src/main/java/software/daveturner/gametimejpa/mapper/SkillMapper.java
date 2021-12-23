package software.daveturner.gametimejpa.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import software.daveturner.gametimejpa.calc.AcumenSkillCalculator;
import software.daveturner.gametimejpa.calc.BallSecuritySkillCalculator;
import software.daveturner.gametimejpa.domain.Player;
import software.daveturner.gametimejpa.domain.PlayerSkills;

@Service
public class SkillMapper {

    @Autowired
    private AcumenSkillCalculator acumenSkillCalculator;

    @Autowired
    private BallSecuritySkillCalculator ballSecuritySkillCalculator;

    public PlayerSkills mapSkills(Player player) {
        PlayerSkills skills = new PlayerSkills();
        skills.setAcumen(acumenSkillCalculator.calc(player));
        skills.setBallSecurity(ballSecuritySkillCalculator.calc(player));
        return skills;
    }
}
