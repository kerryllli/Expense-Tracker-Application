package model.transcation;

// Represents a Expense extends Transaction
public class Expense extends Transaction {

    //EFFECTS: Constructs an instance of the Expense with specified date, amount, type, comment
    public Expense(Date date, double amount, TransactionType type) {
        super(date, amount, type);
    }
}
