package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TransactionFileManager {

    // Load the CSV into an ArrayList. Each line becomes one Transaction.

    // Name of the file where transactions are stored
    private static final String FILE_NAME = "transactions.csv";

    // Read all transactions from the CSV file
    public static ArrayList<Transaction> loadTransactions() {

        // ArrayList stores all transaction objects
        ArrayList<Transaction> transactions = new ArrayList<>();

        File file = new File(FILE_NAME);

        // If the file doesn't exist, return an empty list
        if (!file.exists()) {
            return transactions;
        }

        // try-with-resources automatically closes the BufferedReader.
        try (BufferedReader reader =
                     new BufferedReader(new FileReader(FILE_NAME))) {

            String line;

            while ((line = reader.readLine()) != null) {


    }

}