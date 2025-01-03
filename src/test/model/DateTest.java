package model;

import model.transcation.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateTest {

    Date d1;
    Date d2;
    Date d3;

    @BeforeEach
    public void setUp() {
        d1 = new Date(2024,12,23);
        d2 = new Date(2024,1,2);
        d3 = new Date(2024,6,15);
    }

    @Test
    public void constructorTest() {
        assertEquals(2024, d1.getYear());
        assertEquals(12, d1.getMonth());
        assertEquals(23, d1.getDay());
    }

    @Test
    public void shortFormatTest() {
        assertEquals("12-23-2024", d1.shortFormatDate());
        assertEquals("01-02-2024", d2.shortFormatDate());
        assertEquals("06-15-2024", d3.shortFormatDate());
    }
}
