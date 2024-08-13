package model;

import java.util.ArrayList;
import java.util.List;

// the class contains the list of tasks, will be used in the generation of the schedule
public class TimeTable extends Tasks {

    // Constructor
    public TimeTable() {
        super();
    }

    // Constructor with tasks
    public TimeTable(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    // EFFECTS: returns the number of tasks in the timetable
    public int size() {
        return tasks.size();
    }

    // MODIFIES: this
    // EFFECTS: sets the list of tasks to the given timetable
    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
