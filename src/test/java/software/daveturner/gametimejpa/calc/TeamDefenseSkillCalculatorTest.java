package software.daveturner.gametimejpa.calc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TeamDefenseSkillCalculatorTest extends SkillSetCalculatorUnitTest {

    @BeforeEach
    public void setup() {
        calc = new TeamDefenseSkillCalculator();
        player  = BASE_PLAYER();
    }

    @Test
    public void ensureAverageTeamDefenseReturnsExpected() {
        assertPlayer(AVERAGE_SKILLSET, calc);
    }

    @Test
    public void ensureHighPersonReturnsExpected() {
        player.setCohesion(17);
        assertPlayer(15.3d, calc);
    }

    @Test
    public void ensureSlowReturnsExpected() {
        player.setSpeed(2);
        assertPlayer(6.7d, calc);
    }
}
