package software.daveturner.gametimejpa.calc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TeamOffenseSkillCalculatorTest extends SkillSetCalculatorUnitTest {

    @BeforeEach
    public void setup() {
        calc = new TeamOffenseSkillCalculator();
        player  = BASE_PLAYER();
    }

    @Test
    public void ensureAverageTeamOffenseReturnsExpected() {
        assertPlayer(AVERAGE_SKILLSET, calc);
    }

    @Test
    public void ensureHighIntelReturnsExpected() {
        player.setIntelligence(16);
        assertPlayer(11.3d, calc);
    }

    @Test
    public void ensureHighEgoReturnsExpected() {
        player.setEgo(18);
        assertPlayer(8d, calc);
    }
}
