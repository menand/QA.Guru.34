package lesson07.tests;

import lesson07.pages.PracticeFormPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

public class BigFormTest extends TestBase {


    PracticeFormPage registrationPage = new PracticeFormPage();

    @Test
    @DisplayName("Проверка максимального набора данных")
    void successRegistrationTest() {
        registrationPage.openPage()
                .removeBanner()
                .setFirstName(testData.firstName)
                .setLastName(testData.lastName)
                .setEmail(testData.userEmail)
                .setGender(testData.gender)
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
                .checkResult("Gender", testData.gender)
                .checkResult("Mobile", testData.userNumber)
                .checkResult("Date of Birth", testData.dayOfBirth + " " + testData.monthOfBirth + "," + testData.yearOfBirth)
                .checkResult("Subjects", testData.subject)
                .checkResult("Hobbies", testData.hobbies)
                .checkResult("Picture", new File(testData.uploadImage).getName())
                .checkResult("Address", testData.currentAddress)
                .checkResult("State and City", testData.state + " " + testData.city);
    }

    @Test
    @DisplayName("Проверка минимального набора данных")
    void minimalInputDataTest() {
        registrationPage.openPage()
                .removeBanner()
                .setFirstName(testData.firstName)
                .setLastName(testData.lastName)
                .setGender(testData.gender)
                .setNumber(testData.userNumber)
                .submitForm();

        registrationPage.checkResult("Student Name", testData.firstName + " " + testData.lastName)
                .checkResult("Gender", testData.gender)
                .checkResult("Mobile", testData.userNumber);
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
