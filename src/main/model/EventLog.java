package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

// Represents a log of Expense Tracker events.

public class EventLog implements Iterable<Event>, Observer {
    private static EventLog theLog;
    private Collection<Event> events;

    // EFFECTS: Constructs a new EventLog object.
    private EventLog() {
        events = new ArrayList<Event>();
    }


    // EFFECTS: Returns the instance of EventLog, creating it if it doesn't already exist.
    public static EventLog getInstance() {
        if (theLog == null) {
            theLog = new EventLog();
        }
        return theLog;
    }


    // MODIFIES: this
    // EFFECTS: Adds the specified event to the event log.
    public void logEvent(Event e) {
        events.add(e);
    }


    // MODIFIES: this
    // EFFECTS: Clears the event log.
    public void clear() {
        events.clear();
    }

    // EFFECTS: Returns an iterator over the events in the event log.
    @Override
    public Iterator<Event> iterator() {
        return events.iterator();
    }

    // MODIFIES: this
    // EFFECTS: Updates the event log with the given message.
    @Override
    public void update(String message) {
        logEvent(new Event(message));
    }

    // EFFECTS: Prints events in the event log to the console.
    public void printOnConsole() {
        for (Event event: events) {
            System.out.println(event.toString());
        }
    }
}
