package model.budget;

import model.Subject;
import model.transcation.Expense;


// Represents a Budget having a budget amount, large expense and remaining balance (all in dollars)
public abstract class Budget extends Subject {

    private double budget;
    private double percentage;
    private double largeExpense;
    private double remainingBudget;

    //EFFECTS: Constructs an instance of the Budget without specified parameter
    public Budget() {
        this.budget = 0;
        percentage = 0;
        this.largeExpense = 0;
        remainingBudget = 0;
    }

    // Setters:
    public void setBudget(double budget) {
        this.budget = budget;
    }

    public void setRemainingBudget(double remainingBudget) {
        this.remainingBudget = remainingBudget;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
        largeExpense = percentage / 100 * budget;
    }

    //Getters:
    public double getBudget() {
        notifyObservers("The expense has been added!");
        return budget;
    }

    public double getLargeExpense() {
        return largeExpense;
    }

    public double getRemainingBudget() {
        return remainingBudget;
    }

    public double getPercentage() {
        return percentage;
    }

    // EFFECTS: Calculate the remaining budget of a type after a specific type of expense;
    public abstract void remainingBudget(Expense e);

    // EFFECTS: Print out specific message to alert user when an expense is identified as a large expense of a type.
    public abstract void alertLargeExpense(Expense e);

}
