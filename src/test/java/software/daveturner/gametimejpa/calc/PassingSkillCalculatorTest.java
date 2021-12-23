package software.daveturner.gametimejpa.calc;

import org.junit.jupiter.api.BeforeEach;

public class PassingSkillCalculatorTest extends SkillSetCalculatorUnitTest {

    @BeforeEach
    public void setup() {
        calc = new PassingSkillCalculator();
        player  = BASE_PLAYER();
    }
}
