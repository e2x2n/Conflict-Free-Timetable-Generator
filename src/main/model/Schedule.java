package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

// the Y, preserves the user's inputted tasks
public class Schedule extends Tasks implements Writable {
    private String name;

    public Schedule(String name) {
        this.name = name;
    }

    public Schedule() {
        this.name = "My Schedule";
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("tasks", tasksToJson());
        return json;
    }

    // EFFECTS: returns tasks in this schedule as a JSON array
    private JSONArray tasksToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Task t : tasks) {
            jsonArray.put(t.toJson());
        }
        return jsonArray;
    }

    @Override
    public void addTask(Task task) {
        EventLog.getInstance().logEvent(new Event("Added task: " + task.getTitle()));
        tasks.add(task);
    }

    @Override
    public void removeTask(Task task) {
        EventLog.getInstance().logEvent(new Event("Removed task: " + task.getTitle()));
        tasks.remove(task);
    }

    @Override
    public void clearTasks() {
        EventLog.getInstance().logEvent(new Event("Cleared all tasks"));
        tasks.clear();
    }

    // EFFECTS: returns the name of the schedule
    public String getName() {
        return name;
    }

    // MODIFIES: this
    // EFFECTS: sets the name of the schedule
    public void setName(String name) {
        this.name = name;
    }
}
