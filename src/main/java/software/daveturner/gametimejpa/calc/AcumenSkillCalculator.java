package software.daveturner.gametimejpa.calc;

import org.springframework.stereotype.Component;
import software.daveturner.gametimejpa.domain.Player;

import java.math.BigDecimal;

@Component
public class AcumenSkillCalculator implements SkillCalculator{

    @Override
    public BigDecimal calc(Player player) {
        double value = ((player.getIntelligence() * 2) + player.getHandle() + player.getLuck() + (player.getCohesion()*2)) / 6d;
        if(player.getAthleticism() > 17) { value++;}
        if(player.getSpeed() > 17) { value++;}

        if(player.getEgo() > 19) { value -= 3;}
        else if(player.getEgo() > 18) { value -= 2;}
        else if(player.getEgo() > 17) { value --;}

        if(player.getHandle() > 19) { value += 4;}
        else if(player.getHandle() > 18) { value += 3;}
        else if(player.getHandle() > 17) { value += 2;}
        else if(player.getHandle() > 16) { value += 1;}

        if(player.getHandle() < 3) { value -= 4;}
        else if(player.getHandle() < 5) { value -= 3;}
        else if(player.getHandle() < 7) { value -= 2;}
        else if(player.getHandle() < 9) { value -= 1;}

        if(player.getIntelligence() > 19) { value += 5;}
        else if(player.getIntelligence() > 18) { value += 3;}
        else if(player.getIntelligence() > 17) { value += 2;}
        else if(player.getIntelligence() > 16) { value += 1;}

        if(player.getIntelligence() < 2) { value -= 8;}
        else if(player.getIntelligence() < 3) { value -= 7;}
        else if(player.getIntelligence() < 4) { value -= 6.5;}
        else if(player.getIntelligence() < 5) { value -= 6;}
        else if(player.getIntelligence() < 6) { value -= 5.5;}
        else if(player.getIntelligence() < 7) { value -= 5;}
        else if(player.getIntelligence() < 8) { value -= 4;}
        else if(player.getIntelligence() < 10) { value -= 2;}

        if(player.getLuck() > 19) { value += 3;}
        else if(player.getLuck() > 18) { value += 2;}
        else if(player.getLuck() > 17) { value += 1;}
        else if(player.getLuck() < 2) { value -= 3;}
        else if(player.getLuck() < 3) { value -= 2;}
        else if(player.getLuck() < 4) { value -= 1;}

        if(player.getCohesion() > 19) { value += 3;}
        else if(player.getCohesion() > 18) { value += 2;}
        else if(player.getCohesion() > 17) { value += 1;}
        else if(player.getCohesion() < 2) { value -= 3;}
        else if(player.getCohesion() < 3) { value -= 2;}
        else if(player.getCohesion() < 4) { value -= 1;}

        if(player.getShotSelection() > 19) { value += 3;}
        else if(player.getShotSelection() > 18) { value += 2;}
        else if(player.getShotSelection() > 17) { value += 1;}

        if(player.getYearsPro() != null) {
            if(player.getYearsPro() > 11) { value += 4;}
            else if(player.getYearsPro() > 10) { value += 3.5d;}
            else if(player.getYearsPro() > 9) { value += 3;}
            else if(player.getYearsPro() > 8) { value += 2.5d;}
            else if(player.getYearsPro() > 7) { value += 2;}
            else if(player.getYearsPro() > 5) { value += 1;}
            if(player.getYearsPro() == 1) { value -= 2;}
            else if(player.getYearsPro() == 2) { value -= 1;}
        }
        return round(value);
    }
}
