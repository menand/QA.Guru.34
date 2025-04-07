package lesson08.utils;

import net.datafaker.Faker;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class RandomUtils {
    Faker faker = new Faker(new Locale("en-GB"));

    public String findCityByState(String state) {
        return switch (state) {
            case "NCR" -> faker.options().option("Delhi", "Gurgaon", "Noida");
            case "Uttar Pradesh" -> faker.options().option("Agra", "Lucknow", "Merrut");
            case "Haryana" -> faker.options().option("Karnal", "Panipat");
            case "Rajasthan" -> faker.options().option("Jaipur", "Jaiselmer");
            default -> "";
        };
    }

    public void generateTestData(TestData testData) {
        testData.firstName = faker.name().firstName();
        testData.lastName = faker.name().lastName();
        testData.userEmail = faker.internet().emailAddress();
        testData.gender = faker.options().option("Female", "Male", "Other");
        testData.userNumber = faker.number().digits(10);
        LocalDate fullDayOfBirthday = LocalDate.ofInstant(faker.date().birthday(18, 65).toInstant(),
                ZoneId.systemDefault());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM", Locale.ENGLISH);
        testData.monthOfBirth = fullDayOfBirthday.format(formatter);
        testData.dayOfBirth = String.valueOf(fullDayOfBirthday.getDayOfMonth());
        testData.yearOfBirth = String.valueOf(fullDayOfBirthday.getYear());
        testData.subject = faker.options().option("Maths", "English", "Physics", "Chemistry", "Biology", "Computer Science", "Commerce",
                "Accounting", "Economics", "Arts", "Social Studies", "History", "Civics");
        testData.hobbies = faker.options().option("Sports", "Music", "Reading");
        testData.currentAddress = faker.address().fullAddress();
        testData.state = faker.options().option("NCR", "Uttar Pradesh", "Haryana", "Rajasthan");
        testData.city = findCityByState(testData.state);
    }

}
