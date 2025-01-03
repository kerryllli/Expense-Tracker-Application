package model.transcation;

// Represents an Income extends Transaction
public class Income extends Transaction {

    //EFFECTS: Constructs an instance of the Income with specified date, amount, type, comment
    public Income(Date date, double amount, TransactionType type) {
        super(date, amount, type);
    }
}

