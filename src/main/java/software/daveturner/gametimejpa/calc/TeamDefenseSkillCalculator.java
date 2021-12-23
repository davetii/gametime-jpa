package software.daveturner.gametimejpa.calc;

import org.springframework.stereotype.Component;
import software.daveturner.gametimejpa.domain.Player;

import java.math.BigDecimal;

@Component
public class TeamDefenseSkillCalculator implements SkillCalculator {
    @Override
    public BigDecimal calc(Player player) {

        double value = ((player.getIntelligence() * 2) + player.getStrength() + player.getSpeed() + (player.getCohesion()*2)) / 6d;

        if(player.getEgo() > 18) { value -= 2;}
        else if(player.getEgo() > 15) { value -= 1;}
        else if(player.getEgo() < 4) { value += 2;}
        else if(player.getEgo() < 8) { value += 1;}

        if(player.getCohesion() > 18) { value += 6;}
        else if (player.getCohesion() > 17) { value += 4;}
        else if (player.getCohesion() > 16) { value += 3;}
        else if (player.getCohesion() > 15) { value += 2;}
        else if (player.getCohesion() > 14) { value += 1.5;}
        else if (player.getCohesion() > 13) { value += 1;}
        else if (player.getCohesion() > 10) { value += .5;}

        if (player.getCohesion() < 2) { value -= 6;}
        else if (player.getCohesion() < 4) { value -= 4;}
        else if (player.getCohesion() < 5) { value -= 2;}
        else if (player.getCohesion() < 6) { value -= 1.5;}
        else if (player.getCohesion() < 8) { value -= 1;}
        else if (player.getCohesion() < 9) { value -= .5;}

        if(player.getIntelligence() > 18) { value += 6;}
        else if (player.getIntelligence() > 17) { value += 4;}
        else if (player.getIntelligence() > 16) { value += 3;}
        else if (player.getIntelligence() > 15) { value += 2;}
        else if (player.getIntelligence() > 14) { value += 1.5;}
        else if (player.getIntelligence() > 13) { value += 1;}
        else if (player.getIntelligence() > 10) { value += .5;}
        else if (player.getIntelligence() < 2) { value -= 6;}
        else if (player.getIntelligence() < 4) { value -= 4;}
        else if (player.getIntelligence() < 5) { value -= 2;}
        else if (player.getIntelligence() < 6) { value -= 1.5;}
        else if (player.getIntelligence() < 8) { value -= 1;}
        else if (player.getIntelligence() < 9) { value -= .5;}

        if(player.getEnergy() > 17) { value += 6;}
        else if (player.getEnergy() > 15) { value += 4;}
        else if (player.getEnergy() > 12) { value += 2;}
        else if (player.getEnergy() < 2) { value -= 5;}
        else if (player.getEnergy() < 4) { value -= 3;}
        else if (player.getEnergy() < 6) { value -= 2;}
        else if (player.getEnergy() < 8) { value -= 1;}

        if(player.getStrength() > 17) { value += 4;}
        else if (player.getStrength() > 15) { value += 2.5;}
        else if (player.getStrength() > 12) { value += 1;}
        else if (player.getStrength() < 2) { value -= 4;}
        else if (player.getStrength() < 4) { value -= 3;}
        else if (player.getStrength() < 6) { value -= 2;}

        if(player.getSpeed() > 17) { value += 3;}
        else if (player.getSpeed() > 15) { value += 2;}
        else if (player.getSpeed() > 12) { value += 1;}
        else if (player.getSpeed() < 2) { value -= 3;}
        else if (player.getSpeed() < 4) { value -= 2;}
        else if (player.getSpeed() < 6) { value -= 1;}

        return round(value);
    }
}
