package software.daveturner.gametimejpa.calc;

import org.springframework.stereotype.Component;
import software.daveturner.gametimejpa.domain.Player;

import java.math.BigDecimal;

@Component
public class BallSecuritySkillCalculator implements SkillCalculator{

    @Override
    public BigDecimal calc(Player player) {
        // desire, ego, endurance, energy, handle, intelligence, luck
        // desire, handle, intelligence, luck
        double value = ((player.getDetermination() * 2) + (player.getHandle() * 4) + (player.getIntelligence() * 3) + player.getLuck()) / 10d;

        if(player.getEnergy() != null) {
            if(player.getEnergy() > 18) { value -= 2; }
            else if(player.getEnergy() > 16) { value -= 1; }
        }

        if(player.getEgo() > 18) { value -= 2; }
        else if(player.getEgo() > 16) { value -= 1; }

        if(player.getEndurance() > 17) {value += 3; }
        else if(player.getEndurance() > 18) {value += 2; }
        else if(player.getEndurance() > 19) {value += 4; }

        return round(value);
    }
}
