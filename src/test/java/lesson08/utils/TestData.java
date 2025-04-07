package lesson08.utils;

public class TestData {
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

    public TestData() {
        RandomUtils randomUtils = new RandomUtils();
        randomUtils.generateTestData(this);
    }
}
