package ui;

import model.budget.*;
import model.transcation.Balance;
import model.transcation.Expense;
import model.transcation.Income;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


// Represents a user having an expense list, an income list, balance (in dollars) , and six types of budget
public class User {

    private ArrayList<Expense> expenseList;
    private ArrayList<Income> incomeList;
    private Balance balance;
    private Education education;
    private Entertainment entertainment;
    private Food food;
    private Healthcare health;
    private Housing housing;
    private Other other;


    // EFFECTS: Creates a new instance of User without any parameter;
    public User() {
        this.expenseList = new ArrayList<Expense>();
        this.incomeList = new ArrayList<Income>();
        balance = new Balance(null, null);
        education = new Education();
        entertainment = new Entertainment();
        food = new Food();
        health = new Healthcare();
        housing = new Housing();
        other = new Other();
    }

    // MODIFIES: this
    // EFFECTS: Add the given expense to the expense list
    public void addExpense(Expense e) {
        expenseList.add(e);
        balance = new Balance(expenseList, incomeList);
    }

    // MODIFIES: this
    // EFFECTS: Remove the given index of the expense out of the expense list
    public void removeExpense(int e) {
        expenseList.remove(e);
        balance = new Balance(expenseList, incomeList);
    }

    // MODIFIES: this
    // EFFECTS: Add the given income to the income list
    public void addIncome(Income i) {
        incomeList.add(i);
        balance = new Balance(expenseList, incomeList);
    }

    // MODIFIES: this
    // EFFECTS: Remove the given index of the income out of the income list
    public void removeIncome(int i) {
        incomeList.remove(i);
        balance = new Balance(expenseList, incomeList);
    }

    // EFFECTS: Print out the whole expense list with dates, and amounts
    public void printExpenseList() {
        for (Expense e: expenseList) {
            System.out.println("On " + e.getDate().shortFormatDate() + ", spent " + e.getAmount());
        }
    }

    // EFFECTS: Print out the whole expense list with dates, and amounts for table format
    public String[][] printExpenseListTry() {
        String[][] data = new String[expenseList.size()][2];
        for (int i = 0; i < expenseList.size(); i++) {
            data[i][0] = expenseList.get(i).getDate().shortFormatDate();
            data[i][1] = Double.toString(expenseList.get(i).getAmount());
        }
        return data;
    }

    // EFFECTS: Print out the whole income list with dates, and amounts
    public void printIncomeList() {
        for (Income i: incomeList) {
            System.out.println("On " + i.getDate().shortFormatDate() + ", received " + i.getAmount());
        }
    }

    // Getter:
    public Balance getBalance() {
        return balance;
    }

    public Education getEducation() {
        return education;
    }

    public Entertainment getEntertainment() {
        return entertainment;
    }

    public Food getFood() {
        return food;
    }

    public Healthcare getHealth() {
        return health;
    }

    public Housing getHousing() {
        return housing;
    }

    public Other getOther() {
        return other;
    }

    public ArrayList<Expense> getExpenseList() {
        return expenseList;
    }

    public ArrayList<Income> getIncomeList() {
        return incomeList;
    }


    // EFFECTS: return a JSONObject including arrays for expenseList and incomeList,
    //          individual JSON objects for each budget category.
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("expenseList", expenseArrayToJson());
        json.put("incomeList", incomeArrayToJson());
        json.put("education", eduToJson());
        json.put("entertainment", enToJson());
        json.put("food", foodToJson());
        json.put("health", healthToJson());
        json.put("housing", housingToJson());
        json.put("other", otherToJson());
        return json;
    }

    // EFFECTS: returns expense in this expenseArray as a JSON array
    private JSONArray expenseArrayToJson() {
        JSONArray expenseArray = new JSONArray();
        for (Expense e : getExpenseList()) {
            expenseArray.put(expenseToJson(e));
        }
        return expenseArray;
    }

    // EFFECTS: returns income in this incomeArray as a JSON array
    private JSONArray incomeArrayToJson() {
        JSONArray incomeArray = new JSONArray();
        for (Income i : getIncomeList()) {
            incomeArray.put(incomeToJson(i));
        }

        return incomeArray;
    }

    // EFFECTS: returns a JSONObject representing the details of the given Expense object.
    //          The JSONObject includes date (year, month, day), amount, and type.
    private JSONObject expenseToJson(Expense e) {
        JSONObject expense = new JSONObject();
        JSONObject date = new JSONObject();
        date.put("year", e.getDate().getYear());
        date.put("month", e.getDate().getMonth());
        date.put("day", e.getDate().getDay());
        expense.put("date", date);
        expense.put("amount", e.getAmount());
        expense.put("type", e.getType());
        return expense;
    }

    // EFFECTS: returns a JSONObject representing the details of the given Income object.
    //          The JSONObject includes date (year, month, day), amount, and type.
    private JSONObject incomeToJson(Income i) {
        JSONObject income = new JSONObject();
        JSONObject date = new JSONObject();
        date.put("year", i.getDate().getYear());
        date.put("month", i.getDate().getMonth());
        date.put("day", i.getDate().getDay());
        income.put("date", date);
        income.put("amount", i.getAmount());
        income.put("type", i.getType());
        return income;
    }

    // EFFECTS: return a JSONObject(education) including the budget amount and percentage allocated.
    private JSONObject eduToJson() {
        JSONObject edu = new JSONObject();
        edu.put("budget", getEducation().getBudget());
        edu.put("percentage", getEducation().getPercentage());
        return edu;
    }

    // EFFECTS: return a JSONObject(entertainment) including the budget amount and percentage allocated.
    private JSONObject enToJson() {
        JSONObject en = new JSONObject();
        en.put("budget", getEntertainment().getBudget());
        en.put("percentage", getEntertainment().getPercentage());
        return en;
    }

    // EFFECTS: return a JSONObject(food) including the budget amount and percentage allocated.
    private JSONObject foodToJson() {
        JSONObject food = new JSONObject();
        food.put("budget", getFood().getBudget());
        food.put("percentage", getFood().getPercentage());
        return food;
    }

    // EFFECTS: return a JSONObject(health) including the budget amount and percentage allocated.
    private JSONObject healthToJson() {
        JSONObject health = new JSONObject();
        health.put("budget", getHealth().getBudget());
        health.put("percentage", getHealth().getPercentage());
        return health;
    }

    // EFFECTS: return a JSONObject(housing) including the budget amount and percentage allocated.
    private JSONObject housingToJson() {
        JSONObject housing = new JSONObject();
        housing.put("budget", getHousing().getBudget());
        housing.put("percentage", getHousing().getPercentage());
        return housing;
    }

    // EFFECTS: return a JSONObject(other) including the budget amount and percentage allocated.
    private JSONObject otherToJson() {
        JSONObject other = new JSONObject();
        other.put("budget", getOther().getBudget());
        other.put("percentage", getOther().getPercentage());
        return other;
    }

}
