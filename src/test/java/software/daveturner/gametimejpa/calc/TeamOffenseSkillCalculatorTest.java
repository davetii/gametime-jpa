package software.daveturner.gametimejpa.calc;

import org.junit.jupiter.api.BeforeEach;

public class TeamOffenseSkillCalculatorTest extends SkillSetCalculatorUnitTest {

    @BeforeEach
    public void setup() {
        calc = new TeamOffenseSkillCalculator();
        player  = BASE_PLAYER();
    }
}
