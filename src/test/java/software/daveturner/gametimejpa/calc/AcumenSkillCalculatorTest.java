package software.daveturner.gametimejpa.calc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AcumenSkillCalculatorTest extends SkillSetCalculatorUnitTest{

    AcumenSkillCalculator calc;

    @BeforeEach
    public void setup() {
        calc = new AcumenSkillCalculator();
        player  = BASE_PLAYER();
    }

    @Test
    public void ensureAverageAcumenReturnsExpected() {
        assertCalc(AVERAGE_SKILLSET);
    }

    @Test
    public void ensureHeightenedAgilityReturnsExpected() {
        player.setAthleticism(18);
        assertCalc(11d);
    }

    @Test
    public void ensureBigEgoReturnsExpected() {
        player.setEgo(20); assertCalc(7d);
        player.setEgo(19); assertCalc(8d);
        player.setEgo(18); assertCalc(9d);
        player.setEgo(17); assertCalc(AVERAGE_SKILLSET);
    }

    @Test
    public void ensureExtraHandleReturnsExpected() {
        player.setHandle(20); assertCalc(15.7d);
        player.setHandle(19); assertCalc(14.5d);
        player.setHandle(18); assertCalc(13.3d);
        player.setHandle(17); assertCalc(12.2d);
        player.setHandle(16); assertCalc(11.0d);
    }

    @Test
    public void ensureVetReturnsExpected() {
        player.setYearsPro(12);
        assertCalc(14d);

        player.setYearsPro(9);
        assertCalc(12.5);

        player.setYearsPro(8);
        assertCalc(12d);

        player.setYearsPro(6);
        assertCalc(11d);

        player.setYearsPro(5);
        assertCalc(AVERAGE_SKILLSET);

        player.setYearsPro(1);
        assertCalc(8d);

        player.setYearsPro(2);
        assertCalc(9d);
    }

    private void assertCalc(double d) {
        assertEquals(calc.round(d), calc.calc(player));
    }

    @Test
    public void ensureAgilityEgoMixReturnsExpected() {
        player.setEgo(20);
        player.setAthleticism(19);
        assertCalc(8d);
    }
}
