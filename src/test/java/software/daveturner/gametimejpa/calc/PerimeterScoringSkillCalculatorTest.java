package software.daveturner.gametimejpa.calc;

import org.junit.jupiter.api.BeforeEach;

public class PerimeterScoringSkillCalculatorTest extends SkillSetCalculatorUnitTest {

    @BeforeEach
    public void setup() {
        calc = new PerimeterScoringSkillCalculator();
        player  = BASE_PLAYER();
    }
}
