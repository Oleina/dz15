package com.example;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class MtsByTest {

    @BeforeAll
    public static void setup() {
        Configuration.startMaximized = true;
    }

    @Test
    public void testOnlineTopUpBlockTitle() {
        open("https://mts.by/");
        String expectedTitle = "Онлайн пополнение без комиссии";
        $x("//h2[contains(text(), 'Онлайн пополнение без комиссии')]").shouldHave(Condition.text(expectedTitle));
    }

    @Test
    public void testPaymentSystemsLogos() {
        open("https://mts.by/");
        $x("//img[@alt='Visa']").shouldBe(Condition.visible);
        $x("//img[@alt='Mastercard']").shouldBe(Condition.visible);
        $x("//img[@alt='WebPay']").shouldBe(Condition.visible);
        // Add other payment systems logos here
    }

    @Test
    public void testMoreInfoLink() {
        open("https://mts.by/");
        $(By.linkText("Подробнее о сервисе")).shouldBe(Condition.visible).click();
        switchTo().window(1); // Switch to the new tab
        $x("//h1[contains(text(), 'Подробная информация о сервисе')]").shouldBe(Condition.visible);
        closeWindow(); // Close the new tab
        switchTo().window(0); // Switch back to the main window
    }

    @Test
    public void testTopUpForm() {
        open("https://mts.by/");
        $(By.id("serviceCategory")).selectOption("Услуги связи");
        $(By.id("phoneNumber")).setValue("297777777");
        $(By.id("continueButton")).click();
        $x("//div[contains(text(), 'Введите сумму пополнения')]").shouldBe(Condition.visible);
    }
}
