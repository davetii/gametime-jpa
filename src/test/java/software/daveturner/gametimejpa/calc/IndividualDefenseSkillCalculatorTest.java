package software.daveturner.gametimejpa.calc;

import org.junit.jupiter.api.BeforeEach;

public class IndividualDefenseSkillCalculatorTest extends SkillSetCalculatorUnitTest{

    @BeforeEach
    public void setup() {
        calc = new IndividualDefenseSkillCalculator();
        player  = BASE_PLAYER();
    }
}
