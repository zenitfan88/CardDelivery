package ru.netology;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;


public class cardDeliveryTestPasses {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999/");
        Configuration.holdBrowserOpen = true;
        Configuration.timeout = 10;
    }

    @Test
    void cardDeliveryTest() {
        Calendar c = new GregorianCalendar();
        c.add(Calendar.DAY_OF_YEAR, 4);
        SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy");
        String str = format1.format(c.getTime());
        $("span[data-test-id='city'] input").val("Ульяновск");
        $x("//*[@data-test-id='date']//input[@class='input__control']").sendKeys(Keys.CONTROL
                + "A");
        $x("//*[@data-test-id='date']//input[@class='input__control']").sendKeys(Keys.BACK_SPACE);
        $x("//*[@data-test-id='date']//input[@class='input__control']").val(str);
        $x("//*[@data-test-id='name']//input[@class='input__control']").val("Иван Иванов");
        $x("//input[@name='phone']").val("+79278529614");
        $x("//label[@data-test-id='agreement']").click();
        $x("//*[text()='Забронировать']").click();
        $x("//div[@data-test-id='notification']").should(visible, Duration.ofSeconds(15));
    }
}
