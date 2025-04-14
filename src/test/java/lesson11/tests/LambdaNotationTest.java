package lesson11.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class LambdaNotationTest extends TestBase {
    private static final String PROJECTNAME = "selenide";


    @DisplayName("Тест с использованием Lambda-функций")
    @Test
    public void testLambdaNotation() {
        step("Открываем главную страницу", () -> {
            open("/");
            if (Configuration.browser.equalsIgnoreCase("firefox"))
                Selenide.executeJavaScript(
                        "window.consoleLogs = [];" +
                                "window.console.log = function(message) {" +
                                "   window.consoleLogs.push(message);" +
                                "   return originalConsoleLog.apply(console, arguments);" +
                                "};" +
                                "var originalConsoleLog = console.log;"
                );
        });
        step("Ищем репозиторий " + PROJECTNAME, () -> {
            $(".input-button").click();
            $("#query-builder-test").setValue(PROJECTNAME).submit();
        });
        step("Открываем репозиторий "+PROJECTNAME, () -> {
            $(".search-title").click();
        });
        step("Проверяем что Issue в проекте "+PROJECTNAME+" доступно", () -> {

            $("[data-content='Issues']").shouldHave(text("Issues"));
        });
    }
}
