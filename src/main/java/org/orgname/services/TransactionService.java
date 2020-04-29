package org.orgname.services;

import org.orgname.models.Transaction;
import org.orgname.utils.CalculationsHelper;
import org.orgname.utils.TransactionsReader;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

public class TransactionService {

    private List<Transaction> transactions;

    public TransactionService(String fileName) {
        this.transactions = TransactionsReader.readTransactionsFromCSV(fileName);
    }

    /**
     * @param getDistinct Pass true to get distinct securitiesList from transactions
     * @return List of String containing securitiesList
     */
    public List<String> getSecuritiesList(boolean getDistinct) {
        List<String> securitiesList = new ArrayList<>();
        transactions.forEach(transaction -> securitiesList.add(transaction.getSecurity()));
        return getDistinct ?  securitiesList.stream().distinct().collect(Collectors.toList())
                : securitiesList;
    }

    /**
     * @param security String of security to get average price for
     * @return double of average trade price
     */
    public double getAvgTradePrice(String security) {
        List<Transaction> filteredTransactions = transactions.stream().filter(sec -> sec.getSecurity()
                .equalsIgnoreCase(security)).collect(Collectors.toList());
        List<Double> prices = new ArrayList<>();
        filteredTransactions.forEach(stock -> prices.add(stock.getPrice()));
        return (double) CalculationsHelper.calculateAverage(prices, false);
    }

    /**
     * @param security String of security to get average price for
     * @return String of average trade price
     */
    public String getAvgTradePriceAsString(String security) {
        List<Transaction> filteredTransactions = transactions.stream().filter(sec -> sec.getSecurity()
                .equalsIgnoreCase(security)).collect(Collectors.toList());
        List<Double> prices = new ArrayList<>();
        filteredTransactions.forEach(stock -> prices.add(stock.getPrice()));
        return (String) CalculationsHelper.calculateAverage(prices, true);
    }

    /**
     * @param security String of security to get largest consecutive rise
     * @return double of largest consecutive rise
     */
    public double getLargestConsecutiveRise(String security) {
        List<Double> consecutiveRises = getAllConsecutiveRises(security);
        return Collections.max(consecutiveRises);
    }

    /**
     * @param security String of security to get largest consecutive rise
     * @return List of Double containing consecutive rises
     */
    private List<Double> getAllConsecutiveRises(String security) {

        List<Double> allConsecutiveRises = new ArrayList<>();
        double currentConsecutiveRise = 0.0;
        DecimalFormat df = new DecimalFormat("0.00");

        for (int nextIndex = 0; nextIndex < transactions.size(); nextIndex++) {

            int secondNextIndex = nextIndex + 1;
            if(secondNextIndex < transactions.size() && transactions.get(nextIndex).getSecurity().equalsIgnoreCase(security)
                    && transactions.get(secondNextIndex).getSecurity().equalsIgnoreCase(security)
                    && transactions.get(secondNextIndex).getPrice() >  transactions.get(nextIndex).getPrice()) {
                currentConsecutiveRise += transactions.get(secondNextIndex).getPrice() - transactions.get(nextIndex).getPrice();
            } else {
                currentConsecutiveRise = Double.parseDouble(df.format(currentConsecutiveRise));
                allConsecutiveRises.add(currentConsecutiveRise);
                currentConsecutiveRise = 0.0;
            }
        }

        return allConsecutiveRises;
    }
}
