package model.budget;

import model.transcation.Expense;
import model.transcation.TransactionType;


// Represents a Housing (budget type) extends Budget
public class Housing extends Budget {

    //EFFECTS: Constructs an instance of the Housing without specified parameter
    public Housing() {
        super();
    }

    // EFFECTS: Print out message to alert users when an expense is identified as a large expense in Housing type.
    @Override
    public void alertLargeExpense(Expense e) {
        if (e.getAmount() >= getLargeExpense()) {
            System.out.println("Warning! Large Housing Expense!!!");
        }
    }

    // EFFECTS: Calculate the remaining budget of Housing after an expense of Housing;
    @Override
    public void remainingBudget(Expense e) {
        if (e.getType() == TransactionType.HOUSING) {
            setRemainingBudget(getBudget() - e.getAmount());
        } else {
            setRemainingBudget(getBudget());
        }
    }
}
