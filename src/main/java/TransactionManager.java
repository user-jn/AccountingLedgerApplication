package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class TransactionManager {

    // Load the CSV into an ArrayList. Each line becomes one Transaction.
    // Name of the file where transactions are stored
    private static final String FILE_NAME = "transactions.csv";
    private static final String HEADER = "date|time|description|vendor|amount";

    // List of transactions while the program is running
    private ArrayList<Transaction> transactions = new ArrayList<>();

    // Prepare the csv before reading or writing
    // Return false if file cannot be created or opened
    private boolean prepareFile() {
        File file = new File(FILE_NAME);

        try {
            // add the header when the csv file is missing or empty
            if (!file.exists() || file.length() == 0) {
                try (BufferedWriter writer =
                             new BufferedWriter(new FileWriter(file))) {
                    writer.write(HEADER);
                    writer.newLine();
                }
            }
            return true;

        } catch (IOException e) {
            System.out.println("Could not prepare CSV file: " + e.getMessage());
            return false;

            // create a transaction, save it, then add it to the list
            public boolean addTransaction(String description, String vendor, double amount) {
                Transaction transaction = new Transaction(
                        LocalDate.now(), LocalTime.now(), description, vendor, amount);

                // Don't add a record in memory if saving failed
                if (saveTransactionToFile(transaction)) {
                    transactions.add(transaction);
                    return true;
                }
                return false;
            }


            }
    }