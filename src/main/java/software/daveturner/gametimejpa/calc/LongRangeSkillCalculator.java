package software.daveturner.gametimejpa.calc;

import org.springframework.stereotype.Component;
import software.daveturner.gametimejpa.domain.Player;

import java.math.BigDecimal;

@Component
public class LongRangeSkillCalculator implements SkillCalculator {

    @Override
    public BigDecimal calc(Player player) {
        return null;
    }
}
