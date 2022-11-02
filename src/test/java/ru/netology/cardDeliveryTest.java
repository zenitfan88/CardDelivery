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



public class cardDeliveryTest {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999/");
        Configuration.holdBrowserOpen = true;
        Configuration.timeout = 10;
    }

    @Test
    void cardDeliveryTestNew() {
        Calendar c = new GregorianCalendar();
        c.add(Calendar.DAY_OF_YEAR, 4);
        SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy");
        String str = format1.format(c.getTime());
        $x("//input[@placeholder='Город']").val("Ульяновск");
        $x("//input[@placeholder='Дата встречи']").sendKeys(Keys.CONTROL+"A");
        $x("//input[@placeholder='Дата встречи']").sendKeys(Keys.BACK_SPACE);
        $x("//input[@placeholder='Дата встречи']").val(str);
        $x("//input[@name='name']").val("Иван Иванов");
        $x("//input[@name='phone']").val("+79278529614");
        $x("//label[@data-test-id='agreement']").click();
        $x("//*[text()='Забронировать']").click();
        $x("//div[@data-test-id='notification']").should(visible, Duration.ofSeconds(15));
    }
}
