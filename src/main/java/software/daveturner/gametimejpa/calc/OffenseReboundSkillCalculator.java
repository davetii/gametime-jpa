package software.daveturner.gametimejpa.calc;

import org.springframework.stereotype.Component;
import software.daveturner.gametimejpa.domain.Player;

import java.math.BigDecimal;

@Component
public class OffenseReboundSkillCalculator implements SkillCalculator{

    @Override
    public BigDecimal calc(Player player) {
        double value =
                (player.getEgo() + (player.getDetermination() * 3) +
                        (player.getEnergy() * 2) + (player.getIntelligence() * 2) +
                        (player.getSize() * 2) + (player.getStrength() * 2)) / 12d;

        if(player.getDetermination() > 18) { value += 3.5;}
        else if(player.getDetermination() > 16) { value += 2.5;}
        else if(player.getDetermination() > 14) { value +=1.5; }
        else if(player.getDetermination() > 12) { value +=1; }

        if(player.getAthleticism() > 18) { value += 3.5;}
        else if(player.getAthleticism() > 16) { value += 2.5;}
        else if(player.getAthleticism() > 14) { value +=1.5; }
        else if(player.getAthleticism() > 12) { value +=1; }

        if(player.getSize() > 18) { value += 3;}
        else if(player.getSize() > 16) { value += 2;}
        else if(player.getSize() > 14) { value +=1; }

        if(player.getSpeed() > 17) { value += 2;}
        else if(player.getSpeed() > 13) { value +=1; }

        if(player.getEgo() > 18) { value += 3;}
        else if(player.getEgo() > 15) { value +=1; }

        if(player.getEgo() < 2) { value -= 4;}
        else if(player.getEgo() < 4) { value -=2; }

        if(player.getEndurance() < 2) { value -= 3;}
        else if(player.getEndurance() < 3) { value -=2; }
        else if(player.getEndurance() < 5) { value -=1; }

        if(player.getSize() < 2) { value -= 4;}
        else if(player.getSize() < 3) { value -=3; }
        else if(player.getSize() < 5) { value -=2; }
        else if(player.getSize() < 7) { value -=1; }

        if(player.getDetermination() < 2) { value -= 4;}
        else if(player.getDetermination() < 3) { value -=3; }
        else if(player.getDetermination() < 5) { value -=2; }
        else if(player.getDetermination() < 7) { value -=1; }

        return round(value);
    }
}
