package org.nasdaq.test;

import org.nasdaq.services.TransactionsService;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TransactionTest {

    private TransactionsService transactionsService;

    @BeforeTest
    public void setup() {
        transactionsService = new TransactionsService("src/main/resources/transaction.csv");
    }

    @Test(dataProvider = "getTestDataForSecuritiesList")
    public void shouldGetCorrectSecuritiesList(ArrayList<String> expectedSecurities, boolean getDistinct) {
        List<String> securitiesList = transactionsService.getSecuritiesList(getDistinct);
        Assert.assertEquals(securitiesList, expectedSecurities,
                "Error Message: getSecuritiesList(getDistinct: " + getDistinct + ") failed");
    }

    @Test(dataProvider = "getTestDataForAvgTradePrice")
    public void shouldReturnCorrectAvgTradePrice(String security, double expectedTradePrice) {
        final double DELTA = 0.001;
        double tradePrice = transactionsService.getAvgTradePrice(security);
        Assert.assertEquals(tradePrice, expectedTradePrice, DELTA,
                "Error Message: getAvgTradePrice() failed to return correct average trade price");
    }

    @Test(dataProvider = "getTestDataForLargestConsecutiveRise")
    public void shouldReturnLargestConsecutiveRise(String security, double expectedLargestConsecutiveRise) {
        double largestConsecutiveRise = transactionsService.getLargestConsecutiveRise(security);
        Assert.assertEquals(largestConsecutiveRise, expectedLargestConsecutiveRise,
                "Error Message: getLargestConsecutiveRise() failed to return " +
                        "correct largest consecutive rise");
    }

    /*
    Testing an additional method getAvgTradePriceAsString()
    in case the prices are to parsed as Strings will all trailing zeros.
     */
    @Test(dataProvider = "getTestDataForAvgTradePriceAsString")
    public void shouldReturnCorrectAvgTradePriceAsString(String security, String expectedTradePrice) {
        String tradePrice = transactionsService.getAvgTradePriceAsString(security);
        Assert.assertEquals(expectedTradePrice, tradePrice);
    }

    @DataProvider
    public Object[][] getTestDataForAvgTradePrice() {
        return new Object[][] {
                {"MSFT",93.23},
                {"GOOG",93.59},
                {"CTXS",92.30},
                {"NFLX",87.48}
        };
    }

    @DataProvider
    public Object[][] getTestDataForLargestConsecutiveRise() {
        return new Object[][] {
                {"MSFT",1.07},
                {"GOOG",2.14},
                {"CTXS",0.0},
                {"NFLX",2.21}
        };
    }

    @DataProvider
    public Object[][] getTestDataForSecuritiesList(){

        String[] securitiesAllArr =  {"MSFT", "GOOG", "MSFT", "MSFT",
                "CTXS", "MSFT", "GOOG", "MSFT",
                "MSFT", "MSFT", "GOOG", "MSFT",
                "NFLX", "NFLX", "NFLX", "MSFT",
                "MSFT", "NFLX", "MSFT", "GOOG",
                "GOOG", "GOOG", "NFLX", "NFLX",
                "NFLX", "NFLX", "GOOG", "GOOG",
                "GOOG", "MSFT", "MSFT", "NFLX",
                "NFLX", "GOOG", "GOOG", "MSFT",
                "GOOG", "NFLX", "NFLX", "NFLX"};

        ArrayList<String> securitiesAll = new ArrayList<String>(Arrays.asList(securitiesAllArr));

        String[] securitiesDistinctArr =  {"MSFT", "GOOG", "CTXS", "NFLX"};

        ArrayList<String> securitiesDistinct = new ArrayList<String>(Arrays.asList(securitiesDistinctArr));

        return new Object[][] {
                {securitiesAll, false},
                {securitiesDistinct, true}
        };
    }

    @DataProvider
    public Object[][] getTestDataForAvgTradePriceAsString() {
        return new Object[][] {
                {"MSFT","93.23"},
                {"GOOG","93.59"},
                {"CTXS","92.30"},
                {"NFLX","87.48"}
        };
    }

    @AfterTest
    public void teardown() {
        transactionsService = null;
    }

}
