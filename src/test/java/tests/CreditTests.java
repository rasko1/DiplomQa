package tests;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import data.Data;
import data.SQL;
import pages.Start;
import pages.Credit;
import com.codeborne.selenide.logevents.SelenideLogger;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class CreditTests {
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
    void creditPositiveAllFieldValidApproved() {
        startPage.credit();
        var cardInfo = Data.getApprovedCard();
        var creditPage = new Credit();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationApproved();
        assertEquals("APPROVED", SQL.getCreditRequestStatus());
    }

    @Test
    void creditPositiveAllFieldValidDeclined() {
        startPage.credit();
        var cardInfo = Data.getDeclinedCard();
        var creditPage = new Credit();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationFailure();
        assertEquals("DECLINED", SQL.getCreditRequestStatus());
    }

    @Test
    void creditNegativeAllFieldEmpty() {
        startPage.credit();
        var cardInfo = Data.getEmptyCard();
        var creditPage = new Credit();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationWrongFormat4Fields();

        assertEquals("0", SQL.getOrderCount());

    }

    @Test
    void creditNegativeNumberCard15Symbols() {
        startPage.credit();
        var cardInfo = Data.getNumberCard15Symbols();
        var creditPage = new Credit();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationWrongFormat();
        assertEquals("0", SQL.getOrderCount());
    }

    @Test
    void creditNegativeCardNotInDatabase() {
        startPage.credit();
        var cardInfo = Data.getCardNotInDatabase();
        var creditPage = new Credit();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationFailure();
        assertEquals("0", SQL.getOrderCount());
    }

    @Test
    void creditNegativeMonth1Symbol() {
        startPage.credit();
        var cardInfo = Data.getCardMonth1Symbol();
        var creditPage = new Credit();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationWrongFormat();
        assertEquals("0", SQL.getOrderCount());
    }

    @Test
    void creditNegativeMonthOver12() {
        startPage.credit();
        var cardInfo = Data.getCardMonthOver12();
        var creditPage = new Credit();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationExpirationDateError();
        assertEquals("0", SQL.getOrderCount());
    }

    @Test
    void creditNegativeMonth00ThisYear() {
        startPage.credit();
        var cardInfo = Data.getCardMonth00ThisYear();
        var creditPage = new Credit();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationExpirationDateError();
        assertEquals("0", SQL.getOrderCount());
    }
    @Test
    void creditNegativeMonth00OverThisYear() {
        startPage.credit();
        var cardInfo = Data.getCardMonth00OverThisYear();
        var creditPage = new Credit();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationExpirationDateError();
        assertEquals("0", SQL.getOrderCount());
    }

    @Test
    void buyNegativeYear00() {
        startPage.credit();
        var cardInfo = Data.getCardYear00();
        var creditPage = new Credit();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationExpiredError();
        assertEquals("0", SQL.getOrderCount());
    }

    @Test
    void creditNegativeYear1Symbol() {
        startPage.credit();
        var cardInfo = Data.getCardYear1Symbol();
        var creditPage = new Credit();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationWrongFormat();
        assertEquals("0", SQL.getOrderCount());
    }

    @Test
    void creditNegativeYearUnderThisYear() {
        startPage.credit();
        var cardInfo = Data.getCardYearUnderThisYear();
        var creditPage = new Credit();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationExpiredError();
        assertEquals("0", SQL.getOrderCount());
    }

    @Test
    void creditNegativeYearOverThisYearOn6() {
        startPage.credit();
        var cardInfo = Data.getCardYearOverThisYearOn6();
        var creditPage = new Credit();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationExpirationDateError();
        assertEquals("0", SQL.getOrderCount());
    }

    @Test
    void creditNegativeCvv1Symbol() {
        startPage.credit();
        var cardInfo = Data.getCardCvv1Symbol();
        var creditPage = new Credit();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationWrongFormat();
        assertEquals("0", SQL.getOrderCount());
    }

    @Test
    void creditNegativeCvv2Symbols() {
        startPage.credit();
        var cardInfo = Data.getCardCvv2Symbols();
        var creditPage = new Credit();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationWrongFormat();
        assertEquals("0", SQL.getOrderCount());
    }

    @Test
    void creditNegativeOwner1Word() {
        startPage.credit();
        var cardInfo = Data.getCardHolder1Word();
        var creditPage = new Credit();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationWrongFormat();
        assertEquals("0", SQL.getOrderCount());
    }

    @Test
    void creditNegativeOwnerCirillic() {
        startPage.credit();
        var cardInfo = Data.getCardHolderCirillic();
        var creditPage = new Credit();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationWrongFormat();
        assertEquals("0", SQL.getOrderCount());
    }

    @Test
    void creditNegativeOwnerNumeric() {
        startPage.credit();
        var cardInfo = Data.getCardHolderNumeric();
        var creditPage = new Credit();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationWrongFormat();
        assertEquals("0", SQL.getOrderCount());
    }

    @Test
    void creditNegativeOwnerSpecialSymbols() {
        startPage.credit();
        var cardInfo = Data.getCardSpecialSymbols();
        var creditPage = new Credit();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationWrongFormat();
        assertEquals("0", SQL.getOrderCount());
    }
}