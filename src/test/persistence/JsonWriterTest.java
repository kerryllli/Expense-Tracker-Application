package persistence;

import model.budget.Budget;
import model.transcation.*;
import org.junit.jupiter.api.Test;
import ui.User;

import java.io.IOException;

import static model.transcation.TransactionType.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest {


    @Test
    void testWriterInvalidFile() {
        try {
            User user = new User();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException should be thrown");
        } catch (IOException e) {
            // pass
        }
    }


    @Test
    void testWriterEmptyHistory() {
        try {
            User user = new User();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyHistory.json");
            writer.open();
            writer.write(user);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyHistory.json");
            user = reader.read();
            testWriterBudgetHelper(user.getEducation(), 0, 0);
            testWriterBudgetHelper(user.getEntertainment(), 0, 0);
            testWriterBudgetHelper(user.getHealth(), 0, 0);
            testWriterBudgetHelper(user.getHousing(), 0, 0);
            testWriterBudgetHelper(user.getFood(), 0, 0);
            testWriterBudgetHelper(user.getOther(), 0, 0);
            assertEquals(0,user.getExpenseList().size());
            assertEquals(0,user.getIncomeList().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    void testWriterBudgetHelper(Budget b, double percentage, double budget) {
        assertEquals(percentage, b.getPercentage());
        assertEquals(budget,b.getBudget());
    }

    void testWriterTransactionHelper(Transaction t, String date, double amount, TransactionType type) {
        assertEquals(date, t.getDate().shortFormatDate());
        assertEquals(amount, t.getAmount());
        assertEquals(type, t.getType());
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            User user = new User();
            user.getEducation().setPercentage(80);
            user.getOther().setBudget(20000);
            user.getFood().setPercentage(85);
            Expense expense1 = new Expense(new Date(2025,12,24), 200,ENTERTAINMENT);
            Expense expense2 = new Expense(new Date(2024,12,24), 300,OTHER);
            Income income1 = new Income(new Date(2024,12,24), 5000, HOUSING);
            user.addExpense(expense1);
            user.addExpense(expense2);
            user.addIncome(income1);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralHistory.json");
            writer.open();
            writer.write(user);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralHistory.json");
            user = reader.read();
            testWriterBudgetHelper(user.getEducation(), 80, 0);
            testWriterBudgetHelper(user.getFood(), 85, 0);
            testWriterBudgetHelper(user.getOther(), 0, 20000);
            testWriterTransactionHelper(user.getIncomeList().get(0), "12-24-2024",
                    5000, HOUSING);
            testWriterTransactionHelper(user.getExpenseList().get(0), "12-24-2025",
                    200, ENTERTAINMENT);
            testWriterTransactionHelper(user.getExpenseList().get(1),"12-24-2024",
                    300, OTHER);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
