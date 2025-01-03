package model.budget;

import model.transcation.Expense;
import model.transcation.TransactionType;
import org.json.JSONObject;

// Represents an Education (budget type) extends Budget
public class Education extends Budget {

    //EFFECTS: Constructs an instance of the Education without specified parameter
    public Education() {
        super();
    }



    // EFFECTS: Print out message to alert user when an expense is identified as a large expense in Education type.
    @Override
    public void alertLargeExpense(Expense e) {
        if (e.getAmount() >= getLargeExpense()) {
            System.out.println("Warning! Large Education Expense!!!");
        }
    }

    // EFFECTS: Calculate the remaining budget of Education after an expense of Education;
    @Override
    public void remainingBudget(Expense e) {
        if (e.getType() == TransactionType.EDUCATION) {
            setRemainingBudget(getBudget() - e.getAmount());
        } else {
            setRemainingBudget(getBudget());
        }
    }

}

