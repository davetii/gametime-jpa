package software.daveturner.gametimejpa.calc;

import org.junit.jupiter.api.BeforeEach;

public class OffenseReboundSkillCalculatorTest extends SkillSetCalculatorUnitTest {

    @BeforeEach
    public void setup() {
        calc = new OffenseReboundSkillCalculator();
        player  = BASE_PLAYER();
    }
}
