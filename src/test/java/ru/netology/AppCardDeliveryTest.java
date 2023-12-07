package ru.netology;

import com.github.javafaker.Faker;
import jdk.jfr.DataAmount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import ru.netology.DataGenerator.*;

import java.time.Duration;
import java.util.Date;
import java.util.Locale;

import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AppCardDeliveryTest {


    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @Test
    void shouldSubmitRequest() {
        UserData testUser = UserData.generate("ru");
        String name = testUser.getFirstLastName();
        String phone = testUser.getPhoneNumber();
        String city = testUser.getCity();
        String date = testUser.getMeetingDate();
        $("[data-test-id=city] input").setValue(city);
        $("[data-test-id=name] input").setValue(name);
        $("[data-test-id=date] input ").sendKeys(Keys.CONTROL + "a", Keys.DELETE);
        $("[data-test-id=date] input").setValue(date);

        $("[data-test-id=phone] input").setValue(phone);
        $("[data-test-id=agreement].checkbox").click();

        $(By.xpath("//*[text()='Запланировать']")).click();


        $("[data-test-id=success-notification] .notification__content")
                .shouldBe(visible)
                .shouldHave(matchText(date));

        testUser.setNewMeetingDate();
        String newDate = testUser.getMeetingDate();

        $("[data-test-id=date] input ").sendKeys(Keys.CONTROL + "a", Keys.DELETE);
        $("[data-test-id=date] input").setValue(newDate);
        $(By.xpath("//*[text()='Запланировать']")).click();


        $("[data-test-id=replan-notification].notification_visible .notification__content")
                .shouldBe(visible)
                .shouldBe(matchText("У вас уже запланирована встреча на другую дату. Перепланировать?"));

        $(By.xpath("//*[text()='Перепланировать']")).click();
        $("[data-test-id=success-notification] .notification__content")
                .shouldBe(visible)
                .shouldHave(matchText(newDate));
    }
}
