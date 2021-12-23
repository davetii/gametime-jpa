package software.daveturner.gametimejpa.calc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PerimeterScoringSkillCalculatorTest extends SkillSetCalculatorUnitTest {

    @BeforeEach
    public void setup() {
        calc = new PerimeterScoringSkillCalculator();
        player  = BASE_PLAYER();
    }

    @Test
    public void ensureAveragePerimeterReturnsExpected() {
        assertPlayer(AVERAGE_SKILLSET, calc);
    }

    @Test
    public void ensureHighShotSkillReturnsExpected() {
        player.setShotSkill(20);
        assertPlayer(21d, calc);
    }

    @Test
    public void ensureLowShotSkillReturnsExpected() {
        player.setShotSkill(1);
        assertPlayer(-1.6d, calc);
    }
}
