package model.transcation;


import model.transcation.Date;
import model.transcation.Expense;
import model.transcation.Income;

import java.util.ArrayList;

// Represents a Balance with expense list and income list
public class Balance {

    private ArrayList<Expense> expenseList;
    private ArrayList<Income> incomeList;


    // EFFECTS: Constructs an instance of the Balance without specified parameters.
    public Balance(ArrayList<Expense> el, ArrayList<Income> il) {
        this.expenseList = el;
        this.incomeList = il;
    }


    // EFFECTS: Calculate the total expense of a specific date
    public double dayExpense(Date date) {
        double amount = 0;
        for (int i = 0; i < expenseList.size(); i++) {
            if (date.equals(expenseList.get(i).getDate())) {
                amount += expenseList.get(i).getAmount();
            }
        }
        return amount;
    }


    // EFFECTS: Calculate the total expense of a specific month in a specific year
    public double monthExpense(int month, int year) {
        //if (month <= 0 || year <= 2023) {
        //    throw new IllegalDateException();
        double amount = 0;
        for (int i = 0; i < expenseList.size(); i++) {
            if (year == expenseList.get(i).getDate().getYear() && month == expenseList.get(i).getDate().getMonth()) {
                amount += expenseList.get(i).getAmount();
            }
        }
        return amount;
    }

    // EFFECTS: Calculate the total expense of a specific year
    public double yearExpense(int year) {
        double amount = 0;
        for (int i = 0; i < expenseList.size(); i++) {
            if (year == expenseList.get(i).getDate().getYear()) {
                amount += expenseList.get(i).getAmount();
            }
        }
        return amount;
    }

    // EFFECTS: Calculate the total income of a specific date
    public double dayIncome(Date date) {
        double amount = 0;
        for (int i = 0; i < incomeList.size(); i++) {
            if (date.equals(incomeList.get(i).getDate())) {
                amount += incomeList.get(i).getAmount();
            }
        }
        return amount;
    }

    // EFFECTS: Calculate the total income of a specific month in a specific year
    public double monthIncome(int month, int year)  {
        double amount = 0;
        for (int i = 0; i < incomeList.size(); i++) {
            if (year == incomeList.get(i).getDate().getYear() && month == incomeList.get(i).getDate().getMonth()) {
                amount += incomeList.get(i).getAmount();
            }
        }
        return amount;
    }

    // EFFECTS: Calculate the total income of a specific year
    public double yearIncome(int year) {
        double amount = 0;
        for (int i = 0; i < incomeList.size(); i++) {
            if (year == incomeList.get(i).getDate().getYear()) {
                amount += incomeList.get(i).getAmount();
            }
        }
        return amount;
    }


    // EFFECTS: Calculate the balance of a specific month in a specific year (Balance = Income - Expense)
    public double monthBalance(int month, int year) {
        return (monthIncome(month, year) - monthExpense(month, year));
    }

    // EFFECTS: Calculate the balance of a specific year (Balance = Income - Expense)
    public double yearBalance(int year) {
        return (yearIncome(year) - yearExpense(year));
    }
}
