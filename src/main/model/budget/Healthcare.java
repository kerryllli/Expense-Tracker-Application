package model.budget;

import model.transcation.Expense;
import model.transcation.TransactionType;


// Represents a Healthcare (budget type) extends Budget
public class Healthcare extends Budget {

    //EFFECTS: Constructs an instance of the Healthcare without specified parameter
    public Healthcare() {
        super();
    }

    // EFFECTS: Print out message to alert users when an expense is identified as a large expense in Healthcare type.
    @Override
    public void alertLargeExpense(Expense e) {
        if (e.getAmount() >= getLargeExpense()) {
            System.out.println("Warning! Large Healthcare Expense!!!");
        }
    }

    // EFFECTS: Calculate the remaining budget of Healthcare after an expense of Healthcare;
    @Override
    public void remainingBudget(Expense e) {
        if (e.getType() == TransactionType.HEALTHCARE) {
            setRemainingBudget(getBudget() - e.getAmount());
        } else {
            setRemainingBudget(getBudget());
        }
    }
}
