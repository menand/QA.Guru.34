package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.junit5.SoftAssertsExtension;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.DragAndDropOptions.to;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.title;

@ExtendWith(SoftAssertsExtension.class)
public class HomeWork5Test {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://github.com";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void solutionEnterpriseTest() {
        open("/");
        $$(".HeaderMenu ul li").filterBy(text("Solutions")).first().hover();
        $$(".HeaderMenu-column ul li").filterBy(text("Enterprises")).first().click();
        webdriver().shouldHave(title("The AI Powered Developer Platform. · GitHub"));
        $("[data-testid=SubNav-root]").shouldHave(text("Enterprise"));
    }

    @Test
    void dragNDropTestWithActions() {
        open("https://the-internet.herokuapp.com/drag_and_drop");
        $("#columns").$$(".column").first().shouldHave(text("A"));
        actions()
                .clickAndHold($("#column-a"))
                .moveToElement($("#column-b"))
                .release()
                .perform();
        $("#columns").$$(".column").first().shouldHave(text("B"));
    }

    @Test
    void dragNDropTestWithDragAndDrop() {
        open("https://the-internet.herokuapp.com/drag_and_drop");
        $("#columns").$$(".column").first().shouldHave(text("A"));
        $("#column-a").dragAndDrop(to("#column-b"));
        $("#columns").$$(".column").first().shouldHave(text("B"));
    }
}
