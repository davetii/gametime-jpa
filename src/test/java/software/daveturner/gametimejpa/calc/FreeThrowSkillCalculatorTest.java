package software.daveturner.gametimejpa.calc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FreeThrowSkillCalculatorTest extends SkillSetCalculatorUnitTest{

    @BeforeEach
    public void setup() {
        calc = new FreeThrowSkillCalculator();
        player  = BASE_PLAYER();
    }

    @Test
    public void ensureAverageFreeThrowsReturnsExpected() {
        assertPlayer(AVERAGE_SKILLSET, calc);
    }

    @Test
    public void ensureAverage6YearPlayerReturnsExpected() {
        player.setYearsPro(6);
        assertPlayer(11.0, calc);
    }

    @Test
    public void ensureAverage12YearPlayerReturnsExpected() {
        player.setYearsPro(12);
        assertPlayer(13.5, calc);
    }
}
