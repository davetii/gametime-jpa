package software.daveturner.gametimejpa.calc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IndividualDefenseSkillCalculatorTest extends SkillSetCalculatorUnitTest{

    @BeforeEach
    public void setup() {
        calc = new IndividualDefenseSkillCalculator();
        player  = BASE_PLAYER();
    }

    @Test
    public void ensureAverageIndividualDefenseReturnsExpected() {
        assertPlayer(AVERAGE_SKILLSET, calc);
    }

    @Test
    public void ensureStrongAndFastHasHighIndividualDefense() {
        player.setStrength(18);
        player.setSpeed(18);
        assertPlayer(16d, calc);
    }

    @Test
    public void ensureWeakBigGuysLowIndividualDefense() {
        player.setStrength(5);
        player.setSize(18);
        assertPlayer(5.0d, calc);
    }
}
