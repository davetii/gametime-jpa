package software.daveturner.gametimejpa.calc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OffenseReboundSkillCalculatorTest extends SkillSetCalculatorUnitTest {

    @BeforeEach
    public void setup() {
        calc = new OffenseReboundSkillCalculator();
        player  = BASE_PLAYER();
    }

    @Test
    public void ensureAverageOffenseReboundsReturnsExpected() {
        assertPlayer(AVERAGE_SKILLSET, calc);
    }

    @Test
    public void ensureLowSizeReturnsExpected() {
        player.setSize(2);
        assertPlayer(5.7d, calc);
    }

    @Test
    public void ensureHighDeterminationReturnsExpected() {
        player.setDetermination(19);
        assertPlayer(15.8d, calc);
    }

    @Test
    public void ensureBigSizeAndSpeedReturnsExepected() {
        player.setSize(17);
        player.setSpeed(14);
        assertPlayer(14.2d, calc);
    }
}
