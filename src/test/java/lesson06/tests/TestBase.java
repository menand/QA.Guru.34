package lesson06.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.*;

public class TestBase {
    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920*1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
    }

    @AfterEach
    void afterEach() {
        Selenide.closeWebDriver();
    }
}