package lesson06.tests;

import lesson06.pages.PracticeFormPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

public class BigFormTest extends TestBase {
    String firstName = "Andrey";
    String lastName = "Menshov";
    String userEmail = "menand@main.ru";
    String gender = "Male";
    String userNumber = "1234567890";
    String dayOfBirth = "19";
    String monthOfBirth = "March";
    String yearOfBirth = "1988";
    String subject = "Computer Science";
    String hobbies = "Sports";
    String uploadImage = "images/ava.jpg";
    String currentAddress = "Moscow, Russia";
    String state = "Rajasthan";
    String city = "Jaipur";

    PracticeFormPage registrationPage = new PracticeFormPage();

    @Test
    @DisplayName("Проверка максимального набора данных")
    void successRegistrationTest() {
        registrationPage.openPage()
                .removeBanner()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(userEmail)
                .setGender(gender)
                .setNumber(userNumber)
                .setDateOfBirth(dayOfBirth, monthOfBirth, yearOfBirth)
                .setSubjects(subject)
                .setHobbies(hobbies)
                .setPicture(uploadImage)
                .setAddress(currentAddress)
                .setStateAndCity(state, city)
                .submitForm();

        registrationPage.checkResult("Student Name", firstName + " " + lastName)
                .checkResult("Student Email", userEmail)
                .checkResult("Gender", gender)
                .checkResult("Mobile", userNumber)
                .checkResult("Date of Birth", dayOfBirth + " " + monthOfBirth + "," + yearOfBirth)
                .checkResult("Subjects", subject)
                .checkResult("Hobbies", hobbies)
                .checkResult("Picture", new File(uploadImage).getName())
                .checkResult("Address", currentAddress)
                .checkResult("State and City", state + " " + city);
    }

    @Test
    @DisplayName("Проверка минимального набора данных")
    void minimalInputDataTest() {
        registrationPage.openPage()
                .removeBanner()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setGender(gender)
                .setNumber(userNumber)
                .submitForm();

        registrationPage.checkResult("Student Name", firstName + " " + lastName)
                .checkResult("Gender", gender)
                .checkResult("Mobile", userNumber);
    }

    @Test
    @DisplayName("Негативная проверка")
    void negativeRegistrationTest() {
        registrationPage.openPage()
                .removeBanner()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setGender(gender)
                .setNumber("12345")
                .submitForm();

        registrationPage.checkInvalidInput();
    }
}
