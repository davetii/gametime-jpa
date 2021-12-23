package software.daveturner.gametimejpa.calc;

import org.junit.jupiter.api.BeforeEach;

public class FreeThrowSkillCalculatorTest extends SkillSetCalculatorUnitTest{

    @BeforeEach
    public void setup() {
        calc = new FreeThrowSkillCalculator();
        player  = BASE_PLAYER();
    }
}
