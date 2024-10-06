import java.util.*;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ExpenseTracker tracker = new ExpenseTracker();

        while (true) {
            System.out.println("\nExpense Tracker Menu:");
            System.out.println("1. Add a new expense");
            System.out.println("2. View all expenses");
            System.out.println("3. Show summary by category");
            System.out.println("4. Show summary by date");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter category (e.g., Food, Transport): ");
                    String category = scanner.nextLine();
                    System.out.print("Enter amount: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine(); // consume newline
                    System.out.print("Enter date (MM-DD-YYYY): ");
                    String date = scanner.nextLine();
                    tracker.addExpense(category, amount, date);
                    break;
                case 2:
                    tracker.displayExpenses();
                    break;
                case 3:
                    tracker.showSummaryByCategory();
                    break;
                case 4:
                    tracker.showSummaryByDate();
                    break;
                case 5:
                    System.out.println("Exiting... Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }   
        }
    }
}
