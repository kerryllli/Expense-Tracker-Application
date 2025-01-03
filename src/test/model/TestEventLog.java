package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for the EventLog class
 */
public class TestEventLog {
    private PrintStream systemOut = System.out;
    private ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();


    @BeforeEach
    public void loadEvents() {
        EventLog el = EventLog.getInstance();
        el.clear();
        el.update("A1");
        el.update("A2");
        el.update("A3");
        System.setOut(new PrintStream(byteArrayOutputStream));
    }

    @AfterEach
    public void destroy() {
        System.setOut(systemOut);
    }

    @Test
    public void testLogEvent() {
        List<Event> l = new ArrayList<Event>();

        EventLog el = EventLog.getInstance();
        for (Event next : el) {
            l.add(next);
        }

        assertEquals("A1", l.get(0).getDescription());
        assertEquals("A2", l.get(1).getDescription());
        assertEquals("A3", l.get(2).getDescription());
    }

    @Test
    public void testPrintOnConsole() {
        EventLog el = EventLog.getInstance();
        el.printOnConsole();
        String s = byteArrayOutputStream.toString().trim();
        assertTrue(s.contains("A1"));
        assertTrue(s.contains("A2"));
        assertTrue(s.contains("A3"));
    }
}
