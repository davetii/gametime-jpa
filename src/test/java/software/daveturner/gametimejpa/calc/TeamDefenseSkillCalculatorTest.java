package software.daveturner.gametimejpa.calc;

import org.junit.jupiter.api.BeforeEach;

public class TeamDefenseSkillCalculatorTest extends SkillSetCalculatorUnitTest {

    @BeforeEach
    public void setup() {
        calc = new TeamDefenseSkillCalculator();
        player  = BASE_PLAYER();
    }
}
