package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ScheduleTest {
    private Schedule schedule;
    Task task1;
    Task task2;
    Task task3;

    @BeforeEach
    public void runBefore() {
        schedule = new Schedule();
        task1 = new Task();
        task2 = new Task();
        task3 = new Task();
    }

    @Test
    void testConstructor() {
        assertEquals(0, schedule.getSize());
    }

    @Test
    void testAddTask() {
        assertEquals(0, schedule.getSize());
        schedule.addTask(task1);
        assertEquals(1, schedule.getSize());
        assertTrue(schedule.getTasks().contains(task1));
    }

    @Test
    void testGetTasks() {
        schedule.addTask(task1);
        schedule.addTask(task2);
        schedule.addTask(task3);
        assertEquals(3, schedule.getTasks().size());
        assertTrue(schedule.getTasks().contains(task1));
        assertTrue(schedule.getTasks().contains(task2));
        assertTrue(schedule.getTasks().contains(task3));
    }

    @Test
    void testRemoveTask() {
        schedule.addTask(task1);
        schedule.addTask(task2);
        schedule.addTask(task3);
        assertEquals(3, schedule.getSize());
        assertTrue(schedule.getTasks().contains(task2));
        schedule.removeTask(task2);
        assertEquals(2, schedule.getSize());
        assertFalse(schedule.getTasks().contains(task2));
    }

    @Test
    void testIsEmpty() {
        assertTrue(schedule.isEmpty());
        schedule.addTask(task1);
        assertFalse(schedule.isEmpty());
    }

    @Test
    void testClearTasks() {
        schedule.addTask(task1);
        schedule.addTask(task2);
        schedule.addTask(task3);
        assertEquals(3, schedule.getSize());
        schedule.clearTasks();
        assertEquals(0, schedule.getSize());
        assertTrue(schedule.isEmpty());
    }

    @Test
    void testGetSize() {
        assertEquals(0, schedule.getSize());
        schedule.addTask(task1);
        assertEquals(1, schedule.getSize());
    }

    @Test
    void testGetTask() {
        schedule.addTask(task1);
        schedule.addTask(task2);
        schedule.addTask(task3);
        assertEquals(task1, schedule.getTask(0));
        assertEquals(task2, schedule.getTask(1));
        assertEquals(task3, schedule.getTask(2));
    }

    @Test
    void testGetName() {
        assertEquals("My Schedule", schedule.getName());
    }

    @Test
    void testSetName() {
        schedule.setName("New Schedule");
        assertEquals("New Schedule", schedule.getName());
    }
}