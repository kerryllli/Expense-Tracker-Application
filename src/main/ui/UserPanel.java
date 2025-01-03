package ui;

import model.budget.Budget;
import model.transcation.Date;
import model.transcation.Expense;
import model.transcation.Income;
import model.transcation.TransactionType;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Represent the panel between the program and the user
public class UserPanel {

    private static final String STORE = "./data/user.json";

    private final Scanner scan;
    private User user;
    private Boolean hasStarted;
    private Boolean status;
    private Boolean saved;
    private TransactionType temp;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    //EFFECTS: Constructs an instance of the UserPanel without specified parameter
    public UserPanel() {
        scan = new Scanner(System.in);
        scan.useDelimiter("\n");
        user = new User();
        hasStarted = true;
        status = false;
        saved = false;
        temp = null;
        jsonWriter = new JsonWriter(STORE);
        jsonReader = new JsonReader(STORE);
    }


    // EFFECTS: The main panel. Provide instructions for the user to continue use the panel.
    public void mainLoop() {
        System.out.println("Welcome! Let's embark on the Billionaire Journey: Money Management");
        System.out.println("Do you want to load your transaction history?");
        System.out.println("yes --1  no -- 2");
        switch (validInputForInteger()) {
            case 1:
                loadFunction();
                status = true;
                break;
            case 2:
                break;
        }
        while (hasStarted) {
            if (!status) {
                mustSetBudget();
                mustSetBudgetPercentage();
            } else {
                displayMain();
                handleMain();
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadFunction() {
        try {
            user = jsonReader.read();
            System.out.println("Loaded transaction history from " + STORE);
        } catch (IOException e) {
            System.out.println("Unable to read the transaction history from: " + STORE);
        }
    }


    // EFFECTS: Let the user set the budget for every type of transaction
    public void mustSetBudget() {
        status = true;
        System.out.println("The first step you should do: Setting the BUDGET of each types of transaction");
        System.out.println("Please enter the budget you want to set for each type");
        System.out.println("Education Budget Amount");
        double amount = validInputForDouble();
        user.getEducation().setBudget(amount);
        System.out.println("Entertainment Budget Amount");
        amount = validInputForDouble();
        user.getEntertainment().setBudget(amount);
        System.out.println("Food Budget Amount");
        amount = validInputForDouble();
        user.getFood().setBudget(amount);
        System.out.println("Healthcare Budget Amount");
        amount = validInputForDouble();
        user.getHealth().setBudget(amount);
        System.out.println("Housing Budget Amount");
        amount = validInputForDouble();
        user.getHousing().setBudget(amount);
        System.out.println("Please Enter Other Budget Amount");
        amount = validInputForDouble();
        user.getOther().setBudget(amount);
    }

    // EFFECTS: Let the user set the budget Percentage of each type
    public void mustSetBudgetPercentage() {
        System.out.println("Next, you should set the Budget Percentage");
        System.out.println("If one expense takes up greater than or equal to the budget multiplied by the percentage,"
                + " this expense will be categorized as a 'large expense.'");
        System.out.println("The percentage should be between 0 to 100");
        System.out.println("Education Budget Percentage");
        double p = validInputForBudgetPercentage();
        user.getEducation().setPercentage(p);
        System.out.println("Entertainment Budget Percentage");
        p = validInputForBudgetPercentage();
        user.getEntertainment().setPercentage(p);
        System.out.println("Food Budget Percentage");
        p = validInputForBudgetPercentage();
        user.getFood().setPercentage(p);
        System.out.println("Healthcare Budget Percentage");
        p = validInputForBudgetPercentage();
        user.getHealth().setPercentage(p);
        System.out.println("Housing Budget Percentage");
        p = validInputForBudgetPercentage();
        user.getHousing().setPercentage(p);
        System.out.println("Other Budget Percentage");
        p = validInputForBudgetPercentage();
        user.getOther().setPercentage(p);
    }

    //EFFECTS: To ensure the given budget percentage is valid
    public double validInputForBudgetPercentage() {
        double p = validInputForDouble();
        while ((p < 0) || (p > 100)) {
            System.out.println("Invalid percentage, please enter a value between 0 to 100");
            p  = validInputForDouble();
        }
        return p;
    }

    //EFFECTS: Display the main instructions for user to select their options
    public void displayMain() {
        System.out.println("Select one of the following options by entering the full name of the them: ");
        System.out.println("expense");
        System.out.println("income");
        System.out.println("balance");
        System.out.println("budget");
        System.out.println("store");
        System.out.println("quit");
    }

    //EFFECTS: Handle the methods that belong to the option the user selects.
    public void handleMain() {
        switch (scan.next()) {
            case "expense":
                displayExpense();
                break;
            case "income":
                displayIncome();
                break;
            case "balance":
                displayBalance();
                handleBalance();
                break;
            case "budget":
                handleBudget();
                break;
            case "store":
                handleStore();
                break;
            case "quit":
                handleQuit();
                break;
            default:
                System.out.println("The value is not allowed. Try again!");
        }
    }

    // EFFECTS: Handle the methods that belong to the option the user selects.
    private void handleQuit() {
        if (!saved) {
            System.out.println("You didn't save your transaction. Are you sure you want to store now?");
            System.out.println("yes--1, no--2");
            switch (validInputForInteger()) {
                case 1:
                    handleStore();
                case 2:
                    System.out.println("Bye, see you next time!");
                    hasStarted = false;
            }
        }
    }

    // EFFECTS: Display the instruction after user select "expense"
    public void displayExpense() {
        System.out.println("Select one of the following options by entering the full word of the them: ");
        System.out.println("add");
        System.out.println("remove");
        System.out.println("view");
        System.out.println("quit");
        handleExpense();
    }

    // EFFECTS: Handle the methods that belong to the option the user selects.
    public void handleExpense() {
        String input = scan.next();
        switch (input) {
            case "add":
                displayAddExpense();
                handleAddExpense();
                break;
            case "remove":
                displayRemoveExpense();
                handleRemoveExpense();
                break;
            case "view":
                displayViewExpense();
                handleViewExpense();
                break;
            case "quit":
                System.out.println("Bye, see you next time!");
                hasStarted = false;
                break;
            default:
                System.out.println("The value is not allowed. Try again!");
        }
    }

    // EFFECTS: Display the instructions after user select "add" (under "expense")
    public void displayAddExpense() {
        System.out.println("Now you are allowed to add one expense to your expense list");
        System.out.println("Please enter the following value in order");
        System.out.println("Type the date and amount of the expense: ");
        System.out.println("year");
        System.out.println("month");
        System.out.println("day");
        System.out.println("amount");
        System.out.println("And choose one of the following transaction types of your income "
                + "by typing the number representing it:");
        System.out.println("eduction -- 1");
        System.out.println("entertainment -- 2");
        System.out.println("food -- 3");
        System.out.println("healthcare -- 4");
        System.out.println("housing -- 5");
        System.out.println("other -- any key");
    }

    // EFFECTS: Add the given expense (the expense from handAddExpenseHelper) to the expense list
    public void handleAddExpense() {
        Expense e = handleAddExpenseHelper();
        handleAddExpenseHelper2(e);
        user.addExpense(e);
    }

    // EFFECTS: Return the date that user entered
    public Date dateAddExpenseHelper() {
        int year = validInputForYear();
        int month = validInputForMonth();
        int day = validInputForDay();
        return (new Date(year, month, day));
    }

    // MODIFIES: this
    // EFFECTS: Return the expense with the date, amount and transaction type given by the users
    public Expense handleAddExpenseHelper() {
        Date d = dateAddExpenseHelper();
        double amount = validInputForDouble();
        switch (scan.next())  {
            case "1":
                temp = TransactionType.EDUCATION;
                return (new Expense(d, amount, temp));
            case "2":
                temp = TransactionType.ENTERTAINMENT;
                return (new Expense(d, amount, temp));
            case "3" :
                temp = TransactionType.FOOD;
                return (new Expense(d, amount, temp));
            case "4":
                temp = TransactionType.HEALTHCARE;
                return (new Expense(d, amount, temp));
            case "5":
                temp = TransactionType.HOUSING;
                return (new Expense(d, amount, temp));
            default:
                temp = TransactionType.OTHER;
                return (new Expense(d, amount, temp));
        }
    }

    // EFFECTS: Helper method to check if the expense is large expense and update the remaining budget of given type
    private void largeExpenseCheckAndUpdateRemainingBudget(Budget b, Expense e) {
        b.alertLargeExpense(e);
        b.remainingBudget(e);
    }

    // EFFECTS: Check if the expense is large expense and update the remaining budget of the given type of expense
    public void handleAddExpenseHelper2(Expense e) {
        TransactionType t = e.getType();
        switch (t) {
            case EDUCATION:
                largeExpenseCheckAndUpdateRemainingBudget(user.getEducation(), e);
                break;
            case ENTERTAINMENT:
                largeExpenseCheckAndUpdateRemainingBudget(user.getEntertainment(), e);
                break;
            case FOOD:
                largeExpenseCheckAndUpdateRemainingBudget(user.getFood(), e);
                break;
            case HEALTHCARE:
                largeExpenseCheckAndUpdateRemainingBudget(user.getHealth(), e);
                break;
            case HOUSING:
                largeExpenseCheckAndUpdateRemainingBudget(user.getHousing(), e);
                break;
            case OTHER:
                largeExpenseCheckAndUpdateRemainingBudget(user.getOther(), e);
        }
    }

    // EFFECTS: Display the instructions after user select "remove" (under "expense")
    public void displayRemoveExpense() {
        System.out.println("The following list is your current expense list");
        user.printExpenseList();
        System.out.println("Which line of expense do you want to remove?");
        System.out.println("Please select a line within the boundary");
    }

    // EFFECTS: Handle the method to check if the expense is large expense and update the remaining budget
    public void handleRemoveExpense() {
        int input = validInputForRemoveExpense();
        user.removeExpense((input - 1));
        System.out.println("Successfully removed");
    }

    // EFFECTS: Check if the given line is an integer and within the boundary of the expense list
    public int validInputForRemoveExpense() {
        int input = validInputForInteger();
        while ((input < 0) || (input > (user.getExpenseList().size()))) {
            System.out.println("Invalid line, please enter a value within the boundary");
            input = validInputForInteger();
        }
        return input;
    }

    // EFFECTS: Display the instructions after user select "view" (under "expense")
    public void displayViewExpense() {
        System.out.println("Enter the year and month that you want to view the expense");
        System.out.println("year");
        System.out.println("month");
    }

    // EFFECTS: Print out the expense of the given month and the given year
    public void handleViewExpense() {
        int year = validInputForYear();
        int month = validInputForMonth();
        System.out.print("The expense in this month: ");
        System.out.println(user.getBalance().monthExpense(month, year));
        System.out.print("The expense in this year: ");
        System.out.println(user.getBalance().yearExpense(year));
    }

    // EFFECTS: Display the instructions after user select "Income"
    public void displayIncome() {
        System.out.println("Select options: ");
        System.out.println("add");
        System.out.println("remove");
        System.out.println("view");
        System.out.println("quit");
        handleIncome();
    }

    // EFFECTS: Handle the methods that belong to the option the user selects.
    public void handleIncome() {
        String input = scan.next();
        switch (input) {
            case "add":
                displayAddIncome();
                handleAddIncome();
                break;
            case "remove":
                displayRemoveIncome();
                handleRemoveIncome();
                break;
            case "view":
                displayViewIncome();
                handleViewIncome();
                break;
            case "quit":
                System.out.println("Bye, see you next time!");
                hasStarted = false;
                break;
            default:
                System.out.println("The value is not allowed. Try again!");
        }
    }

    // EFFECTS: Display the instructions after user select "add" (under "income")
    public void displayAddIncome() {
        System.out.println("Now you are allowed to add one income to your income list");
        System.out.println("Please enter the following value in order");
        System.out.println("Type the date and amount of the income: ");
        System.out.println("year");
        System.out.println("month");
        System.out.println("day");
        System.out.println("amount");
        System.out.println("And choose one of the following transaction types of your income "
                + "by typing the number representing it:");
        System.out.println("housing -- 1");
        System.out.println("salaries -- 2");
        System.out.println("other -- any key");
    }

    // EFFECTS: Add the given income with year, month, day, amount and transaction type to the income list
    public void handleAddIncome() {
        int year = validInputForYear();
        int month = validInputForMonth();
        int day = validInputForDay();
        double amount = validInputForDouble();
        TransactionType t = null;
        switch (scan.next())  {
            case "1":
                t = TransactionType.HOUSING;
                break;
            case "2":
                t = TransactionType.SALARIES;
                break;
            default:
                t = TransactionType.OTHER;
        }
        user.addIncome(new Income(new Date(year, month, day), amount, t));
    }

    // EFFECTS: Display the instructions after user select "remove" (under "income")
    public void displayRemoveIncome() {
        System.out.println("The following list is your current income list");
        user.printIncomeList();
        System.out.println("Which line of income do you want to remove?");
        System.out.println("Please select a line within the boundary");
    }

    // EFFECTS: Remove the given income with year, month, day, amount and transaction type out of  the income list
    public void handleRemoveIncome() {
        int input = validInputForRemoveIncome();
        user.removeIncome((input - 1));
        System.out.println("Successfully removed");
    }

    // EFFECTS: Check if the given line is an integer and within the boundary of the income list
    public int validInputForRemoveIncome() {
        int input = validInputForInteger();
        while ((input < 0) || (input > (user.getExpenseList().size()))) {
            System.out.println("Invalid line, please enter a value within the boundary");
            input = validInputForInteger();
        }
        return input;
    }

    // EFFECTS: Display the instructions after user select "view" (under "income")
    public void displayViewIncome() {
        System.out.println("Enter the year and month that you want to view the income: ");
        System.out.println("year");
        System.out.println("month");
    }

    // EFFECTS: Print out the income of the given month and the given year
    public void handleViewIncome() {
        int year = validInputForYear();
        int month = validInputForMonth();
        System.out.print("The income in this month: ");
        System.out.println(user.getBalance().monthIncome(month, year));
        System.out.print("The income in this year: ");
        System.out.println(user.getBalance().yearIncome(year));
    }

    // EFFECTS: Display the instructions after user select "balance"
    public void displayBalance() {
        System.out.println("Enter the year and month that you want to view the balance: ");
        System.out.println("year");
        System.out.println("month");
    }

    // EFFECTS: Print out the balance of the given month and the given year
    public void handleBalance() {
        int year = validInputForYear();
        int month = validInputForMonth();
        System.out.print("The balance in this month: ");
        System.out.println(user.getBalance().monthBalance(month, year));
        System.out.print("The balance int this year: ");
        System.out.println(user.getBalance().yearBalance(year));
    }

    // EFFECTS: Display the instructions after user select "budget"
    public void handleBudget() {
        System.out.println("Select one of the following options by typing the full word of it: ");
        System.out.println("view");
        System.out.println("quit");
        String input = scan.next();
        switch (input) {
            case "view":
                displayViewBudget();
                handleViewBudget();
                break;
            case "quit":
                System.out.println("Bye, see you next time!");
                hasStarted = false;
                break;
            default:
                System.out.println("The value is not allowed. Try again!");
        }
    }

    // EFFECTS: saves the user to file
    public void handleStore() {
        try {
            jsonWriter.open();
            jsonWriter.write(user);
            jsonWriter.close();
            saved = true;
            System.out.println("Saved transaction to " + STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + STORE);
        }
    }

    // EFFECTS: Display the instructions after user select "view" (under "budget")
    public void displayViewBudget() {
        System.out.println("Select one of the following options by typing the number representing it: ");
        System.out.println("View Budget Amount -- 1");
        System.out.println("View Remaining Budget -- 2");
    }

    // EFFECTS: Handle the methods that belong to the option the user selects.
    public void handleViewBudget() {
        String input = scan.next();
        switch (input) {
            case "1":
                displayViewBudgetAmount();
                handleViewBudgetAmount();
                break;
            case "2":
                displayViewRemainingBudget();
                handleViewRemainingBudget();
                break;
        }
    }

    // EFFECTS: Display the instructions after user select "1" (under "budget", and under "view")
    public void displayViewBudgetAmount() {
        System.out.println("Enter the type of budget for which you want to view"
                + " the remaining budget by typing the corresponding number: ");
        System.out.println("eduction -- 1");
        System.out.println("entertainment -- 2");
        System.out.println("food -- 3");
        System.out.println("healthcare -- 4");
        System.out.println("housing -- 5");
        System.out.println("other -- any key");
    }

    // EFFECTS: Handle the methods that belong to the option the user selects.
    public void handleViewBudgetAmount() {
        String input = scan.next();
        switch (input)  {
            case "1":
                System.out.println(user.getEducation().getBudget());
                break;
            case "2":
                System.out.println(user.getEntertainment().getBudget());
                break;
            case "3" :
                System.out.println(user.getFood().getBudget());
                break;
            case "4":
                System.out.println(user.getHealth().getBudget());
                break;
            case "5":
                System.out.println(user.getHousing().getBudget());
                break;
            default:
                System.out.println(user.getOther().getBudget());
        }
    }

    // EFFECTS: Display the instructions after user select "2" (under "budget", and under "view")
    public void displayViewRemainingBudget() {
        System.out.println("Enter the type of remaining budget for which you want to view"
                + " the remaining budget by typing the corresponding number: ");
        System.out.println("eduction -- 1");
        System.out.println("entertainment -- 2");
        System.out.println("food -- 3");
        System.out.println("healthcare -- 4");
        System.out.println("housing -- 5");
        System.out.println("other -- any key");
    }

    // EFFECTS: Print out the remaining budget of the given transaction type
    public void handleViewRemainingBudget() {
        String input = scan.next();
        switch (input)  {
            case "1":
                System.out.println("The remaining budget of education is: " + user.getEducation().getRemainingBudget());
                break;
            case "2":
                System.out.println("The remaining budget of entertainment is: "
                        + user.getEntertainment().getRemainingBudget());
                break;
            case "3" :
                System.out.println("The remaining budget of food is: " + user.getFood().getRemainingBudget());
                break;
            case "4":
                System.out.println("The remaining budget of healthcare is: " + user.getHealth().getRemainingBudget());
                break;
            case "5":
                System.out.println("The remaining budget of housing is: " + user.getHousing().getRemainingBudget());
                break;
            default:
                System.out.println("The remaining budget of other is: " + user.getOther().getRemainingBudget());
        }
    }

    // EFFECTS: Check if the given number is an integer
    public int validInputForInteger() {
        String s = scan.next();
        while (!s.matches("[0-9]+")) {
            System.out.println("Can only input integer, try again");
            s = scan.next();
        }
        return Integer.valueOf(s);
    }

    // EFFECTS: Check if the given number is a double
    public double validInputForDouble() {
        String s = scan.next();
        while (!s.matches("[0-9]+(.[0-9]+)?")) {
            System.out.println("Can only input number. Try again!");
            s = scan.next();
        }
        return Double.valueOf(s);
    }

    // EFFECTS: Check if the given year is larger or equal to 2024
    public int validInputForYear() {
        int year = validInputForInteger();
        while (!(year >= 2024)) {
            System.out.println("Invalid year, please enter a value >= 2024");
            year = validInputForInteger();
        }
        return year;
    }

    // EFFECTS: Check if the given month is valid (between 1 and 12, included)
    public int validInputForMonth() {
        int month = validInputForInteger();
        while (!(month >= 1 && month <= 12)) {
            System.out.println("Invalid month, please enter a new value");
            month = validInputForInteger();
        }
        return month;
    }

    // EFFECTS: Check if the given day is valid (between 1 and 31, included)
    public int validInputForDay() {
        int day = validInputForInteger();
        while (!(day <= 31 && day >= 1)) {
            System.out.println("Invalid day, please enter a new value");
            day = validInputForInteger();
        }
        return day;
    }
}
