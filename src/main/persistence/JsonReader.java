package persistence;

import model.transcation.Date;
import model.transcation.Expense;
import model.transcation.Income;
import model.transcation.TransactionType;
import ui.User;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads budget from JSON data stored in file
// Reference JsonSerializationDemo: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads user from file and returns it;
    // throws IOException if an error occurs reading data from file
    public User read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseUser(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses user from JSON object and returns it
    private User parseUser(JSONObject jsonObject) {
        User user = new User();
        JSONObject edu = jsonObject.getJSONObject("education");
        addEdu(user, edu);
        JSONObject en = jsonObject.getJSONObject("entertainment");
        addEn(user, en);
        JSONObject food = jsonObject.getJSONObject("food");
        addFood(user, food);
        JSONObject health = jsonObject.getJSONObject("health");
        addHealth(user, health);
        JSONObject housing = jsonObject.getJSONObject("housing");
        addHousing(user, housing);
        JSONObject other = jsonObject.getJSONObject("other");
        addOther(user, other);
        JSONArray expenseArray = jsonObject.getJSONArray("expenseList");
        for (Object expense: expenseArray) {
            addExpense(user, (JSONObject) expense);
        }
        JSONArray incomeArray = jsonObject.getJSONArray("incomeList");
        for (Object income: incomeArray) {
            addIncome(user, (JSONObject) income);
        }
        return user;
    }

    // MODIFIES: user
    // EFFECTS: parses add expense from JSON object and adds them to user
    private void addExpense(User user, JSONObject expense) {
        JSONObject date = expense.getJSONObject("date");
        int year = date.getInt("year");
        int month = date.getInt("month");
        int day = date.getInt("day");
        Date newDate = new Date(year,month,day);
        double amount = expense.getDouble("amount");
        TransactionType type = TransactionType.valueOf(expense.getString("type"));
        Expense newExpense = new Expense(newDate, amount, type);
        user.addExpense(newExpense);
        switchCategory(type, user, newExpense);
    }

    private void switchCategory(TransactionType t, User user, Expense e) {
        switch (t) {
            case EDUCATION:
                user.getEducation().remainingBudget(e);
                break;
            case ENTERTAINMENT:
                user.getEntertainment().remainingBudget(e);
                break;
            case FOOD:
                user.getFood().remainingBudget(e);
                break;
            case HEALTHCARE:
                user.getHealth().remainingBudget(e);
                break;
            case HOUSING:
                user.getHousing().remainingBudget(e);
                break;
            default:
                user.getOther().remainingBudget(e);
        }
    }

    // MODIFIES: user
    // EFFECTS: parses add income from JSON object and adds them to user
    private void addIncome(User user, JSONObject income) {
        JSONObject date = income.getJSONObject("date");
        int year = date.getInt("year");
        int month = date.getInt("month");
        int day = date.getInt("day");
        Date newDate = new Date(year,month,day);
        double amount = income.getDouble("amount");
        TransactionType type = TransactionType.valueOf(income.getString("type"));
        Income newIncome = new Income(newDate, amount, type);
        user.addIncome(newIncome);
    }

    // MODIFIES: user
    // EFFECTS: parses add edu from JSON object and adds them to user
    private void addEdu(User user, JSONObject edu) {
        double budget = edu.getDouble("budget");
        double percentage = edu.getDouble("percentage");
        user.getEducation().setBudget(budget);
        user.getEducation().setPercentage(percentage);
        user.getEducation().setRemainingBudget(budget);
    }

    // MODIFIES: user
    // EFFECTS: parses add en from JSON object and adds them to user
    private void addEn(User user, JSONObject en) {
        double budget = en.getDouble("budget");
        double percentage = en.getDouble("percentage");
        user.getEntertainment().setBudget(budget);
        user.getEntertainment().setPercentage(percentage);
        user.getEntertainment().setRemainingBudget(budget);
    }

    // MODIFIES: user
    // EFFECTS: parses add food from JSON object and adds them to user
    private void addFood(User user, JSONObject food) {
        double budget = food.getDouble("budget");
        double percentage = food.getDouble("percentage");
        user.getFood().setBudget(budget);
        user.getFood().setPercentage(percentage);
        user.getFood().setRemainingBudget(budget);
    }

    // MODIFIES: user
    // EFFECTS: parses add edu from JSON object and adds them to user
    private void addHealth(User user, JSONObject health) {
        double budget = health.getDouble("budget");
        double percentage = health.getDouble("percentage");
        user.getHealth().setBudget(budget);
        user.getHealth().setPercentage(percentage);
        user.getHealth().setRemainingBudget(budget);
    }

    // MODIFIES: user
    // EFFECTS: parses add housing from JSON object and adds them to user
    private void addHousing(User user, JSONObject housing) {
        double budget = housing.getDouble("budget");
        double percentage = housing.getDouble("percentage");
        user.getHousing().setBudget(budget);
        user.getHousing().setPercentage(percentage);
        user.getHousing().setRemainingBudget(budget);
    }

    // MODIFIES: user
    // EFFECTS: parses add other from JSON object and adds them to user
    private void addOther(User user, JSONObject other) {
        double budget = other.getDouble("budget");
        double percentage = other.getDouble("percentage");
        user.getOther().setBudget(budget);
        user.getOther().setPercentage(percentage);
        user.getOther().setRemainingBudget(budget);
    }
}
