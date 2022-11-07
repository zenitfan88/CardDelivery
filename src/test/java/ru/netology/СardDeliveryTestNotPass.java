package ru.netology;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$x;


public class СardDeliveryTestNotPass {
    @BeforeEach
    void setUp() {
        open("http://localhost:9999/");
        Configuration.holdBrowserOpen = true;
        Configuration.timeout = 10;
    }

    private void assertTrue(String element) {
    }

    public String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @Test
    void cardDeliveryTestNotPassCity() {
        String planningDate = generateDate(4);
        $("span[data-test-id='city'] input").val("New-York");
        $x("//*[@data-test-id='date']//input[@class='input__control']")
                .sendKeys(Keys.CONTROL + "A");
        $x("//*[@data-test-id='date']//input[@class='input__control']").sendKeys(Keys.BACK_SPACE);
        $x("//*[@data-test-id='date']//input[@class='input__control']").val(planningDate);
        $x("//*[@data-test-id='name']//input[@class='input__control']").val("Иван Иванов");
        $x("//input[@name='phone']").val("+79278529614");
        $x("//label[@data-test-id='agreement']").click();
        $x("//*[text()='Забронировать']").click();
        $x("//*[@data-test-id='city']//span[@class='input__sub']").shouldHave(exactText("Доставка " +
                "в выбранный город недоступна"));
    }

    @Test
    void cardDeliveryTestNotPassNoCity() {
        String planningDate = generateDate(4);
        $("span[data-test-id='city'] input").val("");
        $x("//*[@data-test-id='date']//input[@class='input__control']")
                .sendKeys(Keys.CONTROL + "A");
        $x("//*[@data-test-id='date']//input[@class='input__control']").sendKeys(Keys.BACK_SPACE);
        $x("//*[@data-test-id='date']//input[@class='input__control']").val(planningDate);
        $x("//*[@data-test-id='name']//input[@class='input__control']").val("Иван Иванов");
        $x("//input[@name='phone']").val("+79278529614");
        $x("//label[@data-test-id='agreement']").click();
        $x("//*[text()='Забронировать']").click();
        $x("//*[@data-test-id='city']//span[@class='input__sub']").shouldHave(exactText("Поле " +
                "обязательно для заполнения"));
    }

    @Test
    void cardDeliveryTestNotPassData() {
        String planningDate = generateDate(2);
        $("span[data-test-id='city'] input").val("Ульяновск");
        $x("//*[@data-test-id='date']//input[@class='input__control']")
                .sendKeys(Keys.CONTROL + "A");
        $x("//*[@data-test-id='date']//input[@class='input__control']").sendKeys(Keys.BACK_SPACE);
        $x("//*[@data-test-id='date']//input[@class='input__control']").val(planningDate);
        $x("//*[@data-test-id='name']//input[@class='input__control']").val("Иван Иванов");
        $x("//input[@name='phone']").val("+79278529614");
        $x("//label[@data-test-id='agreement']").click();
        $x("//*[text()='Забронировать']").click();
        $x("//*[@data-test-id='date']//span[@class='input__sub']").shouldHave(exactText("Заказ " +
                "на выбранную дату невозможен"));
    }

    @Test
    void cardDeliveryTestNotPassNoData() {
        $("span[data-test-id='city'] input").val("Ульяновск");
        $x("//*[@data-test-id='date']//input[@class='input__control']")
                .sendKeys(Keys.CONTROL + "A");
        $x("//*[@data-test-id='date']//input[@class='input__control']").sendKeys(Keys.BACK_SPACE);
        $x("//*[@data-test-id='name']//input[@class='input__control']").val("Иван Иванов");
        $x("//input[@name='phone']").val("+79278529614");
        $x("//label[@data-test-id='agreement']").click();
        $x("//*[text()='Забронировать']").click();
        $x("//*[@data-test-id='date']//span[@class='input__sub']").shouldHave(exactText("Неверно " +
                "введена дата"));
    }

    @Test
    void cardDeliveryTestNotPassName() {
        String planningDate = generateDate(4);
        $("span[data-test-id='city'] input").val("Ульяновск");
        $x("//*[@data-test-id='date']//input[@class='input__control']")
                .sendKeys(Keys.CONTROL + "A");
        $x("//*[@data-test-id='date']//input[@class='input__control']").sendKeys(Keys.BACK_SPACE);
        $x("//*[@data-test-id='date']//input[@class='input__control']").val(planningDate);
        $x("//*[@data-test-id='name']//input[@class='input__control']").val("Ivan");
        $x("//input[@name='phone']").val("+79278529614");
        $x("//label[@data-test-id='agreement']").click();
        $x("//*[text()='Забронировать']").click();
        $x("//*[@data-test-id='name']//span[@class='input__sub']").shouldHave(exactText("Имя и " +
                "Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void cardDeliveryTestNotPassNoName() {
        String planningDate = generateDate(4);
        $("span[data-test-id='city'] input").val("Ульяновск");
        $x("//*[@data-test-id='date']//input[@class='input__control']")
                .sendKeys(Keys.CONTROL + "A");
        $x("//*[@data-test-id='date']//input[@class='input__control']").sendKeys(Keys.BACK_SPACE);
        $x("//*[@data-test-id='date']//input[@class='input__control']").val(planningDate);
        $x("//*[@data-test-id='name']//input[@class='input__control']").val("");
        $x("//input[@name='phone']").val("+79278529614");
        $x("//label[@data-test-id='agreement']").click();
        $x("//*[text()='Забронировать']").click();
        $x("//*[@data-test-id='name']//span[@class='input__sub']").shouldHave(exactText("Поле " +
                "обязательно для заполнения"));
    }

    @Test
    void cardDeliveryTestNotPassPhone() {
        String planningDate = generateDate(4);
        $("span[data-test-id='city'] input").val("Ульяновск");
        $x("//*[@data-test-id='date']//input[@class='input__control']")
                .sendKeys(Keys.CONTROL + "A");
        $x("//*[@data-test-id='date']//input[@class='input__control']").sendKeys(Keys.BACK_SPACE);
        $x("//*[@data-test-id='date']//input[@class='input__control']").val(planningDate);
        $x("//*[@data-test-id='name']//input[@class='input__control']").val("Иван Иванов");
        $x("//input[@name='phone']").val("+7927852961");
        $x("//label[@data-test-id='agreement']").click();
        $x("//*[text()='Забронировать']").click();
        $x("//*[@data-test-id='phone']//span[@class='input__sub']").shouldHave(exactText("Телефон указан " +
                "неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void cardDeliveryTestNotPassNoCheckBox() {
        String planningDate = generateDate(4);
        $("span[data-test-id='city'] input").val("Ульяновск");
        $x("//*[@data-test-id='date']//input[@class='input__control']")
                .sendKeys(Keys.CONTROL + "A");
        $x("//*[@data-test-id='date']//input[@class='input__control']").sendKeys(Keys.BACK_SPACE);
        $x("//*[@data-test-id='date']//input[@class='input__control']").val(planningDate);
        $x("//*[@data-test-id='name']//input[@class='input__control']").val("Иван Иванов");
        $x("//input[@name='phone']").val("+79279631423");
        $x("//*[text()='Забронировать']").click();

        $("label[data-test-id='agreement']").shouldHave(cssClass("input_invalid"));
    }

}
