package lesson06.tests;


import lesson06.pages.TextBoxFormPage;
import org.junit.jupiter.api.Test;

public class TextBoxFormTest extends TestBase {
    String userName = "Andrey Menshov";
    String userEmail = "menand@narod.ru";
    String currentAddress = "Russia, Moscow, Some street 1";
    String permanentAddress = "123456, Moscow, Another street 1";

    TextBoxFormPage textBoxFormPage = new TextBoxFormPage();

    @Test
    void fillTextFormTest() {
        textBoxFormPage.openPage()
                .setUserName(userName)
                .setEmail(userEmail)
                .setCurrentAddress(currentAddress)
                .setPermanentAddress(permanentAddress)
                .submitForm();

        textBoxFormPage.checkResult("name",userName)
                .checkResult("email",userEmail)
                .checkResult("currentAddress",currentAddress)
                .checkResult("permanentAddress",permanentAddress);
    }
}