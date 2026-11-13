
    import java.time.LocalDate;
    import java.time.LocalTime;
    import java.time.format.DateTimeFormatter;
    import java.util.Locale;
    import java.math.BigDecimal;

        public class Transaction {  // OBJECT HOLDS ONE ROW OF THE LEDGER

            private LocalDate date;
            private LocalTime time;
            private String description;
            private String vendor;
            private BigDecimal amount;

            // CONSTRUCTOR
            public Transaction(LocalDate date, LocalTime time, String description, String vendor, BigDecimal amount) {

                this.date = date;
                this.time = time;
                this.description = description;
                this.vendor = vendor;
                this.amount = amount;
            }

            // GETTERS - ALLOWS OTHER CLASSES TO READ OUR FIELDS

            // RETURNS THE DATE OF THE TRANSACTION
            public LocalDate getDate() {
                return date;
            }

            // Returns the time of the transaction
            public LocalTime getTime() {
                return time;
            }

            public String getDescription() {
                return description;
            }

            public String getVendor() {
                return vendor;
            }

            public BigDecimal getAmount() {
                return amount;
            }

            // SETTERS
            public void setDate(LocalDate date) {
                this.date = date;
            }

            public void setTime(LocalTime time) {
                this.time = time;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public void setVendor(String vendor) {
                this.vendor = vendor;
            }

            public void setAmount(BigDecimal amount) {
                this.amount = amount;
            }

            @Override
            public String toString() {   // FORMATTING THE DATE FOR THE CSV FILE

                String formattedDate = getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                String formattedTime = getTime().format(DateTimeFormatter.ofPattern("HH:mm:ss"));

                // RUBRIC FORMAT
                return String.format(
                        Locale.US,
                        "%s|%s|%s|%s|%.2f",
                        formattedDate,
                        formattedTime,
                        description,
                        vendor,
                        amount);
            }



            public String toDisplayString() {

                // Format payments and deposits
                String formattedAmount;

                if (amount.compareTo(BigDecimal.ZERO) < 0) {
                    formattedAmount = "-$" + amount.abs().setScale(2).toPlainString();
                } else {
                    formattedAmount = "$" + amount.setScale(2).toPlainString();
                }

                // Create aligned columns for console output
                return String.format(
                        Locale.US,
                        "%-12s %-10s %-26s %-22s %12s",
                        date,
                        time.format(DateTimeFormatter.ofPattern("HH:mm:ss")),
                        description,
                        vendor,
                        formattedAmount);
            }

        }





