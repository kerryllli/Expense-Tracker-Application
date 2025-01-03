package model.transcation;


// Represents a Date having year, month, and day2
public class Date {

    private int year;
    private int month;
    private int day;

    //EFFECTS: Constructs an instance of the Date with specified year, month, day.
    public Date(int year, int month, int day) {
        this.month = month;
        this.year = year;
        this.day = day;
    }

    // EFFECTS: Transfer the Date with month, day and year into short format date (month-day-year)
    public String shortFormatDate() {
        return toDoubleDigits(month) + "-" + toDoubleDigits(day) + "-" + Integer.toString(year);
    }

    // EFFECTS: Transfer the single digit into double digits (e.g. 3 will be transferred into 03)
    private String toDoubleDigits(int i) {
        if (i <= 9) {
            return "0" + (Integer.toString(i));
        } else {
            return (Integer.toString(i));
        }
    }

    //Getters:
    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }
}
