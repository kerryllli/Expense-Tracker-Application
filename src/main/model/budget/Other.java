package model.budget;

import model.transcation.Expense;
import model.transcation.TransactionType;


// Represents an Other (budget type) extends Budget
public class Other extends Budget {

    //EFFECTS: Constructs an instance of the Other without specified parameter
    public Other() {
        super();
    }

    // EFFECTS: Print out message to alert users when an expense is identified as a large expense in Other type.
    @Override
    public void alertLargeExpense(Expense e) {
        if (e.getAmount() >= getLargeExpense()) {
            System.out.println("Warning! Large Other Expense!!!");
        }
    }

    // EFFECTS: Calculate the remaining budget of Other after an expense of Others;
    @Override
    public void remainingBudget(Expense e) {
        if (e.getType() == TransactionType.OTHER) {
            setRemainingBudget(getBudget() - e.getAmount());
        } else {
            setRemainingBudget(getBudget());
        }
    }
}
