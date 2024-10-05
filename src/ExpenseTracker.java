import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpenseTracker {
     private List<Expense> expenses;
    private static final String FILE_NAME = "expenses.txt";

    public ExpenseTracker() {
        expenses = new ArrayList<>();
        loadExpenses();
    }

    // Add a new expense
    public void addExpense(String category, double amount, String date) {
        expenses.add(new Expense(category, amount, date));
        System.out.println("Expense added successfully!");
        saveExpenses();
    }

    // Display all expenses
    public void displayExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses to show.");
            return;
        }
        for (Expense e : expenses) {
            System.out.println(e);
        }
    }

    // Show summary by category
    public void showSummaryByCategory() {
        Map<String, Double> categoryTotals = new HashMap<>();
        for (Expense e : expenses) {
            categoryTotals.put(e.getCategory(),
                    categoryTotals.getOrDefault(e.getCategory(), 0.0) + e.getAmount());
        }
        for (String category : categoryTotals.keySet()) {
            System.out.println("Category: " + category + ", Total Spent: $" + categoryTotals.get(category));
        }
    }

    // Show summary by date
    public void showSummaryByDate() {
        Map<String, Double> dateTotals = new HashMap<>();
        for (Expense e : expenses) {
            dateTotals.put(e.getDate(),
                    dateTotals.getOrDefault(e.getDate(), 0.0) + e.getAmount());
        }
        for (String date : dateTotals.keySet()) {
            System.out.println("Date: " + date + ", Total Spent: $" + dateTotals.get(date));
        }
    }

    // Save expenses to a file
    private void saveExpenses() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Expense e : expenses) {
                writer.write(e.getDate() + "," + e.getCategory() + "," + e.getAmount());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving expenses: " + e.getMessage());
        }
    }

    // Load expenses from a file
    private void loadExpenses() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String date = parts[0];
                    String category = parts[1];
                    double amount = Double.parseDouble(parts[2]);
                    expenses.add(new Expense(category, amount, date));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No previous expense data found.");
        } catch (IOException e) {
            System.out.println("Error loading expenses: " + e.getMessage());
        }
    }
}
