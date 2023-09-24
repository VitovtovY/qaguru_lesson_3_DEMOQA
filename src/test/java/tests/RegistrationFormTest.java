package tests;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RegistrationFormTest {

    {
        Configuration.pageLoadStrategy = "eager";
    }
    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        // Configuration.holdBrowserOpen = true;
    }
    @Test
    void successfulRegistrationTest() {
        String userName = "Yury";
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        $("#firstName").setValue(userName);
        $("#lastName").setValue("Vitovtov");
        $("#userEmail").setValue("vitovtov.y@test.com");
        $("#genterWrapper").$(byText("Male")).click();
        $("#userNumber").setValue("2345678901");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("November");
        $(".react-datepicker__year-select").selectOptionByValue("1991");
        $(".react-datepicker__day--006:not(.react-datepicker__day--outside-month)").click();
        $("#subjectsInput").setValue("Chemistry").pressEnter();
        $("#subjectsInput").setValue("Maths").pressEnter();
        $("#hobbiesWrapper").$(byText("Sports")).click();
        $("#hobbiesWrapper").$(byText("Reading")).click();
        $("#uploadPicture").uploadFromClasspath("img/avatar.png");
        $("#currentAddress").setValue("344 Bobby st.");
        $("#state").click();
        $("#stateCity-wrapper").$(byText("Haryana")).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Panipat")).click();
        $("#submit").click();

        $(".modal-dialog").should(appear);
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(text("Yury"), text("vitovtov.y@test.com"), text("Vitovtov"),
                text("Male"), text("2345678901"), text("06 November,1991"),text("Chemistry, Maths"),
                text("Sports, Reading"), text("avatar.png"), text("344 Bobby st."), text("Haryana Panipat"));
        }
    }
