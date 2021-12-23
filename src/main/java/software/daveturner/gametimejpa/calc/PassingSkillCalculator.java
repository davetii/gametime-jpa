package software.daveturner.gametimejpa.calc;

import org.springframework.stereotype.Component;
import software.daveturner.gametimejpa.domain.Player;

import java.math.BigDecimal;

@Component
public class PassingSkillCalculator implements SkillCalculator {
    @Override
    public BigDecimal calc(Player player) {
        //handle, intelligence, luck, ego, shot select, speed
        double value = ((player.getIntelligence() * 2) + (player.getHandle() * 3) + player.getLuck()) / 6d;

        if(player.getHandle() > 19) { value += 5;}
        else if(player.getHandle() > 18) { value += 4;}
        else if(player.getHandle() > 17) { value += 3;}
        else if(player.getHandle() > 16) { value += 2;}
        else if(player.getHandle() > 14) { value += 1.5;}
        else if(player.getHandle() > 13) { value += 1;}
        else if(player.getHandle() > 12) { value += .5;}
        else if(player.getHandle() < 3) { value -= 4;}
        else if(player.getHandle() < 5) { value -= 3;}
        else if(player.getHandle() < 6) { value -= 2;}
        else if(player.getHandle() < 7) { value -= 1;}

        if(player.getIntelligence() > 18) { value += 5;}
        else if(player.getIntelligence() > 16) { value += 4;}
        else if(player.getIntelligence() > 14) { value += 3;}
        else if(player.getIntelligence() > 13) { value += 2;}
        else if(player.getIntelligence() > 12) { value += 1.5;}
        else if(player.getIntelligence() > 11) { value += 1;}
        else if(player.getIntelligence() < 3) { value -= 4;}
        else if(player.getIntelligence() < 5) { value -= 3;}
        else if(player.getIntelligence() < 6) { value -= 2;}
        else if(player.getIntelligence() < 7) { value -= 1.5;}
        else if(player.getIntelligence() < 8) { value -= 1;}
        else if(player.getIntelligence() < 9) { value -= .5;}

        if(player.getYearsPro() > 11) { value += 4;}
        else if(player.getYearsPro() > 9) { value += 3;}
        else if(player.getYearsPro() > 8) { value += 2.5d;}
        else if(player.getYearsPro() > 7) { value += 2;}
        else if(player.getYearsPro() > 6) { value += 1;}

        return round(value);
    }
}
