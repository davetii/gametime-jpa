package software.daveturner.gametimejpa.calc;

import org.junit.jupiter.api.BeforeEach;

public class DriveSkillCalculatorTest extends SkillSetCalculatorUnitTest{

    @BeforeEach
    public void setup() {
        calc = new DriveSkillCalculator();
        player  = BASE_PLAYER();
    }
}
