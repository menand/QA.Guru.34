package lesson11.tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
public class PureSelenidTest extends TestBase {

    @Test
    public void testWithPureSelenide() {
        open("/");
        $(".input-button").click();
        $("#query-builder-test").setValue("selenide").submit();
        $(".search-title").click();
        $("[data-content='Issues']").shouldHave(text("Issues"));
    }
}
