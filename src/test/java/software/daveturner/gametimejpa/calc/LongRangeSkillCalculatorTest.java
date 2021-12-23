package software.daveturner.gametimejpa.calc;

import org.junit.jupiter.api.BeforeEach;

public class LongRangeSkillCalculatorTest extends SkillSetCalculatorUnitTest{

    @BeforeEach
    public void setup() {
        calc = new LongRangeSkillCalculator();
        player  = BASE_PLAYER();
    }
}
