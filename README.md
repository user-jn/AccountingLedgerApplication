Accounting Ledger Application

Project Description

A Java console-based accounting application that allows users to record deposits, track payments, view financial transactions, and generate reports.

The application uses object-oriented programming (OOP), ArrayLists, BigDecimal, and CSV file handling to manage financial records.

Features

Add deposits (positive transactions)

Record payments (negative transactions)

Display all transactions, newest first

View deposits and payments separately

Generate month-to-date reports

Generate previous-month reports

Generate year-to-date reports

Generate previous-year reports

Search transactions by vendor

Bonus: Custom transaction search

Input validation and exception handling

Automatically save transactions to a CSV file

Technologies Used

Java

IntelliJ IDEA

Git and GitHub

Project Structure

Main.java — Controls menus, user input, and application navigation.

Transaction.java — Represents a transaction with its date, time, description, vendor, and amount.

TransactionManager.java — Creates, saves, loads, sorts, and filters transactions.

LedgerManager.java — Displays transaction records.

ReportManager.java — Generates date reports and performs vendor and custom searches.

transactions.csv — Stores transaction data between sessions.

How to Run

Clone or download the repository.

Open the project in IntelliJ IDEA.

Run Main.java.

Select an option from the Home menu.

Follow the console prompts to record or view transactions.

The program creates transactions.csv in its working directory if the file does not already exist.


Interesting Code

Screenshots

## Screenshots

### Home Menu
![Home Menu](screenshots/home-menu.png)

### Ledger Menu
![Ledger Menu](screenshots/ledger-menu.png)

### Reports Menu
![Reports Menu](screenshots/reports-menu.png)

### Transaction Example
![Transaction Example](screenshots/transaction-example.png)


What I Learned

Through this project, I practiced object-oriented programming, class relationships, constructors, ArrayLists, file handling, exception handling, input validation, and organizing methods across multiple Java classes.

I also learned how to separate the user interface, transaction management, ledger display, and reporting responsibilities.