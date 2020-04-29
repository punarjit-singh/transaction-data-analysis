package org.orgname.utils;

import org.orgname.models.Transaction;

import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TransactionsReader {

    public static List<Transaction> readTransactionsFromCSV(String fileName) {
        List<Transaction> securities = new ArrayList<Transaction>();

        Path path = Paths.get(fileName);

        try (BufferedReader br = Files.newBufferedReader(path, StandardCharsets.US_ASCII)) {

            String header = br.readLine();
            String line = br.readLine();


            while (line != null) {
                String[] items = line.split(",");
                Transaction security = createTransactions(items);

                securities.add(security);

                line = br.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return securities;
    }

    private static Transaction createTransactions(String[] data) {
        String timestamp = data[0];
        String security = data[1];
        String action = data[2];
        double price = Double.parseDouble(data[3]);

        return new Transaction(timestamp, security, action, price);
    }

}
