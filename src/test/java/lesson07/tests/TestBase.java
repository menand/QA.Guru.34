package lesson07.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import lesson07.utils.RandomUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import net.datafaker.Faker;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class TestBase {
    public String firstName,
            lastName,
            userEmail,
            gender,
            userNumber,
            dayOfBirth,
            monthOfBirth,
            yearOfBirth,
            subject,
            hobbies,
            uploadImage = "images/ava.jpg",
            currentAddress,
            state,
            city;

    Faker faker = new Faker(new Locale("en-GB"));

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
        firstName=faker.name().firstName();
        lastName=faker.name().lastName();
        userEmail=faker.internet().emailAddress();
        gender = faker.options().option("Female", "Male", "Other");
        userNumber=faker.number().digits(10);
        LocalDate fullDayOfBirthday = LocalDate.ofInstant(faker.date().birthday(18, 65).toInstant(),
                ZoneId.systemDefault());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM", Locale.ENGLISH);
        monthOfBirth = fullDayOfBirthday.format(formatter);
        dayOfBirth= String.valueOf(fullDayOfBirthday.getDayOfMonth());
        yearOfBirth=String.valueOf(fullDayOfBirthday.getYear());
        subject= faker.options().option("Maths","English","Physics","Chemistry","Biology","Computer Science","Commerce",
                "Accounting","Economics","Arts","Social Studies","History","Civics");
        hobbies = faker.options().option("Sports", "Music", "Reading");
        currentAddress=faker.address().fullAddress();
        state = faker.options().option("NCR", "Uttar Pradesh", "Haryana", "Rajasthan");
        city = new RandomUtils().findCityByState(state);
    }


}