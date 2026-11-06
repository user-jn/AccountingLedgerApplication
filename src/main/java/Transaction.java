package com.pluralsight;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/** One ledger record: a positive deposit or a negative payment. */
public class Transaction {
    private final LocalDate date;
    private final LocalTime time;
    private final String description;
    private final String vendor;
    private final BigDecimal amount;

    public Transaction(LocalDate date, LocalTime time, String description, String vendor, BigDecimal amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
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

    public String toCsv() {
        return date + "|" + time + "|" + description + "|" + vendor + "|" + String.format(java.util.Locale.US, "%.2f", amount);


    }

    // Controls how one transaction looks when printed on screen.

    @Override
    public String toString() {

        // Format the transaction for saving to the CSV file
        String formattedDate = getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String formattedTime = getTime().format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        return String.format("%s|%s|%s|%s|%.2f ", formattedDate, formattedTime, description, vendor, amount);
    }
}