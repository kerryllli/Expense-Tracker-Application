package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the Event class
 */
public class TestEvent {
    private Event e;
    private Date d;

    //NOTE: these tests might fail if time at which line (2) below is executed
    //is different from time that line (1) is executed.  Lines (1) and (2) must
    //run in same millisecond for this test to make sense and pass.

    @BeforeEach
    public void runBefore() {
        e = new Event("The budget has been viewed!");   // (1)
        d = Calendar.getInstance().getTime();   // (2)
    }

    @Test
    public void testEvent() {
        assertEquals("The budget has been viewed!", e.getDescription());
        assertEquals(d.toString(), e.getDate().toString());
    }

    @Test
    public void testToString() {
        assertEquals(d.toString() + "\n" + "The budget has been viewed!", e.toString());
    }
}

