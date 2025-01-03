package model;


// Represents an observer interface for observing changes in the subject.
public interface Observer {


    // EFFECTS: This method is called by the subject to notify the observer of a change,
    //          with the provided message containing information about the change.
    void update(String message);
}
