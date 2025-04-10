package lesson10.tests;

import org.junit.jupiter.api.Test;

public class StepsWithAnnotationTest extends TestBase{
    @Test
    public void testStepsWithAnnotation() {
        StepsWithAnnotation steps = new StepsWithAnnotation();
        steps.openGitHub();
        steps.selenideRepoOpen();
        steps.issueTabIsExist();
    }
}
