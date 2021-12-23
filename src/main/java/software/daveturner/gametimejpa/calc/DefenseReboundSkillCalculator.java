package software.daveturner.gametimejpa.calc;

import org.springframework.stereotype.Component;
import software.daveturner.gametimejpa.domain.Player;

import java.math.BigDecimal;

@Component
public class DefenseReboundSkillCalculator  implements SkillCalculator{
    @Override
    public BigDecimal calc(Player player) {

            double value =
                    ((player.getDetermination() * 3) +
                            player.getEnergy() + player.getIntelligence() +
                            (player.getSize() * 2) + (player.getStrength() * 2)) / 9d;
            if (player.getAthleticism() > 9) {
                value += 3.5;
            } else if (player.getAthleticism() > 8) {
                value += 2.5;
            } else if (player.getAthleticism() > 7) {
                value += 1.5;
            } else if (player.getAthleticism() > 6) {
                value += 1;
            }

            if (player.getSize() > 9) {
                value += 3;
            } else if (player.getSize() > 8) {
                value += 2;
            } else if (player.getSize() > 7) {
                value += 1;
            }

            if (player.getSpeed() > 8) {
                value += 2;
            } else if (player.getSpeed() > 6) {
                value += 1;
            }

            if (player.getEgo() > 8) {
                value += 3;
            } else if (player.getEgo() > 6) {
                value += 1;
            }


            if (player.getEgo() < 2) {
                value -= 3;
            } else if (player.getEgo() < 4) {
                value -= 1;
            }

            if (player.getEndurance() < 2) {
                value -= 3;
            } else if (player.getEndurance() < 3) {
                value -= 2;
            } else if (player.getEndurance() < 4) {
                value -= 1;
            }

            if (player.getSize() < 2) {
                value -= 3;
            } else if (player.getSize() < 3) {
                value -= 2;
            } else if (player.getSize() > 7) {
                value += 1;
            } else if (player.getSize() > 9) {
                value += 2;
            }

            return round(value);


    }
}
