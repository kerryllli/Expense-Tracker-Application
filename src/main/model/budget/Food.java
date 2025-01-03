package model.budget;

import model.transcation.Expense;
import model.transcation.TransactionType;


// Represents a Food (budget type) extends Budget
public class Food extends Budget {

    //EFFECTS: Constructs an instance of the Food without specified parameter
    public Food() {
        super();
    }

    // EFFECTS: Print out message to alert users when an expense is identified as a large expense in Food type.
    @Override
    public void alertLargeExpense(Expense e) {
        if (e.getAmount() >= getLargeExpense()) {
            System.out.println("Warning! Large Food Expense!!!");
        }
    }

    // EFFECTS: Calculate the remaining budget of Food after an expense of Food;
    @Override
    public void remainingBudget(Expense e) {
        if (e.getType() == TransactionType.FOOD) {
            setRemainingBudget(getBudget() - e.getAmount());
        } else {
            setRemainingBudget(getBudget());
        }
    }
}
