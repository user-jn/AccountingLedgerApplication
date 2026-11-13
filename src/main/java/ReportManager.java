import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

public class ReportManager {

    // METHOD 1: Reports 1-4 all use the same date filtering method
    public ArrayList<Transaction> dateReport(
            ArrayList<Transaction> transactions, String choice) {

        LocalDate today = LocalDate.now();
        LocalDate start;
        LocalDate end = today;

        switch (choice) {

            case "1":// Month to date
                start = today.withDayOfMonth(1);
                break;

            case "2":// Previous month
                start = today.minusMonths(1).withDayOfMonth(1);
                end = start.withDayOfMonth(start.lengthOfMonth());
                break;

            case "3":// Year to date
                start = today.withDayOfYear(1);
                break;

            case "4":// Previous year
                start = LocalDate.of(today.getYear() - 1, 1, 1);
                end = LocalDate.of(today.getYear() - 1, 12, 31);
                break;

            default:
                return new ArrayList<>();
        }

        // Reuse our date search method
        return searchDates(transactions, start, end);
    }

    // METHOD 2: Return transactions within a date range
    public ArrayList<Transaction> searchDates(
            ArrayList<Transaction> transactions,
            LocalDate start, LocalDate end) {

        ArrayList<Transaction> results = new ArrayList<>();

        for (Transaction transaction : transactions) {

            LocalDate date = transaction.getDate();

            if (!date.isBefore(start) && !date.isAfter(end)) {
                results.add(transaction);
            }
        }

        return results;
    }

    // METHOD 3: Search for transactions matching a vendor
    public ArrayList<Transaction> searchVendor(
            ArrayList<Transaction> transactions, String vendor) {

        ArrayList<Transaction> results = new ArrayList<>();

        for (Transaction transaction : transactions) {

            if (transaction.getVendor().toLowerCase()
                    .contains(vendor.toLowerCase())) {

                results.add(transaction);
            }
        }

        return results;
    }

    // BONUS: Search by up to five different fields
    // Empty fields are ignored
    public ArrayList<Transaction> customSearch(
            ArrayList<Transaction> transactions,
            LocalDate start,
            LocalDate end,
            String description,
            String vendor,
            BigDecimal amount) {

        ArrayList<Transaction> results = new ArrayList<>();

        // Check each transaction against the chosen filters
        for (Transaction transaction : transactions) {

            // Assume it matches until a check fails
            boolean matches = true;

            if (start != null &&
                    transaction.getDate().isBefore(start)) {
                matches = false;
            }

            if (end != null &&
                    transaction.getDate().isAfter(end)) {
                matches = false;
            }

            if (!description.isEmpty() &&
                    !transaction.getDescription().toLowerCase()
                            .contains(description.toLowerCase())) {
                matches = false;
            }

            if (!vendor.isEmpty() &&
                    !transaction.getVendor().toLowerCase()
                            .contains(vendor.toLowerCase())) {
                matches = false;
            }

            if (amount != null &&
                    transaction.getAmount().compareTo(amount) != 0) {
                matches = false;
            }

            // Add only transactions passing all active filters
            if (matches) {
                results.add(transaction);
            }
        }

        return results;
    }
}
