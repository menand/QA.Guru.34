package lesson10.tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class PureSelenidTest extends TestBase{

    @Test
    public void testWithPureSelenide() {
        open("/");
        $(".input-button").click();
        $("#query-builder-test").setValue("selenide").submit();
        $(".search-title").click();
        $("[data-content='Issues']").shouldHave(text("Issues"));
    }
}
