package lesson08.tests;

import lesson08.pages.PracticeFormPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class BigFormTestWithParam extends TestBase {


    PracticeFormPage registrationPage = new PracticeFormPage();

    @Tag("REGRESS")
    @ParameterizedTest(name = "Проверка регистрации с полом: {0}")
    @ValueSource(strings = {"Male", "Female", "Other"})
    @DisplayName("Проверка максимального набора данных")
    void successRegistrationWithAllFieldsTest(String gender) {
        registrationPage.openPage()
                .removeBanner()
                .setFirstName(testData.firstName)
                .setLastName(testData.lastName)
                .setEmail(testData.userEmail)
                .setGender(gender)
                .setNumber(testData.userNumber)
                .setDateOfBirth(testData.dayOfBirth, testData.monthOfBirth, testData.yearOfBirth)
                .setSubjects(testData.subject)
                .setHobbies(testData.hobbies)
                .setPicture(testData.uploadImage)
                .setAddress(testData.currentAddress)
                .setStateAndCity(testData.state, testData.city)
                .submitForm();

        registrationPage.checkResult("Student Name", testData.firstName + " " + testData.lastName)
                .checkResult("Student Email", testData.userEmail)
                .checkResult("Gender", gender)
                .checkResult("Mobile", testData.userNumber)
                .checkResult("Date of Birth", testData.dayOfBirth + " " + testData.monthOfBirth + "," + testData.yearOfBirth)
                .checkResult("Subjects", testData.subject)
                .checkResult("Hobbies", testData.hobbies)
                .checkResult("Picture", new File(testData.uploadImage).getName())
                .checkResult("Address", testData.currentAddress)
                .checkResult("State and City", testData.state + " " + testData.city);
    }

    @Tag("SMOKE")
    @ParameterizedTest(name = "Проверка регистрации с разными языками: {0} {1}")
    @CsvSource(value = {
            "Andrey, Menshov",
            "Андрей, Меньшов"
    })
    @DisplayName("Проверка минимального набора данных")
    void minimalInputDataWithDifferentLanguageTest(String firstName, String lastName) {
        registrationPage.openPage()
                .removeBanner()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setGender(testData.gender)
                .setNumber(testData.userNumber)
                .submitForm();

        registrationPage.checkResult("Student Name", firstName + " " + lastName)
                .checkResult("Gender", testData.gender)
                .checkResult("Mobile", testData.userNumber);
    }

    @Tag("SMOKE")
    @ParameterizedTest(name = "Проверка регистрации с разными Датами: {0}/{1}")
    @MethodSource("differentDays")
    void minimalInputDataWithDifferentDatesTest(int day, String month) {
        registrationPage.openPage()
                .removeBanner()
                .setFirstName(testData.firstName)
                .setLastName(testData.lastName)
                .setGender(testData.gender)
                .setNumber(testData.userNumber)
                .setDateOfBirth(String.valueOf(day), month, testData.yearOfBirth)
                .submitForm();

        registrationPage.checkResult("Student Name", testData.firstName + " " + testData.lastName)
                .checkResult("Gender", testData.gender)
                .checkResult("Date of Birth", day + " " + month + "," + testData.yearOfBirth)
                .checkResult("Mobile", testData.userNumber);
    }

    static Stream<Arguments> differentDays() {
        return Stream.of(
                arguments(1, "March"),
                arguments(31, "March")
        );
    }

    @Test
    @DisplayName("Негативная проверка")
    void negativeRegistrationTest() {
        registrationPage.openPage()
                .removeBanner()
                .setFirstName(testData.firstName)
                .setLastName(testData.lastName)
                .setGender(testData.gender)
                .setNumber("12345")
                .submitForm();

        registrationPage.checkInvalidInput();
    }
}
