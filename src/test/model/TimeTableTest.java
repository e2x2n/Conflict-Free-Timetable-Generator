package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class TimeTableTest {
    private TimeTable timeTable;
    private Task task1;
    private Task task2;

    @BeforeEach
    public void setUp() {
        timeTable = new TimeTable();
        task1 = new Task();
        task2 = new Task();
    }

    @Test
    void testConstructor() {
        assert timeTable.isEmpty();
    }

    @Test
    void testConstructorWithTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        timeTable = new TimeTable(tasks);
        assertEquals(2, timeTable.size());
    }

    @Test
    void testAddTask() {
        timeTable.addTask(new Task());
        assertEquals(1, timeTable.size());
    }

    @Test
    void testRemoveTask() {
        Task task = new Task();
        timeTable.addTask(task);
        assertFalse(timeTable.isEmpty());
        timeTable.removeTask(task);
        assertTrue(timeTable.isEmpty());
    }

    @Test
    void testIsEmpty() {
        assertTrue(timeTable.isEmpty());
        timeTable.addTask(task1);
        assertFalse(timeTable.isEmpty());
    }

    @Test
    void testSize() {
        assertEquals(0, timeTable.size());
        timeTable.addTask(task1);
        assertEquals(1, timeTable.size());
    }

    @Test
    void testGetTasks() {
        timeTable.addTask(task1);
        timeTable.addTask(task2);
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        assertEquals(tasks, timeTable.getTasks());
    }

    @Test
    void testSetTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        timeTable.setTasks(tasks);
        assertEquals(tasks, timeTable.getTasks());
    }
}
