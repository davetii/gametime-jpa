package software.daveturner.gametimejpa.calc;

import org.springframework.stereotype.Component;
import software.daveturner.gametimejpa.domain.Player;
import java.math.BigDecimal;

@Component
public class PerimeterScoringSkillCalculator implements SkillCalculator {

    @Override
    public BigDecimal calc(Player player) {
        double value = ((player.getShotSkill() * 4) +
                (player.getShotSelection() * 2) +
                player.getLuck() +
                player.getIntelligence() +
                player.getAthleticism() +
                player.getSpeed()) / 10d;

        if(player.getShotSkill() > 19) { value += 7; }
        else if(player.getShotSkill() > 18) { value += 5; }
        else if(player.getShotSkill() > 17) { value += 3.5; }
        else if(player.getShotSkill() > 16) { value += 2.5; }
        else if(player.getShotSkill() > 15) { value += 2; }
        else if(player.getShotSkill() > 12) { value += 1; }

        if(player.getShotSkill() < 2) { value -= 8; }
        else if(player.getShotSkill() < 3) { value -= 6; }
        else if(player.getShotSkill() < 4) { value -= 5; }
        else if(player.getShotSkill() < 5) { value -= 4; }
        else if(player.getShotSkill() < 6) { value -= 3; }
        else if(player.getShotSkill() < 8) { value -= 2; }
        else if(player.getShotSkill() < 10) { value -= 1; }

        if(player.getShotSelection() > 18) { value += 4; }
        else if(player.getShotSelection() > 16) { value += 3; }
        else if(player.getShotSelection() > 14) { value += 2; }

        if(player.getSize() > 18) { value -= 2; }
        else if(player.getSize() > 16) { value -= 1.5; }
        else if(player.getSize() > 14) { value -= 1; }

        return round(value);
    }
}
