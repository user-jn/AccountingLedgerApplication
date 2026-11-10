
    package com.pluralsight;


    import java.time.LocalDate;
    import java.time.LocalTime;
    import java.time.format.DateTimeFormatter;
    import java.util.Locale;

        public class Transaction {
            // OBJECT HOLDS ONE ROW OF THE LEDGER
            private LocalDate date;
            private LocalTime time;
            private String description;
            private String vendor;
            private double amount;

            // CONSTRUCTOR
            public Transaction(LocalDate date, LocalTime time, String description, String vendor, double amount) {

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

            public double getAmount() {
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

            public void setAmount(double amount) {
                this.amount = amount;
            }

            // CONVERTING TRANSACTION INTO A CSV FILE
            @Override
            public String toString() {
                // FORMATTING THE DATE FOR THE CSV FILE
                // FORMATTING THE TIME FOR THE CSV FILE

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

        }



