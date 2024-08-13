package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class TimeTableGeneratorTest {
    private TimeTableGenerator timeTableGenerator;
    private List<Task> tasks1;
    private List<Task> tasks2;
    private List<Task> tasks3;

    @BeforeEach
    public void setUp() {
        timeTableGenerator = new TimeTableGenerator();
        Task task1 = new Task("1", "1", 1, "1",
                "Mon 12:00-15:00, Tue 14:00-16:00");
        Task task2 = new Task("2", "2", 1, "2",
                "Mon 12:00-15:00, Tue 14:00-16:00");
        Task task3 = new Task("3", "3", 1, "3",
                "Fri 14:00-16:00");
        tasks1 = new ArrayList<>();
        tasks1.add(task1);
        tasks1.add(task2);
        tasks2 = new ArrayList<>();
        tasks2.add(task1);
        tasks2.add(task3);
        tasks3 = new ArrayList<>();
        tasks3.add(task1);
        tasks3.add(task2);
        tasks3.add(task3);
    }

    @Test
    void testGenerateAllTimeTables() {
        String expected1 = "TimeTable 1:\n" +
                "Title: 1\n" +
                "Description: 1\n" +
                "Priority: 1\n" +
                "Category: 1\n" +
                "Slot: Mon 12:00-15:00\n" +
                "\n" +
                "Title: 2\n" +
                "Description: 2\n" +
                "Priority: 1\n" +
                "Category: 2\n" +
                "Slot: Tue 14:00-16:00\n" +
                "\n" +
                "\n" +
                "TimeTable 2:\n" +
                "Title: 1\n" +
                "Description: 1\n" +
                "Priority: 1\n" +
                "Category: 1\n" +
                "Slot: Tue 14:00-16:00\n" +
                "\n" +
                "Title: 2\n" +
                "Description: 2\n" +
                "Priority: 1\n" +
                "Category: 2\n" +
                "Slot: Mon 12:00-15:00\n" +
                "\n" +
                "\n";

        String expected2 = "TimeTable 1:\n" +
                "Title: 1\n" +
                "Description: 1\n" +
                "Priority: 1\n" +
                "Category: 1\n" +
                "Slot: Mon 12:00-15:00\n" +
                "\n" +
                "Title: 3\n" +
                "Description: 3\n" +
                "Priority: 1\n" +
                "Category: 3\n" +
                "Slot: Fri 14:00-16:00\n" +
                "\n" +
                "\n" +
                "TimeTable 2:\n" +
                "Title: 1\n" +
                "Description: 1\n" +
                "Priority: 1\n" +
                "Category: 1\n" +
                "Slot: Tue 14:00-16:00\n" +
                "\n" +
                "Title: 3\n" +
                "Description: 3\n" +
                "Priority: 1\n" +
                "Category: 3\n" +
                "Slot: Fri 14:00-16:00\n" +
                "\n" +
                "\n";

        String expected3 = "TimeTable 1:\n" +
                "Title: 1\n" +
                "Description: 1\n" +
                "Priority: 1\n" +
                "Category: 1\n" +
                "Slot: Mon 12:00-15:00\n" +
                "\n" +
                "Title: 2\n" +
                "Description: 2\n" +
                "Priority: 1\n" +
                "Category: 2\n" +
                "Slot: Tue 14:00-16:00\n" +
                "\n" +
                "Title: 3\n" +
                "Description: 3\n" +
                "Priority: 1\n" +
                "Category: 3\n" +
                "Slot: Fri 14:00-16:00\n" +
                "\n" +
                "\n" +
                "TimeTable 2:\n" +
                "Title: 1\n" +
                "Description: 1\n" +
                "Priority: 1\n" +
                "Category: 1\n" +
                "Slot: Tue 14:00-16:00\n" +
                "\n" +
                "Title: 2\n" +
                "Description: 2\n" +
                "Priority: 1\n" +
                "Category: 2\n" +
                "Slot: Mon 12:00-15:00\n" +
                "\n" +
                "Title: 3\n" +
                "Description: 3\n" +
                "Priority: 1\n" +
                "Category: 3\n" +
                "Slot: Fri 14:00-16:00\n" +
                "\n" +
                "\n";

        assertEquals(expected1, timeTableGenerator.generateAllTimeTables(tasks1));
        assertEquals(expected2, timeTableGenerator.generateAllTimeTables(tasks2));
        assertEquals(expected3, timeTableGenerator.generateAllTimeTables(tasks3));
    }
}
