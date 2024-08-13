package model;

import java.util.ArrayList;
import java.util.List;

public class TimeTableGenerator {

    // EFFECTS: generates all possible timetables from the given list of tasks,
    //          and returns a string representation of all the timetables
    public String generateAllTimeTables(List<Task> tasks) {
        EventLog.getInstance().logEvent(new Event("Generated all possible timetables"));
        List<TimeTable> allTimeTables = new ArrayList<>();
        generateTimeTablesHelper(tasks, 0, new TimeTable(), allTimeTables);

        StringBuilder result = new StringBuilder();

        for (TimeTable timeTable : allTimeTables) {
            result.append("TimeTable ").append(allTimeTables.indexOf(timeTable) + 1).append(":\n");
            for (Task task : timeTable.getTasks()) {
                result.append(task.printTask()).append("\n").append("\n");
            }
            result.append("\n");
        }
        return result.toString();
    }

    // MODIFIES: allTimeTables
    // EFFECTS: generates all possible timetables from the given list of tasks
    @SuppressWarnings("methodlength")
    private void generateTimeTablesHelper(List<Task> tasks, int currentIndex,
                                          TimeTable currentTimeTable, List<TimeTable> allTimeTables) {
        if (currentIndex == tasks.size()) {
            allTimeTables.add(new TimeTable(new ArrayList<>(currentTimeTable.getTasks())));
            return;
        }

        Task currentTask = tasks.get(currentIndex);
        List<TimeInterval> intervalsCopy = new ArrayList<>(currentTask.getIntervals());
        for (TimeInterval interval : intervalsCopy) {
            boolean overlaps = false;
            for (Task assignedTask : currentTimeTable.getTasks()) {
                if (interval.isOverlapping(assignedTask.getIntervals().get(0))) {
                    overlaps = true;
                    break;
                }
            }

            if (!overlaps) {
                Task modifiedTask = new Task(currentTask);
                modifiedTask.setTimeSlot(modifiedTask.returnTimeslot(interval));
                modifiedTask.setIntervals(new ArrayList<>());
                modifiedTask.getIntervals().add(interval);

                currentTimeTable.addTask(modifiedTask);

                generateTimeTablesHelper(tasks, currentIndex + 1, currentTimeTable, allTimeTables);

                currentTimeTable.getTasks().remove(currentTimeTable.getTasks().size() - 1);
            }
        }
    }
}
