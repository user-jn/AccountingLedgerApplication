

public class LedgerManager {

    // METHOD 1: DISPLAY ALL TRANSACTIONS
    public static void displayAll(TransactionManager manager) {

        // Loop through every transaction
        for (Transaction t : manager.getAllTransactions()) {

            // Calls our overridden toString() method
            System.out.println(t.toDisplayString());
        }
    }

    // METHOD 2: DISPLAY DEPOSITS
    public static void displayDeposits(TransactionManager manager) {

        // Loop through positive transactions
        for (Transaction t : manager.getDeposits()) {
            System.out.println(t.toDisplayString());
        }

    }

    // METHOD 3: DISPLAY PAYMENTS
    public static void displayPayments(TransactionManager manager) {
        // Loop through negative transactions
        for (Transaction t : manager.getPayments()) {
            System.out.println(t.toDisplayString());

        }
    }

}
