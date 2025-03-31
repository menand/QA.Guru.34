package lesson06.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class TextBoxFormPage {
    private final SelenideElement userNameInput = $("#userName"),
            userEmailInput = $("#userEmail"),
            currentAddressInput = $("#currentAddress"),
            permanentAddressInput = $("#permanentAddress"),
            submitButton = $("#submit"),
            outputTable = $("#output");


    public TextBoxFormPage openPage() {
        open("/text-box");
        this.removeBanner();
        $(".text-center").shouldHave(text("Text Box"));
        return this;
    }

    public TextBoxFormPage removeBanner() {
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        return this;
    }

    public TextBoxFormPage setUserName(String value) {
        userNameInput.setValue(value);
        return this;
    }

    public TextBoxFormPage setEmail(String value) {
        userEmailInput.setValue(value);
        return this;
    }

    public TextBoxFormPage setCurrentAddress(String value) {
        currentAddressInput.setValue(value);
        return this;
    }

    public TextBoxFormPage setPermanentAddress(String value) {
        permanentAddressInput.setValue(value);
        return this;
    }

    public void submitForm() {
        submitButton.click();
    }


    public TextBoxFormPage checkResult(String key, String value) {
        outputTable.$("#" + key).shouldHave(text(value));
        return this;
    }
}
