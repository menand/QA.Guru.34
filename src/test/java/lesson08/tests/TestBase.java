package lesson08.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import lesson08.utils.TestData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;


public class TestBase {

    TestData testData;

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1280x720";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
    }

    @AfterEach
    void afterEach() {
        Selenide.closeWebDriver();
    }

    @BeforeEach
    void beforeEach() {
        testData = new TestData();
    }


}