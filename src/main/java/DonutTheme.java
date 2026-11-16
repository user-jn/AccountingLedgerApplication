public class DonutTheme {

    // Welcome banner displayed when the application starts


    // show welcome
    public static void showWelcome() { //ascii style

        System.out.println();
        System.out.println("        .-'''''''-.");
        System.out.println("      .'  .-'''-.  '.");
        System.out.println("     /  .'       '.  \\");
        System.out.println("    |  |   DOUGH   |  |");
        System.out.println("     \\  '.       .'  /");
        System.out.println("      '.  '-...-'  .'");
        System.out.println("        '-.......-'");
        System.out.println();
        System.out.println("       🍩 DOUGH & CO. 🍩");
        System.out.println("     Freshly Glazed Finances");
        System.out.println("====================================");
        System.out.println(" Every dollar deserves a little dough!");
        System.out.println("====================================");
    }

         // display the main navigation menu
        public static void showHomeMenu() {

            System.out.println();
            System.out.println("========== 🍩 THE DONUT COUNTER ==========");
            System.out.println(" [D] 🍩 Ring Up Some Dough");
            System.out.println(" [P] 💸 Pay the Dough Bills");
            System.out.println(" [L] 📒 Open the Dough Book");
            System.out.println(" [X] 👋 Close Up Shop");
            System.out.println("==========================================");
            System.out.print("🍬 What are we baking today? ");
        }

             // Show ledger Menu
            public static void showLedgerMenu() {

                System.out.println();
                System.out.println("========== 📒 THE DOUGH BOOK ==========");
                System.out.println(" [A] 🧾 Full Batch History");
                System.out.println(" [D] 💰 Sweet Sales");
                System.out.println(" [P] 💸 Shop Expenses");
                System.out.println(" [R] 📊 The Dough Report");
                System.out.println(" [H] 🏠 Back to the Counter");
                System.out.println("=======================================");
                System.out.print("🍩 Pick your next batch: ");
            }


                // show reportsMenu

            public static void showReportsMenu() {

                System.out.println();
                System.out.println("========== 📊 THE DOUGH REPORT ==========");
                System.out.println(" [1] 🍩 This Month's Batch");
                System.out.println(" [2] 🧁 Last Month's Batch");
                System.out.println(" [3] 🍯 This Year's Dough");
                System.out.println(" [4] 🍪 Last Year's Dough");
                System.out.println(" [5] 🔎 Find a Dough Supplier");
                System.out.println(" [6] 🔍 Build Your Own Batch");
                System.out.println("=========================================");
                System.out.print("🍬 Which report sounds sweet? ");


            }

                     // Display custom message after successfully saving a transaction
                     public static void showSuccess() {

                         System.out.println();
                         System.out.println("🍩 Fresh out of the oven!");
                         System.out.println("✅ Your transaction has been saved.");
                         System.out.println("====================================");
                     }

                        //show invalid option

                    public static void showInvalidOption() {

                        System.out.println();
                        System.out.println("🍩 Oops! That donut isn't on the menu.");
                        System.out.println("Please choose a valid option.");
                    }


                             // show goodbye message

                     public static void showGoodbye() {

                         System.out.println();
                         System.out.println("====================================");
                         System.out.println("     🍩 THANKS A DOZEN! 🍩");
                         System.out.println("       DOUGH & CO.");













                     }









}
