package lesson06.pages;

import com.codeborne.selenide.SelenideElement;
import lesson06.pages.components.CalendarComponent;
import lesson06.pages.components.ResultTableComponent;

import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormPage {
    private final SelenideElement firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            userEmailInput = $("#userEmail"),
            genderSelect = $("#genterWrapper"),
            userNumberInput = $("#userNumber"),
            calendarInput = $("#dateOfBirthInput"),
            subjectsInput = $("#subjectsInput"),
            hobbiesSelect = $("#hobbiesWrapper"),
            pictureSelect = $("#uploadPicture"),
            userAddressInput = $("#currentAddress"),
            stateAndCitySelect = $("#stateCity-wrapper"),
            stateSelect = $("#state"),
            citySelect = $("#city"),
            submitButton = $("#submit");

    private static final String ERROR_COLOR = "rgb(220, 53, 69)";

    private final CalendarComponent calendarComponent = new CalendarComponent();
    private final ResultTableComponent resultTableComponent = new ResultTableComponent();


    public PracticeFormPage openPage() {
        open("/automation-practice-form");
        $(".text-center").shouldHave(text("Practice Form"));
        return this;
    }

    public PracticeFormPage removeBanner() {
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        return this;
    }

    public PracticeFormPage setFirstName(String value) {
        firstNameInput.setValue(value);
        return this;
    }

    public PracticeFormPage setLastName(String value) {
        lastNameInput.setValue(value);
        return this;
    }


    public PracticeFormPage setEmail(String value) {
        userEmailInput.setValue(value);
        return this;
    }

    public PracticeFormPage setGender(String value) {
        genderSelect.$(byText(value)).click();
        return this;
    }

    public PracticeFormPage setNumber(String value) {
        userNumberInput.setValue(value);
        return this;
    }

    public PracticeFormPage setDateOfBirth(String day, String month, String year) {
        calendarInput.click();
        calendarComponent.setDate(day, month, year);
        return this;
    }

    public PracticeFormPage setSubjects(String value) {
        subjectsInput.setValue(value).pressEnter();
        return this;
    }

    public PracticeFormPage setHobbies(String value) {
        hobbiesSelect.$(byText(value)).scrollTo().click();
        return this;
    }

    public PracticeFormPage setPicture(String value) {
        pictureSelect.uploadFromClasspath(value);
        return this;
    }

    public PracticeFormPage setAddress(String value) {
        userAddressInput.setValue(value);
        return this;
    }

    public PracticeFormPage setStateAndCity(String state, String city) {
        stateSelect.click();
        stateAndCitySelect.$(byText(state)).click();
        citySelect.click();
        stateAndCitySelect.$(byText(city)).click();
        return this;
    }

    public void submitForm() {
        submitButton.click();
    }

    public void checkInvalidInput() {
        userNumberInput.shouldHave(cssValue("border-color", ERROR_COLOR));
    }

    public PracticeFormPage checkResult(String key, String value) {
        resultTableComponent.checkResult(key, value);
        return this;
    }
}
