package persistence;

import model.Schedule;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.Task;
import org.json.*;

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads schedule from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Schedule read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseSchedule(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses schedule from JSON object and returns it
    private Schedule parseSchedule(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Schedule schedule = new Schedule();
        addTasks(schedule, jsonObject);
        return schedule;
    }

    // MODIFIES: schedule
    // EFFECTS: parses tasks from JSON object and adds them to schedule
    private void addTasks(Schedule schedule, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("tasks");
        for (Object json : jsonArray) {
            JSONObject nextTask = (JSONObject) json;
            addTask(schedule, nextTask);
        }
    }

    // MODIFIES: schedule
    // EFFECTS: parses task from JSON object and adds it to schedule
    private void addTask(Schedule schedule, JSONObject jsonObject) {
        String title = jsonObject.getString("title");
        String description = jsonObject.getString("description");
        int priority = jsonObject.getInt("priority");
        String category = jsonObject.getString("category");
        String timeSlot = jsonObject.getString("timeSlot");
        Task task = new Task(title, description, priority, category, timeSlot);
        schedule.addTask(task);
    }
}