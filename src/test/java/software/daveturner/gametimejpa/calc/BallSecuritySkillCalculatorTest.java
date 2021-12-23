package software.daveturner.gametimejpa.calc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BallSecuritySkillCalculatorTest extends SkillSetCalculatorUnitTest{

    BallSecuritySkillCalculator calc;

    @BeforeEach
    public void setup() {
        calc = new BallSecuritySkillCalculator();
        player  = BASE_PLAYER();
    }

    private void assertPLayer(double d) {
        Assertions.assertEquals(calc.calc(player), calc.round(d));
    }

    @Test
    public void ensureAverageBallSecurityReturnsExpected() {
        assertPLayer(AVERAGE_SKILLSET);
    }

    @Test
    public void ensureHighEnergyAffectsBallSecurity() {
        player.setEnergy(19);
        assertPLayer(8d);
    }

    @Test
    public void ensureHighEgoAffectsBallSecurity() {
        player.setEgo(19);
        assertPLayer(8d);
    }

    @Test
    public void ensureHighEnduranceAffectsBallSecurity() {
        player.setEndurance(18);
        assertPLayer(13d);
    }
}
