package model.transcation;


import model.Subject;

// Represents a Transaction having a date, an amount (in dollars), and Transaction type
public abstract class Transaction extends Subject {

    private Date date;
    private double amount;
    private TransactionType type;

    //EFFECTS: Constructs an instance of the Transaction with specified date, amount, type, comment
    public Transaction(Date date, double amount, TransactionType type) {
        this.date = date;
        this.amount = amount;
        this.type = type;
    }

    //Getters
    public Date getDate() {
        notifyObservers("The expense list has been viewed!");
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public TransactionType getType() {
        return type;
    }

}
