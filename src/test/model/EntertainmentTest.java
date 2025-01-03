package model;

import model.budget.Entertainment;
import model.transcation.Date;
import model.transcation.Expense;
import model.transcation.TransactionType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EntertainmentTest {

    Entertainment ent;
    double budget;
    double percentage;
    double remaining;
    Date d1;
    Date d2;
    Expense e1;
    Expense e2;
    Expense e3;
    Expense e4;
    final PrintStream sout = System.out;
    final ByteArrayOutputStream out = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        ent = new Entertainment();
        percentage = 20;
        budget = 10000;
        remaining = 0;
        d1 = new Date(2024,12,24);
        d2 = new Date(2025,10,20);
        e1 = new Expense(d1,2000, TransactionType.ENTERTAINMENT);
        e2 = new Expense(d2,1000,TransactionType.ENTERTAINMENT);
        e3 = new Expense(d1,1000,TransactionType.HOUSING);
        e4 = new Expense(d1,2500,TransactionType.ENTERTAINMENT);
        System.setOut(new PrintStream(out));
    }

    @AfterEach
    public void clear() {
        System.setOut(sout);
    }


    @Test
    public void alertLargeExpenseTestCanPrintBoundary() {
        ent.setBudget(budget);
        ent.setPercentage(percentage);
        ent.alertLargeExpense(e1);
        assertEquals("Warning! Large Entertainment Expense!!!", out.toString().trim());
    }

    @Test
    public void alertLargeExpenseTestCanPrint() {
        ent.setBudget(budget);
        ent.setPercentage(percentage);
        ent.alertLargeExpense(e4);
        assertEquals("Warning! Large Entertainment Expense!!!", out.toString().trim());
    }

    @Test
    public void alertLargeExpenseTestCannotPrint() {
        ent.setBudget(budget);
        ent.setPercentage(percentage);
        ent.alertLargeExpense(e2);
        assertEquals("", out.toString().trim());
    }

    @Test
    public void remainingBudgetTestTypeTrue() {
        ent.setBudget(budget);
        ent.remainingBudget(e2);
        assertEquals(9000, ent.getRemainingBudget());
    }

    @Test
    public void remainingBudgetTestTypeFalse() {
        ent.setBudget(budget);
        ent.remainingBudget(e3);
        assertEquals(10000, ent.getRemainingBudget());
    }

    @Test
    public void testAddObserver() {
        ent.addObserver(EventLog.getInstance());
        ent.notifyObservers("A1");
        EventLog el = EventLog.getInstance();
        el.printOnConsole();
        String s = out.toString().trim();
        assertTrue(s.contains("A1"));
    }

}
