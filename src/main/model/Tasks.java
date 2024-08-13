package model;

import java.util.ArrayList;
import java.util.List;

// the super class for TimeTable and Schedule, contains most of the methods for both classes
public class Tasks {
    protected List<Task> tasks;

    // Constructor
    public Tasks() {
        this.tasks = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds a task to the list of tasks
    public void addTask(Task task) {
        tasks.add(task);
    }

    // EFFECTS: returns the list of tasks
    public List<Task> getTasks() {
        return tasks;
    }

    // REQUIRES: task is in the list of tasks
    // MODIFIES: this
    // EFFECTS: removes a task from the list of tasks
    public void removeTask(Task task) {
        tasks.remove(task);
    }

    // EFFECTS: returns true if the list of tasks is empty
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    // MODIFIES: this
    // EFFECTS: clears the list of tasks
    public void clearTasks() {
        tasks.clear();
    }

    // EFFECTS: returns the number of tasks in the list of tasks
    public int getSize() {
        return tasks.size();
    }

    // REQUIRES: return the task at the given index
    public Task getTask(int index) {
        return tasks.get(index);
    }
}
