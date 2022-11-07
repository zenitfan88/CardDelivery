package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;


public class СardDeliveryTestPasses {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999/");
        Configuration.holdBrowserOpen = true;
        Configuration.timeout = 10;
    }

    public String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @Test
    void сardDeliveryTest() {
        String planningDate = generateDate(4);
        $("span[data-test-id='city'] input").val("Ульяновск");
        $x("//*[@data-test-id='date']//input[@class='input__control']")
                .sendKeys(Keys.CONTROL + "A");
        $x("//*[@data-test-id='date']//input[@class='input__control']").sendKeys(Keys.BACK_SPACE);
        $x("//*[@data-test-id='date']//input[@class='input__control']").val(planningDate);
        $x("//*[@data-test-id='name']//input[@class='input__control']").val("Иван Иванов-Сидоров");
        $x("//input[@name='phone']").val("+79278529614");
        $x("//label[@data-test-id='agreement']").click();
        $x("//*[text()='Забронировать']").click();
        $x("//div[@data-test-id='notification']").should(visible, Duration.ofSeconds(15));
        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }
}
