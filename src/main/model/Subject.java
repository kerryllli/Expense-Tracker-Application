package model;

import java.util.ArrayList;
import java.util.List;

// Represents a subject that can be observed by observers for changes in its state.
public abstract class Subject {

    protected List<Observer> observers;

    // EFFECTS: Constructs a Subject object with an empty list of observers.
    public Subject() {
        observers = new ArrayList<Observer>();
    }

    // MODIFIES: this
    // EFFECTS: Adds the given observer to the list of observers.
    public void addObserver(Observer o) {
        observers.add(o);
    }

    // EFFECTS: Notifies all observers in the list of observers about the change
    //          by calling their update method with the provided message.
    public void notifyObservers(String message) {
        for (Observer observer: observers) {
            observer.update(message);
        }
    }

}
