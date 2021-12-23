package software.daveturner.gametimejpa.calc;

import org.springframework.stereotype.Component;
import software.daveturner.gametimejpa.domain.Player;

import java.math.BigDecimal;

@Component
public class IndividualDefenseSkillCalculator implements SkillCalculator {

    @Override
    public BigDecimal calc(Player player) {
        double value = ((player.getAthleticism() * 3) + (player.getDetermination() * 3) + (player.getIntelligence() * 2) +
                player.getEgo() + (player.getEndurance() * 2) + player.getHandle() + player.getLuck()) / 13d;

        value += maybeAddIndividualDefense(player);
        value -= maybeLowerInidividualDefense(player);
        if(player.getYearsPro() > 12) { value -= 2.5d; }
        else if ( player.getYearsPro() > 9) { value -= 2d; }
        else if ( player.getYearsPro() > 6) { value -= 1d; }
        return round(value);
    }

    private double maybeAddIndividualDefense(Player a) {
        double result = 0d;
        int speedStrength = a.getStrength() + a.getSpeed();
        int strengthSize = a.getStrength() + a.getSize();

        if (speedStrength > 35) {
            result += 6;
        } else if (speedStrength > 32) {
            result += 4.5;
        } else if (speedStrength > 30) {
            result += 3.5;
        } else if (speedStrength > 28) {
            result += 2;
        } else if (speedStrength > 25) {
            result += 1;
        } else if (strengthSize > 35) {
            result += 3.5;
        } else if (strengthSize > 32) {
            result += 2.5;
        } else if (strengthSize > 30) {
            result += 1.5;
        } else if (strengthSize > 28) {
            result += 1;
        } else if (strengthSize > 26) {
            result += .5;
        } else if (a.getSpeed() > 18) {
            result += 3;
        } else if (a.getSpeed() > 16) {
            result += 2;
        } else if (a.getSpeed() > 14) {
            result += 2;
        }
        return result;
    }

    private double maybeLowerInidividualDefense(Player a) {
        double result = 0d;
        int speedStrength = a.getStrength() + a.getSpeed();

        if (speedStrength < 4) {
            result += 5;
        } else if (speedStrength < 7) {
            result += 4;
        } else if (speedStrength < 10) {
            result += 3;
        } else if (speedStrength < 14) {
            result += 2;
        } else if (speedStrength < 18) {
            result += 1;
        }

        if (a.getSize() > 13) {
            if(a.getStrength() < 4) { result += 5; }
            else if(a.getStrength() < 6) { result += 4; }
            else if(a.getStrength() < 8) { result += 3; }
            else if(a.getStrength() < 10) { result += 2; }
        }

        if (a.getSize() < 8) {
            if(a.getSpeed() < 4) { result += 5; }
            else if(a.getSpeed() < 6) { result += 4; }
            else if(a.getSpeed() < 8) { result += 3; }
            else if(a.getSpeed() < 10) { result += 2; }
        }
        return result;
    }


}
