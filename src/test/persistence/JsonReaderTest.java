package persistence;

import model.budget.Budget;
import model.transcation.Transaction;
import model.transcation.TransactionType;
import org.junit.jupiter.api.Test;
import ui.User;

import java.io.IOException;

import static model.transcation.TransactionType.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/wrongFile.json");
        try {
            User user = reader.read();
            fail("IOException should be thrown");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyHistory() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyHistory.json");
        try {
            User user = reader.read();
            testReaderBudgetHelper(user.getEducation(), 0, 0, 0);
            testReaderBudgetHelper(user.getEntertainment(), 0, 0, 0);
            testReaderBudgetHelper(user.getHealth(), 0, 0, 0);
            testReaderBudgetHelper(user.getHousing(), 0, 0, 0);
            testReaderBudgetHelper(user.getFood(), 0, 0, 0);
            testReaderBudgetHelper(user.getOther(), 0, 0, 0);
            assertEquals(0,user.getExpenseList().size());
            assertEquals(0,user.getIncomeList().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    void testReaderBudgetHelper(Budget b, double percentage, double budget, double reb) {
        assertEquals(percentage, b.getPercentage());
        assertEquals(budget,b.getBudget());
        assertEquals(reb, b.getRemainingBudget());
    }

    void testReaderTransactionHelper(Transaction t, String date, double amount, TransactionType type) {
        assertEquals(date, t.getDate().shortFormatDate());
        assertEquals(amount, t.getAmount());
        assertEquals(type, t.getType());
    }

    @Test
    void testReaderGeneralHistory() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralHistory.json");
        try {
            User user = reader.read();
            testReaderBudgetHelper(user.getEducation(), 80, 10000, 9000);
            testReaderBudgetHelper(user.getEntertainment(), 50, 10000, 9800);
            testReaderBudgetHelper(user.getHealth(), 75, 1000, 900);
            testReaderBudgetHelper(user.getHousing(), 90, 20000,19000);
            testReaderBudgetHelper(user.getFood(), 85, 10000, 8000);
            testReaderBudgetHelper(user.getOther(), 60, 20000, 19700);
            testReaderTransactionHelper(user.getExpenseList().get(0), "12-24-2025",
                    200, ENTERTAINMENT);
            testReaderTransactionHelper(user.getExpenseList().get(1),"12-24-2024",
                    300, OTHER);
            testReaderTransactionHelper(user.getExpenseList().get(2), "12-23-2024",
                    1000, EDUCATION);
            testReaderTransactionHelper(user.getExpenseList().get(3), "12-28-2024",
                    2000, FOOD);
            testReaderTransactionHelper(user.getExpenseList().get(4), "12-26-2024",
                    100, HEALTHCARE);
            testReaderTransactionHelper(user.getExpenseList().get(5), "12-23-2024",
                    1000, HOUSING);
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
