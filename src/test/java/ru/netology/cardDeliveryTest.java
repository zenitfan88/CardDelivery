package ru.netology;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import java.time.Duration;
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
        $x("//input[@placeholder='Город']").val("Ульяновск");
        $x("//input[@placeholder='Дата встречи']").sendKeys(Keys.CONTROL+"A");
        $x("//input[@placeholder='Дата встречи']").sendKeys(Keys.BACK_SPACE);
        $x("//input[@placeholder='Дата встречи']").val("06.11.2022");
        $x("//input[@name='name']").val("Иван Иванов");
        $x("//input[@name='phone']").val("+79278529614");
        $x("//label[@data-test-id='agreement']").click();
        $x("//*[text()='Забронировать']").click();
        $x("//div[@data-test-id='notification']").should(visible, Duration.ofSeconds(15));
    }
}
