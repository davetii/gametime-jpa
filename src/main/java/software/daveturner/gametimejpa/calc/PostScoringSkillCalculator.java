package software.daveturner.gametimejpa.calc;

import org.springframework.stereotype.Component;
import software.daveturner.gametimejpa.domain.Player;

import java.math.BigDecimal;

@Component
public class PostScoringSkillCalculator implements SkillCalculator {
    @Override
    public BigDecimal calc(Player player) {

        double value = ((player.getSize() * 2) + (player.getStrength() * 4) + (player.getShotSelection() * 3) + player.getIntelligence()  ) / 10d;

        if(player.getSize() > 12) {
            if(player.getAthleticism() > 17) { value += 2.5; }
            else if(player.getAthleticism() > 14) { value += 1.5; }
        }

        if(player.getSize() > 11) {
            if(player.getDetermination() > 18) { value += 4; }
            if(player.getDetermination() > 16) { value += 2.5; }
            else if(player.getDetermination() > 14) { value += 1.5; }
        }


        if(player.getSize() > 12) {
            if(player.getShotSkill() > 18) { value += 4; }
            if(player.getShotSkill() > 16) { value += 2.5; }
            else if(player.getShotSkill() > 14) { value += 1.5; }
        }

        if(player.getSize() > 12) {
            if(player.getLuck() < 3) { value -= 2.5; }
            else if(player.getLuck() < 6) { value -= 1.5; }
            else if(player.getLuck() < 8) { value -= .5; }
        }

        if(player.getShotSkill() < 3) { value -= 6; }
        if(player.getShotSkill() < 6) { value -= 4; }
        else if(player.getShotSkill() < 8) { value -= 2; }

        if(player.getDetermination() < 3) { value -= 5; }
        if(player.getDetermination() < 6) { value -= 3; }
        else if(player.getDetermination() < 8) { value -= 1.5; }

        if(player.getDetermination() < 3) { value -= 4; }
        if(player.getDetermination() < 6) { value -= 3; }
        else if(player.getDetermination() < 8) { value -= 1.5; }

        if(player.getSize() < 3) { value -= 4; }
        if(player.getSize() < 6) { value -= 3; }
        else if(player.getSize() < 8) { value -= 2; }

        return round(value);
    }
}
