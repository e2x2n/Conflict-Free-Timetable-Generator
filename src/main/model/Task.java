package model;

import org.json.JSONObject;
import persistence.Writable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

// the X, the user's tasks with their title, description, priority, category, and time slots
public class Task implements Writable {
    private String title;
    private String description;
    private int priority;
    private String category;
    private String timeSlot;
    private ArrayList<TimeInterval> intervals = new ArrayList<>();

    // Constructor
    public Task(String title, String description, int priority, String category, String timeSlot) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.category = category;
        this.timeSlot = timeSlot;
        digestTimeslot();
    }

    // Default constructor, for testing purposes
    public Task() {
        this.title = "";
        this.description = "";
        this.priority = 0;
        this.category = "";
        this.timeSlot = "";
    }

    // Constructor with title and intervals only
    public Task(String title, ArrayList<TimeInterval> intervals) {
        this.title = title;
        this.intervals = intervals;
    }

    // Copy constructor
    public Task(Task task) {
        this.title = task.title;
        this.description = task.description;
        this.priority = task.priority;
        this.category = task.category;
        this.timeSlot = task.timeSlot;
        this.intervals = task.intervals;
    }

    // EFFECTS: returns a string representation of the task
    public String printTask() {
        return "Title: " + title + "\nDescription: " + description + "\nPriority: " + priority
                + "\nCategory: " + category + "\nSlot: " + timeSlot;
    }

    // EFFECTS: transforms the time slot string into TimeInterval objects in integer form
    @SuppressWarnings("methodlength")
    public void digestTimeslot() {
        String[] timeSlotsSlice;
        String dayOfWeek;
        int day;
        int startTime;
        int endTime;

        timeSlotsSlice = timeSlot.split(", ");
        for (String slot : timeSlotsSlice) {
            slot = slot.replaceAll(":", "");
            dayOfWeek = slot.substring(0, 3);
            startTime = Integer.parseInt(slot.substring(4, 8));
            endTime = Integer.parseInt(slot.substring(9, 13));
            switch (dayOfWeek) {
                case "Sun":
                    day = 0;
                    break;
                case "Mon":
                    day = 1;
                    break;
                case "Tue":
                    day = 2;
                    break;
                case "Wed":
                    day = 3;
                    break;
                case "Thu":
                    day = 4;
                    break;
                case "Fri":
                    day = 5;
                    break;
                case "Sat":
                    day = 6;
                    break;
                default:
                    day = 10;
            }
            intervals.add(new TimeInterval(day * 2400 + startTime, day * 2400 + endTime));
        }
    }

    // EFFECTS: returns a string representation of the time slot
    public String returnTimeslot(TimeInterval interval) {
        StringBuilder result = new StringBuilder();
        int day = interval.getStartTime() / 2400;
        int startTime = interval.getStartTime() % 2400;
        int endTime = interval.getEndTime() % 2400;
        result.append(dayOfWeek(day)).append(" ").append(startTime / 100).append(":")
                .append(String.format("%02d", startTime % 100))
                .append("-").append(endTime / 100).append(":")
                .append(String.format("%02d", endTime % 100));
        timeSlot = result.toString();
        return timeSlot;
    }

    // EFFECTS: returns a string representation of the day of the week
    public String dayOfWeek(int day) {
        switch (day) {
            case 0:
                return "Sun";
            case 1:
                return "Mon";
            case 2:
                return "Tue";
            case 3:
                return "Wed";
            case 4:
                return "Thu";
            case 5:
                return "Fri";
            case 6:
                return "Sat";
            default:
                return "Invalid day";
        }
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("title", title);
        json.put("description", description);
        json.put("priority", priority);
        json.put("category", category);
        json.put("timeSlot", timeSlot);
        return json;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public ArrayList<TimeInterval> getIntervals() {
        return intervals;
    }

    public void setIntervals(ArrayList<TimeInterval> intervals) {
        this.intervals = intervals;
    }
}
