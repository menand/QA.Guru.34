package tests;


import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.visible;

public class HomeWork3Test {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--force-device-scale-factor=0.75"); // Масштаб 75%, иначе на ноуте не работает
        // Передача настроек в WebDriver
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        Configuration.browserCapabilities = capabilities;
    }

    @Test
    void fillFormTest() {
        open("/automation-practice-form");
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        $("#firstName").setValue("Andrey");
        $("#lastName").setValue("Menshov");
        $("#userEmail").setValue("menand@narod.ru");
        $("#gender-radio-1").parent().$(byText("Male")).click();
        $("#userNumber").setValue("9161234567");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("March");
        $(".react-datepicker__year-select").selectOption("1988");
        $(".react-datepicker__day--019").click();
        $("#subjectsInput").setValue("Comm").pressEnter();
        $("#hobbies-checkbox-3").parent().$(byText("Music")).click();
        $("#uploadPicture").uploadFromClasspath("images/ava.jpg");
        $("#currentAddress").setValue("Russia, Moscow");
        $("#state").click();
        $(byText("Haryana")).click();
        $("#city").click();
        $(byText("Panipat")).click();

        $("#submit").click();

        $(".modal-content").shouldBe(visible);
        $(".modal-title").shouldHave(text("Thanks for submitting the form"));
        $(".modal-content").shouldHave(text("Andrey Menshov"));
        $(".modal-content").shouldHave(text("menand@narod.ru"));
        $(".modal-content").shouldHave(text("Male"));
        $(".modal-content").shouldHave(text("9161234567"));
        $(".modal-content").shouldHave(text("19 March,1988"));
        $(".modal-content").shouldHave(text("Commerce"));
        $(".modal-content").shouldHave(text("Music"));
        $(".modal-content").shouldHave(text("images/ava.jpg"));
        $(".modal-content").shouldHave(text("Russia, Moscow"));
        $(".modal-content").shouldHave(text("Haryana Panipat"));

    }
}
