package model;

import java.util.Calendar;
import java.util.Date;


// Represents a transaction event with a date/time stamp and a description.

public class Event {
    private Date dateLogged;
    private String description;

    // EFFECTS: Constructs an Event object with the provided description and the current date/time stamp.
    public Event(String description) {
        dateLogged = Calendar.getInstance().getTime();
        this.description = description;
    }

    // EFFECTS: Returns the date and time when this event occurred.
    public Date getDate() {
        return dateLogged;
    }

    // EFFECTS: Returns the description of this event.
    public String getDescription() {
        return description;
    }

    // EFFECTS: Returns a string representation of this event, including the date/time and description.
    @Override
    public String toString() {
        return dateLogged.toString() + "\n" + description;
    }
}
