package model;

// the time interval of a task in integer format
public class TimeInterval {
    // Time should from 0000 to 16800
    private int startTime;
    private int endTime;

    // Constructor
    public TimeInterval(int startTime, int endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // Default constructor, for testing purposes
    public TimeInterval() {
        this.startTime = 0;
        this.endTime = 0;
    }

    // EFFECTS: returns true if the time interval is overlapping with another time interval
    public Boolean isOverlapping(TimeInterval other) {
        return (this.startTime < other.endTime && this.endTime > other.startTime);
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }
}
