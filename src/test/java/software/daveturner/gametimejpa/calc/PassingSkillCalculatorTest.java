package software.daveturner.gametimejpa.calc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PassingSkillCalculatorTest extends SkillSetCalculatorUnitTest {

    @BeforeEach
    public void setup() {
        calc = new PassingSkillCalculator();
        player  = BASE_PLAYER();
    }

    @Test
    public void ensureAveragePassingReturnsExpected() {
        assertPlayer(AVERAGE_SKILLSET, calc);
    }

    @Test
    public void assertGoodHandle() {
        player.setHandle(18);
        assertPlayer(17d, calc);
    }

    @Test
    public void assertGoodIntel() {
        player.setIntelligence(18);
        assertPlayer(16.7d, calc);
    }

    @Test
    public void assertBadHandle() {
        player.setHandle(4);
        assertPlayer(4d, calc);
    }

    @Test
    public void assertBadIntel() {
        player.setIntelligence(4);
        assertPlayer(5d, calc);
    }
}
