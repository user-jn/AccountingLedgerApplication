import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;

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
            String description, String vendor, BigDecimal amount) {

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

        // METHOD 4: LOAD TRANSACTION FROM CSV
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

                    // split the line into five parts
                    String[] parts = line.split("\\|");

                    if (parts.length == 5) {

                        try {
                            LocalDate date = LocalDate.parse(parts[0]);
                            LocalTime time = LocalTime.parse(parts[1]);
                            String description = parts[2];
                            String vendor = parts[3];
                            BigDecimal amount = new BigDecimal(parts[4]); //updated double with BigDecimal to use compare

                            // ignore invalid numeric values

                            Transaction transaction = new Transaction(
                                    date, time, description, vendor, amount);

                            transactions.add(transaction);

                        } catch (Exception e) {
                            System.out.println("Skipping invalid transaction.");

                        }

                    }
                }
            } catch (IOException e) {
                System.out.println("Error reading file: " + e.getMessage());
            }
        }
        // METHOD 5: RETURN ALL TRANSACTIONS NEWEST FIRST
        public ArrayList<Transaction> getAllTransactions() {

            ArrayList<Transaction> results =
                    new ArrayList<>(transactions);

            // sort by date, then time, newest first
            results.sort(
                    Comparator.comparing(Transaction::getDate)
                            .thenComparing(Transaction::getTime)
                            .reversed());

            return results;
        }

        // METHOD 6: RETURN DEPOSITS ONLY
        public ArrayList<Transaction> getDeposits() {

            ArrayList<Transaction> deposits = new ArrayList<>();

            for (Transaction transaction : getAllTransactions()) {

                // Positive amounts are deposits
                if (transaction.getAmount().compareTo(BigDecimal.ZERO) > 0) {
                    deposits.add(transaction);
                }
            }

        return deposits;
    }
            // METHOD 7: RETURN PAYMENTS ONLY
            public ArrayList<Transaction> getPayments() {

            ArrayList<Transaction> payments = new ArrayList<>();

            for (Transaction transaction : getAllTransactions()) {

            // Negative amounts are payments
            if (transaction.getAmount().compareTo(BigDecimal.ZERO) < 0) {
                payments.add(transaction);
            }
        }
            return payments;
    }

}

