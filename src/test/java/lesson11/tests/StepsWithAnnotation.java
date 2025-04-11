package lesson11.tests;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class StepsWithAnnotation{

    @Step("Открываем github.com")
    public void openGitHub() {
        open("/");
    }

    @Step("Ищем и открываем репозиторий селенида")
    public void selenideRepoOpen() {
        $(".input-button").click();
        $("#query-builder-test").setValue("selenide").submit();
        $(".search-title").click();
    }

    @Step("Проверяем что вкладка Issue доступна")
    public void issueTabIsExist() {
        $("[data-content='Issues']").shouldHave(text("Issues"))
                .shouldBe(visible);
    }
}