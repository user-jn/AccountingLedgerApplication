
package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class TransactionManager {

    // CSV file name and header
    private static final String FILE_NAME = "transactions.csv";
    private static final String HEADER =
            "date|time|description|vendor|amount";

    // List of transactions while the program is running
    private ArrayList<Transaction> transactions = new ArrayList<>();

    // METHOD 1: PREPARE CSV FILE
    private boolean prepareFile() {

        File file = new File(FILE_NAME);

        try {

            // Add the header if the CSV file is missing or empty
            if (!file.exists() || file.length() == 0) {

                try (BufferedWriter writer =
                             new BufferedWriter(new FileWriter(file))) {

                    writer.write(HEADER);
                    writer.newLine();
                }
            }

            return true;

        } catch (IOException e) {
            System.out.println(
                    "Could not prepare CSV file: " + e.getMessage());

            return false;
        }
    }

    // METHOD 2: ADD TRANSACTION
    // Create a transaction, save it, then add it to our list
    public boolean addTransaction(
            String description, String vendor, double amount) {

        Transaction transaction = new Transaction(
                LocalDate.now(),
                LocalTime.now(),
                description,
                vendor,
                amount);

        // Only add to the list after saving successfully
        if (saveTransactionToFile(transaction)) {

            transactions.add(transaction);
            return true;
        }

        return false;
    }

    // METHOD 3: SAVE TRANSACTION
    public boolean saveTransactionToFile(Transaction transaction) {

        // Stop if preparing the CSV file fails
        if (!prepareFile()) {
            return false;
        }

        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter(FILE_NAME, true))) {

            // Use the overridden toString() method
            writer.write(transaction.toString());

            // Move to the next line
            writer.newLine();

            return true;

        } catch (IOException e) {
            System.out.println(
                    "Error saving transaction: " + e.getMessage());

            return false;
        }
    }

        // METHOD 4- LOAD TRANSACTION FROM CSV
        public void loadTransactionFromFile() {

            transactions.clear();

            if (!prepareFile()) {
                return;
            }
            try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {

                String line;

                    // read each line until the end of the file
                while ((line = reader.readLine()) != null) {

                    // skip header and blank lines
                    if (line.isBlank() || line.equalsIgnoreCase(HEADER)) {
                        continue;
                    }

                }

            }

