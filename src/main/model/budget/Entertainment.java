package model.budget;

import model.transcation.Expense;
import model.transcation.TransactionType;


// Represents a Entertainment (budget type) extends Budget
public class Entertainment extends Budget {

    //EFFECTS: Constructs an instance of the Entertainment without specified parameter
    public Entertainment() {
        super();
    }

    // EFFECTS: Print out message to alert user when an expense is identified as a large expense in Entertainment type.
    @Override
    public void alertLargeExpense(Expense e) {
        if (e.getAmount() >= getLargeExpense()) {
            System.out.println("Warning! Large Entertainment Expense!!!");
        }
    }

    // EFFECTS: Calculate the remaining budget of Entertainment after an expense of Entertainment;
    @Override
    public void remainingBudget(Expense e) {
        if (e.getType() == TransactionType.ENTERTAINMENT) {
            setRemainingBudget(getBudget() - e.getAmount());
        } else {
            setRemainingBudget(getBudget());
        }
    }
}
