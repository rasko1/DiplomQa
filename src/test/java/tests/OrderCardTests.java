package tests;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import data.Data;
import data.SQL;
import pages.OrderCard;
import pages.Start;
import com.codeborne.selenide.logevents.SelenideLogger;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderCardTests {
    Start startPage = open("http://localhost:8080/", Start.class);

    @AfterEach
    public void cleanBase() {
        SQL.clearDB();
    }

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }


    @Test
    void buyPositiveAllFieldValidApproved() {
        startPage.orderCard();
        var cardInfo = Data.getApprovedCard();
        var orderCardPage = new OrderCard();
        orderCardPage.insertCardData(cardInfo);
        orderCardPage.waitNotificationApproved();
        assertEquals("APPROVED", SQL.getPaymentStatus());
    }

    @Test
    void buyPositiveAllFieldValidDeclined() {
        startPage.orderCard();
        var cardInfo = Data.getDeclinedCard();
        var orderCardPage = new OrderCard();
        orderCardPage.insertCardData(cardInfo);
        orderCardPage.waitNotificationFailure();
        assertEquals("DECLINED", SQL.getPaymentStatus());

    }

    @Test
    void buyNegativeAllFieldEmpty() {
        startPage.orderCard();
        var cardInfo = Data.getEmptyCard();
        var orderCardPage = new OrderCard();
        orderCardPage.insertCardData(cardInfo);
        orderCardPage.waitNotificationWrongFormat4Fields();
        assertEquals("0", SQL.getOrderCount());

    }

    @Test
    void buyNegativeNumberCard15Symbols() {
        startPage.orderCard();
        var cardInfo = Data.getNumberCard15Symbols();
        var orderCardPage = new OrderCard();
        orderCardPage.insertCardData(cardInfo);
        orderCardPage.waitNotificationWrongFormat();
        assertEquals("0", SQL.getOrderCount());
    }

    @Test
    void buyNegativeCardNotInDatabase() {
        startPage.orderCard();
        var cardInfo = Data.getCardNotInDatabase();
        var orderCardPage = new OrderCard();
        orderCardPage.insertCardData(cardInfo);
        orderCardPage.waitNotificationFailure();
        assertEquals("0", SQL.getOrderCount());
    }

    @Test
    void buyNegativeMonth1Symbol() {
        startPage.orderCard();
        var cardInfo = Data.getCardMonth1Symbol();
        var orderCardPage = new OrderCard();
        orderCardPage.insertCardData(cardInfo);
        orderCardPage.waitNotificationWrongFormat();
        assertEquals("0", SQL.getOrderCount());
    }

    @Test
    void buyNegativeMonthOver12() {
        startPage.orderCard();
        var cardInfo = Data.getCardMonthOver12();
        var orderCardPage = new OrderCard();
        orderCardPage.insertCardData(cardInfo);
        orderCardPage.waitNotificationExpirationDateError();
        assertEquals("0", SQL.getOrderCount());
    }

    @Test
    void buyNegativeMonth00ThisYear() {
        startPage.orderCard();
        var cardInfo = Data.getCardMonth00ThisYear();
        var orderCardPage = new OrderCard();
        orderCardPage.insertCardData(cardInfo);
        orderCardPage.waitNotificationExpirationDateError();
        assertEquals("0", SQL.getOrderCount());
    }
    @Test
    void creditNegativeMonth00OverThisYear() {
        startPage.orderCard();
        var cardInfo = Data.getCardMonth00OverThisYear();
        var orderCardPage = new OrderCard();
        orderCardPage.insertCardData(cardInfo);
        orderCardPage.waitNotificationExpirationDateError();
        assertEquals("0", SQL.getOrderCount());
    }

    @Test
    void buyNegativeYear00() {
        startPage.orderCard();
        var cardInfo = Data.getCardYear00();
        var orderCardPage = new OrderCard();
        orderCardPage.insertCardData(cardInfo);
        orderCardPage.waitNotificationExpiredError();
        assertEquals("0", SQL.getOrderCount());
    }

    @Test
    void buyNegativeYear1Symbol() {
        startPage.orderCard();
        var cardInfo = Data.getCardYear1Symbol();
        var orderCardPage = new OrderCard();
        orderCardPage.insertCardData(cardInfo);
        orderCardPage.waitNotificationWrongFormat();
        assertEquals("0", SQL.getOrderCount());
    }

    @Test
    void buyNegativeYearUnderThisYear() {
        startPage.orderCard();
        var cardInfo = Data.getCardYearUnderThisYear();
        var orderCardPage = new OrderCard();
        orderCardPage.insertCardData(cardInfo);
        orderCardPage.waitNotificationExpiredError();
        assertEquals("0", SQL.getOrderCount());
    }

    @Test
    void buyNegativeYearOverThisYearOn6() {
        startPage.orderCard();
        var cardInfo = Data.getCardYearOverThisYearOn6();
        var orderCardPage = new OrderCard();
        orderCardPage.insertCardData(cardInfo);
        orderCardPage.waitNotificationExpirationDateError();
        assertEquals("0", SQL.getOrderCount());
    }

    @Test
    void buyNegativeCvv1Symbol() {
        startPage.orderCard();
        var cardInfo = Data.getCardCvv1Symbol();
        var orderCardPage = new OrderCard();
        orderCardPage.insertCardData(cardInfo);
        orderCardPage.waitNotificationWrongFormat();
        assertEquals("0", SQL.getOrderCount());
    }

    @Test
    void buyNegativeCvv2Symbols() {
        startPage.orderCard();
        var cardInfo = Data.getCardCvv2Symbols();
        var orderCardPage = new OrderCard();
        orderCardPage.insertCardData(cardInfo);
        orderCardPage.waitNotificationWrongFormat();
        assertEquals("0", SQL.getOrderCount());
    }

    @Test
    void buyNegativeOwner1Word() {
        startPage.orderCard();
        var cardInfo = Data.getCardHolder1Word();
        var orderCardPage = new OrderCard();
        orderCardPage.insertCardData(cardInfo);
        orderCardPage.waitNotificationWrongFormat();
        assertEquals("0", SQL.getOrderCount());
    }

    @Test
    void buyNegativeOwnerCirillic() {
        startPage.orderCard();
        var cardInfo = Data.getCardHolderCirillic();
        var orderCardPage = new OrderCard();
        orderCardPage.insertCardData(cardInfo);
        orderCardPage.waitNotificationWrongFormat();
        assertEquals("0", SQL.getOrderCount());
    }

    @Test
    void buyNegativeOwnerNumeric() {
        startPage.orderCard();
        var cardInfo = Data.getCardHolderNumeric();
        var orderCardPage = new OrderCard();
        orderCardPage.insertCardData(cardInfo);
        orderCardPage.waitNotificationWrongFormat();
        assertEquals("0", SQL.getOrderCount());
    }

    @Test
    void buyNegativeOwnerSpecialSymbols() {
        startPage.orderCard();
        var cardInfo = Data.getCardSpecialSymbols();
        var orderCardPage = new OrderCard();
        orderCardPage.insertCardData(cardInfo);
        orderCardPage.waitNotificationWrongFormat();
        assertEquals("0", SQL.getOrderCount());
    }
}