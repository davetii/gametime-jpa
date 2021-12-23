package software.daveturner.gametimejpa.calc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DefenseReboundSkillCalculatorTest extends SkillSetCalculatorUnitTest{

    @BeforeEach
    public void setup() {
        calc = new DefenseReboundSkillCalculator();
        player  = BASE_PLAYER();
    }

    @Test
    public void ensureAverageDefenseReboundsReturnsExpected() {
        assertPlayer(AVERAGE_SKILLSET, calc);
    }

    @Test
    public void ensureLowEgoReturnsExpected() {
        player.setEgo(1);
        assertPlayer(5.2d, calc);
    }

    @Test
    public void ensureLowEnduranceReturnsExpected() {
        player.setEndurance(4);
        assertPlayer(9.0d, calc);
    }

    @Test
    public void ensureLowSizeReturnsExpected() {
        player.setSize(2);
        assertPlayer(5.5d, calc);
    }

    @Test
    public void ensureBigAndStrongReturnsExpected() {
        player.setStrength(18); player.setSize(18);
        assertPlayer(16.4d, calc);
    }

    @Test
    public void ensureBigEgoReturnsExpected() {
        player.setEgo(18);
        assertPlayer(11.7d, calc);
    }

    @Test
    public void ensureSuperAgileReturnsExpected() {
        player.setAthleticism(19);
        assertPlayer(13.5d, calc);
    }

    @Test
    public void ensureBigPlayerReturnsExpected() {
        player.setSize(19);
        assertPlayer(14.6d, calc);
    }
}
