package model;

import model.transcation.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BalanceTest {

    Balance balance;
    ArrayList<Expense> el;
    ArrayList<Income> il;
    Expense expense1;
    Expense expense2;
    Expense expense3;
    Expense expense4;
    Expense expense5;
    Income income1;
    Income income2;
    Income income3;
    Income income4;
    Income income5;
    Double a1;
    Double a2;
    Double a3;
    Date d1;
    Date d2;
    Date d3;
    Date d4;

    @BeforeEach
    public void setUp() {
        d1 = new Date (2024,12,24);
        d2 = new Date (2024,11,20);
        d3 = new Date (2025, 12, 24);
        d4 = new Date (2024,12,20);
        el = new ArrayList<Expense>();
        il = new ArrayList<Income>();
        balance = new Balance(el,il);
        a1 = 100.00;
        a2 = 200.50;
        a3 = 500.00;
        // expense 1 & 2 :
        expense1 = new Expense(d1,a1, TransactionType.EDUCATION);
        expense2 = new Expense(d2,a2,TransactionType.FOOD);
        expense3 = new Expense(d1,a2,TransactionType.OTHER);
        expense4 = new Expense(d3,a1,TransactionType.HEALTHCARE);
        expense5 = new Expense(d4,a3,TransactionType.HOUSING);
        income1 = new Income(d1,a1,TransactionType.OTHER);
        income2 = new Income(d2,a2,TransactionType.SALARIES);
        income3 = new Income(d1,a2,TransactionType.SALARIES);
        income4 = new Income(d3,a1,TransactionType.HOUSING);
        income5 = new Income(d4,a3,TransactionType.SALARIES);
    }

    @Test
    public void dayExpenseTestOne() {
        el.add(expense1);
        assertEquals(100.00, balance.dayExpense(d1));
    }

    @Test
    public void dayExpenseTestMultipleSameDay() {
        el.add(expense1);
        el.add(expense3);
        assertEquals(300.50, balance.dayExpense(d1));
    }

    @Test
    public void dayExpenseTestMultipleDifDay() {
        el.add(expense1);
        el.add(expense2);
        assertEquals(100.00, balance.dayExpense(d1));
    }

    @Test
    public void monthExpenseTestOne() {
        el.add(expense2);
        assertEquals(200.50, balance.monthExpense(11,2024));
    }

    @Test
    public void monthExpenseTestMultipleSameMonthDifferentYear() {
        el.add(expense1);
        el.add(expense4);
        assertEquals(100.00, balance.monthExpense(12,2024));
    }

    @Test
    public void monthExpenseTestMultipleSameYearDifferentMonth() {
        el.add(expense1);
        el.add(expense2);
        assertEquals(200.50, balance.monthExpense(11,2024));
    }

    @Test
    public void yearExpenseTestOne() {
        el.add(expense1);
        assertEquals(100.00, balance.yearExpense(2024));
    }

    @Test
    public void yearExpenseTestMultipleSameYear() {
        el.add(expense1);
        el.add(expense2);
        assertEquals(300.50,balance.yearExpense(2024));
    }

    @Test
    public void yearExpenseTestMultipleDifYear() {
        el.add(expense1);
        el.add(expense4);
        assertEquals(100.00, balance.yearExpense(2025));
    }

    @Test
    public void dayIncomeTestOne() {
        il.add(income1);
        assertEquals(100.00, balance.dayIncome(d1));
    }

    @Test
    public void dayIncomeTestMultipleSameDay() {
        il.add(income1);
        il.add(income3);
        balance = new Balance(el, il);
        assertEquals(300.50, balance.dayIncome(d1));
    }

    @Test
    public void dayIncomeTestMultipleDifDay() {
        il.add(income1);
        il.add(income2);
        assertEquals(100.00, balance.dayIncome(d1));
    }

    @Test
    public void monthIncomeTestOne() {
        il.add(income2);
        assertEquals(200.50, balance.monthIncome(11,2024));
    }

    @Test
    public void monthIncomeTestMultipleSameMonthDifferentYear() {
        il.add(income1);
        il.add(income4);
        assertEquals(100.00, balance.monthIncome(12,2024));
    }

    @Test
    public void monthIncomeTestMultipleSameYearDifferentMonth() {
        il.add(income1);
        il.add(income2);
        assertEquals(200.50, balance.monthIncome(11,2024));
    }

    @Test
    public void yearIncomeTestOne() {
        il.add(income1);
        assertEquals(100.00, balance.yearIncome(2024));
    }

    @Test
    public void yearIncomeTestMultipleSameYear() {
        il.add(income1);
        il.add(income2);
        assertEquals(300.50,balance.yearIncome(2024));
    }

    @Test
    public void yearIncomeTestMultipleDifYear() {
        il.add(income1);
        il.add(income4);
        assertEquals(100.00, balance.yearIncome(2025));
    }

    @Test
    public void monthBalanceTestPositiveValue() {
        el.add(expense1);
        il.add(income1);
        il.add(income5);
        assertEquals(500,balance.monthBalance(12, 2024));
    }

    @Test
    public void monthBalanceTestNegativeValue() {
        el.add(expense1);
        el.add(expense3);
        il.add(income1);
        assertEquals(-200.50, balance.monthBalance(12,2024));
    }

    @Test
    public void yearBalanceTestPositiveValue() {
        el.add(expense1);
        il.add(income2);
        il.add(income1);
        assertEquals(200.50, balance.yearBalance(2024));
    }

    @Test
    public void yearBalanceTestNegativeValue() {
        el.add(expense1);
        el.add(expense5);
        il.add(income1);
        assertEquals(-500.00, balance.yearBalance(2024));
    }

}
