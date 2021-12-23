package software.daveturner.gametimejpa.calc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AcumenSkillCalculatorTest extends SkillSetCalculatorUnitTest{

    @BeforeEach
    public void setup() {
        calc = new AcumenSkillCalculator();
        player  = BASE_PLAYER();
    }

    @Test
    public void ensureAverageAcumenReturnsExpected() {
        assertPlayer(AVERAGE_SKILLSET, calc);
    }

    @Test
    public void ensureHeightenedAgilityReturnsExpected() {
        player.setAthleticism(18);
        assertPlayer(11d, calc);
    }

    @Test
    public void ensureBigEgoReturnsExpected() {
        player.setEgo(20); assertPlayer(7d, calc);
        player.setEgo(19); assertPlayer(8d, calc);
        player.setEgo(18); assertPlayer(9d, calc);
        player.setEgo(17); assertPlayer(AVERAGE_SKILLSET, calc);
    }

    @Test
    public void ensureExtraHandleReturnsExpected() {
        player.setHandle(20); assertPlayer(15.7d, calc);
        player.setHandle(19); assertPlayer(14.5d, calc);
        player.setHandle(18); assertPlayer(13.3d, calc);
        player.setHandle(17); assertPlayer(12.2d, calc);
        player.setHandle(16); assertPlayer(11.0d, calc);
    }

    @Test
    public void ensureVetReturnsExpected() {
        player.setYearsPro(12);
        assertPlayer(14d, calc);

        player.setYearsPro(9);
        assertPlayer(12.5, calc);

        player.setYearsPro(8);
        assertPlayer(12d, calc);

        player.setYearsPro(6);
        assertPlayer(11d, calc);

        player.setYearsPro(5);
        assertPlayer(AVERAGE_SKILLSET, calc);

        player.setYearsPro(1);
        assertPlayer(8d, calc);

        player.setYearsPro(2);
        assertPlayer(9d, calc);
    }

    @Test
    public void ensureAgilityEgoMixReturnsExpected() {
        player.setEgo(20);
        player.setAthleticism(19);
        assertPlayer(8d, calc);
    }
}
