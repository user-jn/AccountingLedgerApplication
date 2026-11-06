package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
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

                // Split the line into parts using |
                String[] parts = line.split("\\|");

                // Make sure the line has all 5 fields
                if (parts.length == 5) {

                    try {
                        // Convert Strings into the correct data types
                        LocalDate date = LocalDate.parse(parts[0]);
                        LocalTime time = LocalTime.parse(parts[1]);
                        String description = parts[2];
                        String vendor = parts[3];
                        BigDecimal amount = new BigDecimal(parts[4]);

                        // Create a transaction object
                        Transaction transaction = new Transaction(
                                date, time, description, vendor, amount);

                        // Add the object to the ArrayList
                        transactions.add(transaction);

                    } catch (Exception e) {
                        System.out.println(
                                "Skipped a transaction with invalid data.");
                    }

                } else {
                    System.out.println(
                            "Skipped a transaction with missing fields.");
                }
            }

        } catch (IOException e) {
            System.out.println(
                    "Could not read transactions: " + e.getMessage());
        }

        // Return all transactions
        return transactions;
                }

                }


    }

}