package software.daveturner.gametimejpa.calc;


import org.springframework.stereotype.Component;
import software.daveturner.gametimejpa.domain.Player;

import java.math.BigDecimal;

@Component
public class DriveSkillCalculator implements SkillCalculator {

    @Override
    public BigDecimal calc(Player player) {
        double value = ((player.getAthleticism() * 3) + player.getDetermination() + (player.getHandle() * 2) + player.getSpeed()) / 7d;
        value = calc(player.getEgo(), value);
        value = calc(player.getShotSkill(), value);
        value = calc(player.getSpeed(), value);
        value = calc(player.getStrength(), value);

        if(player.getSize() > 18) { value -= 3;}
        else if(player.getSize()  > 15) { value -= 2;}
        else if(player.getSize()  > 12) { value -= 1;}

        if(player.getEnergy() < 3) { value -= 4;}
        else if(player.getEnergy()  < 6) { value -= 3;}
        else if(player.getEnergy()  < 9) { value -= 1;}

        if(player.getYearsPro() > 10) { value -= 4; }
        else if(player.getYearsPro() > 8) { value -= 3; }
        else if(player.getYearsPro() > 7) { value -= 2; }
        else if(player.getYearsPro() > 6) { value -= 1; }

        return round(value);
    }

    private double calc(int a, double currentVal) {
        if(a > 18) { currentVal += 3;}
        else if(a > 15) { currentVal += 2;}
        else if(a > 12) { currentVal += 1;}
        else if(a < 3) { currentVal -= 3;}
        else if(a < 6) { currentVal -= 2;}
        else if(a < 9) { currentVal -= 1;}
        return currentVal;
    }
}
