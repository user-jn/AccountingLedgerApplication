
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static TransactionManager manager = new TransactionManager();
    private static ReportManager reports = new ReportManager();

    public static void main(String[] args) {

        manager.loadTransactionFromFile();
        homeScreen();
        scanner.close();
    }

    // HOME MENU: add deposits, payments, or open the ledger
    public static void homeScreen() {
        String choice;

        do {
            System.out.println("\n========== CLEARLEDGER ==========");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextLine().trim().toUpperCase();

            switch (choice) {
                case "D":
                    recordTransaction(false);
                    break;
                case "P":
                    recordTransaction(true);
                    break;
                case "L":
                    ledgerScreen();
                    break;
                case "X":
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");


                }
            } while (!choice.equals("X"));
         }

    // ADD  A POSITIVE DEPOSIT
    public static void addDeposit() {
        recordTransaction(false);
    }

    // MAKE A NEGATIVE PAYMENT
    public static void makePayment() {
        recordTransaction(true);
    }

    // METHOD: ask for transaction information once for both deposits and payments
    public static void recordTransaction(boolean isPayment) {
        String type = isPayment ? "PAYMENT" : "DEPOSIT";
        System.out.println("\n========== " + type + " ==========");

        String description = askRequired("Description: ");
        String vendor = askRequired("Vendor: ");
        BigDecimal amount = askPositiveAmount();

        // Payments are stored as negative amounts.
        if (isPayment) {
            amount = amount.negate();
        }


        // Save transaction through TransactionManager
        if (manager.addTransaction(description, vendor, amount)) {
            System.out.println(type + " saved successfully.");
        } else {
            System.out.println("Could not save transaction.");
        }
    }


    // Require text that is not empty and does not contain CSV's pipe symbol
    public static String askRequired(String question) {
        while (true) {
            String answer = ask(question);

            if (!answer.isEmpty() && !answer.contains("|")) {
                return answer;
            }

            System.out.println(
                    "Please enter text without the | symbol.");
        }
    }

    // Ask for a positive amount with no more than 2 decimals
    public static BigDecimal askPositiveAmount() {

        while (true) {

            String text = ask("Amount (example 25.50): ");
            try {

                BigDecimal amount = new BigDecimal(text);

                if (amount.signum() > 0 && amount.scale() <= 2) {
                    return amount;
                }

            } catch (NumberFormatException e) {
                // Invalid input; show message and retry
            }

            System.out.println(
                    "Enter a positive amount with at most 2 decimals.");
        }
    }

    // LEDGER SCREEN
    public static void ledgerScreen() {

        String choice = "";

        while (!choice.equals("H")) {

            showTitle("LEDGER");

            System.out.println("A) All Transactions");
            System.out.println("D) Deposits Only");
            System.out.println("P) Payments Only");
            System.out.println("R) Reports");
            System.out.println("H) Home");

            choice = getChoice();

            switch (choice) {

                case "A":
                    LedgerManager.displayAll(manager);
                    break;

                case "D":
                    LedgerManager.displayDeposits(manager);
                    break;

                case "P":
                    LedgerManager.displayPayments(manager);
                    break;

                case "R":
                    reportsScreen();
                    break;

                case "H":
                    break;

                default:
                    System.out.println("Invalid ledger option.");
            }
        }
    }

    // REPORTS SCREEN
    public static void reportsScreen() {

        String choice = "";

        while (!choice.equals("0")) {

            showTitle("REPORTS");

            System.out.println("1) Month To Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year To Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search by Vendor");
            System.out.println("6) Custom Search (BONUS)");
            System.out.println("0) Back to Ledger");

            choice = getChoice();

            switch (choice) {

                case "1":
                case "2":
                case "3":
                case "4":
                    displayTransactions(
                            reports.dateReport(
                                    manager.getAllTransactions(),
                                    choice));
                    break;

                case "5":
                    String vendor = ask("Vendor name: ");

                    displayTransactions(
                            reports.searchVendor(
                                    manager.getAllTransactions(),
                                    vendor));
                    break;

                case "6":
                    customSearchScreen();
                    break;

                case "0":
                    break;

                default:
                    System.out.println("Please choose 0 through 6.");
            }
        }
    }

    // BONUS: CUSTOM SEARCH
    public static void customSearchScreen() {

        showTitle("CUSTOM SEARCH");

        System.out.println("Press Enter to skip a field.");

        LocalDate start =
                askOptionalDate("Start date (yyyy-MM-dd): ");

        LocalDate end =
                askOptionalDate("End date (yyyy-MM-dd): ");

        // Check the order of the entered dates
        if (start != null && end != null && start.isAfter(end)) {

            System.out.println(
                    "Start date must not be after end date.");
            return;
        }

        String description = ask("Description: ");
        String vendor = ask("Vendor: ");

        BigDecimal amount = askOptionalAmount(
                "Exact amount (negative for payments): ");

        // Filter and display matching transactions
        ArrayList<Transaction> matches = reports.customSearch(
                manager.getAllTransactions(),
                start,
                end,
                description,
                vendor,
                amount);

        displayTransactions(matches);
    }

    // Return null when user skips the date
    public static LocalDate askOptionalDate(String question) {

        while (true) {

            String text = ask(question);

            if (text.isEmpty()) {
                return null;
            }

            try {
                return LocalDate.parse(text);

            } catch (DateTimeParseException e) {

                System.out.println(
                        "Invalid date. Use yyyy-MM-dd, or Enter to skip.");
            }
        }
    }

    // Return null when user skips the amount
    public static BigDecimal askOptionalAmount(String question) {

        while (true) {

            String text = ask(question);

            if (text.isEmpty()) {
                return null;
            }

            try {
                return new BigDecimal(text);

            } catch (NumberFormatException e) {

                System.out.println(
                        "Enter a number, or press Enter to skip.");
            }
        }
    }

    // Reusable method to display ledger and report results
    public static void displayTransactions(
            ArrayList<Transaction> transactions) {

        System.out.println();

        System.out.println(
                "DATE         TIME      DESCRIPTION            VENDOR                 AMOUNT");

        System.out.println(
                "-------------------------------------------------------------------------");

        if (transactions.isEmpty()) {
            System.out.println("No matching transactions found.");
        }

        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }

        // Show financial totals after the transactions
        showSummary(transactions);
    }

    // Calculate deposits, payments, and net total
    public static void showSummary(
            ArrayList<Transaction> transactions) {

        BigDecimal deposits = BigDecimal.ZERO;
        BigDecimal payments = BigDecimal.ZERO;

        int depositCount = 0;
        int paymentCount = 0;

        for (Transaction transaction : transactions) {

            if (transaction.getAmount().signum() > 0) {

                deposits = deposits.add(transaction.getAmount());
                depositCount++;

            } else if (transaction.getAmount().signum() < 0) {

                payments = payments.add(
                        transaction.getAmount().abs());

                paymentCount++;
            }
        }

        System.out.println(
                "-------------------------------------------------------------------------");

        System.out.println(
                "Deposits (" + depositCount + "): $" +
                        deposits.setScale(2));

        System.out.println(
                "Payments (" + paymentCount + "): $" +
                        payments.setScale(2));

        System.out.println(
                "Net total: $" +
                        deposits.subtract(payments).setScale(2));
    }

        // adding helper methods - ask the user a question
        public static String ask(String question) {
            System.out.print(question);
            return scanner.nextLine().trim();
        }
        // METHOD: Get the user's menu choice
        public static String getChoice() {
            return ask("Choose an option: ").toUpperCase();

        }
            // // METHOD: Display a menu title
            public static void showTitle (String title){
                System.out.println("\n========== CLEARLEDGER | "
                        + title + " ==========");
            }

        }
