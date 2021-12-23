package software.daveturner.gametimejpa.calc;

import org.junit.jupiter.api.BeforeEach;

public class PostScoringSkillCalculatorTest extends SkillSetCalculatorUnitTest {

    @BeforeEach
    public void setup() {
        calc = new PostScoringSkillCalculator();
        player  = BASE_PLAYER();
    }
}
