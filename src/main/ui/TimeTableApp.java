package ui;

import model.Task;
import model.Schedule;
import model.TimeTableGenerator;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// the core of the application, the user interface
public class TimeTableApp {
    private Scanner input;
    private Schedule schedule;
    private String name;
    private static final String JSON_STORE = "./data/schedule.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    public Schedule getSchedule() {
        return schedule;
    }

    // EFFECTS: runs the timetable application
    public TimeTableApp() throws FileNotFoundException {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        init();
//        runTaskManager();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    public void runTaskManager() {
        boolean running = true;
        String command;

        init();

        while (running) {
            displayMenu();
            command = input.next();
            input.nextLine();
            command = command.toLowerCase();
            if (command.equals("q")) {
                running = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    // EFFECTS: displays the menu
    public void displayMenu() {
        System.out.println("Please select an option:");
        System.out.println("[A] Add a task");
        System.out.println("[R] Remove a task");
        System.out.println("[V] View tasks");
        System.out.println("[G] Generate timetable");
        System.out.println("[S] Save schedule to file");
        System.out.println("[L] Load schedule from file");
        System.out.println("[Q] Quit");
    }

    // MODIFIES: this
    // EFFECTS: initializes the application
    public void init() {
        input = new Scanner(System.in);
        schedule = new Schedule();
        System.out.println("Welcome to Task Manager!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    @SuppressWarnings("methodlength")
    public void processCommand(String command) {
        switch (command) {
            case "a":
                addTask();
                break;
            case "r":
                removeTask();
                break;
            case "v":
                viewTasks();
                break;
            case "g":
                generateTimeTables();
                break;
            case "s":
                saveSchedule();
                break;
            case "l":
                loadSchedule();
                break;
            default:
                System.out.println("Invalid command. Please try again.");
                break;
        }
    }

    public String generateTimeTables() {
//        System.out.println("\n");
        TimeTableGenerator ttg = new TimeTableGenerator();
//        System.out.println(ttg.generateAllTimeTables(schedule.getTasks()));
        return ttg.generateAllTimeTables(schedule.getTasks());
    }

    // MODIFIES: this
    // EFFECTS: adds a task to the schedule
    public void addTask() {
        System.out.println("Please enter the title of the task:");
        String title = input.nextLine();
        System.out.println("Please enter the description of the task:");
        String description = input.nextLine();
        System.out.println("Please enter the priority of the task:"
                + "\n(1: Low, 2: Medium, 3: High, 4: Urgent)");
        int priority = Integer.parseInt(input.nextLine());
        System.out.println("Please enter the category of the task:");
        String category = input.nextLine();
        System.out.println("Please enter all the available timeslots of the task (separated by \",\"): \n"
                + " (e.g. Mon 12:00-14:00, Tue 14:00-16:00)");
        String timeSlot = input.nextLine();

        Task task = new Task(title, description, priority, category, timeSlot);
        schedule.addTask(task);
        task.digestTimeslot();

        System.out.println("Task added successfully!\n");
    }

    // MODIFIES: this
    // EFFECTS: removes a task from the schedule
    public void viewTasks() {
        if (!schedule.isEmpty()) {
            for (Task task : schedule.getTasks()) {
                System.out.println("Task" + (schedule.getTasks().indexOf(task) + 1) + ": ");
                System.out.println(task.printTask() + "\n");
            }
        } else {
            System.out.println("No tasks available!\n");
        }
    }

    // MODIFIES: this
    // EFFECTS: removes a task from the schedule
    public void removeTask() {
        if (!schedule.isEmpty()) {
            System.out.println("Please enter the index of the task you want to remove:");
            int index = Integer.parseInt(input.next());
            String name = schedule.getTask(index - 1).getTitle();
            schedule.removeTask(schedule.getTask(index - 1));
            System.out.println("Task" + index + " \"" + name + "\" " + "removed successfully!\n");
        } else {
            System.out.println("No tasks available!\n");
        }
    }

    // EFFECTS: saves the schedule to file
    public void saveSchedule() {
        try {
            jsonWriter.open();
            jsonWriter.write(schedule);
            jsonWriter.close();
            System.out.println("Saved " + schedule.getName() + " to " + JSON_STORE + "\n");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE + "\n");
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    public void loadSchedule() {
        try {
            schedule = jsonReader.read();
            System.out.println("Loaded " + schedule.getName() + " from " + JSON_STORE + "\n");
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE + "\n");
        }
    }
}
