package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TasksTest {
    private Tasks tasks;
    Task task1;
    Task task2;
    Task task3;

    @BeforeEach
    public void runBefore() {
        tasks = new Tasks();
        task1 = new Task();
        task2 = new Task();
        task3 = new Task();
    }

    @Test
    void testConstructor() {
        assertEquals(0, tasks.getSize());
    }

    @Test
    void testAddTask() {
        assertEquals(0, tasks.getSize());
        tasks.addTask(task1);
        assertEquals(1, tasks.getSize());
        assertTrue(tasks.getTasks().contains(task1));
    }

    @Test
    void testGetTasks() {
        tasks.addTask(task1);
        tasks.addTask(task2);
        tasks.addTask(task3);
        assertEquals(3, tasks.getTasks().size());
        assertTrue(tasks.getTasks().contains(task1));
        assertTrue(tasks.getTasks().contains(task2));
        assertTrue(tasks.getTasks().contains(task3));
    }

    @Test
    void testRemoveTask() {
        tasks.addTask(task1);
        tasks.addTask(task2);
        tasks.addTask(task3);
        assertEquals(3, tasks.getSize());
        assertTrue(tasks.getTasks().contains(task2));
        tasks.removeTask(task2);
        assertEquals(2, tasks.getSize());
        assertFalse(tasks.getTasks().contains(task2));
    }

    @Test
    void testIsEmpty() {
        assertTrue(tasks.isEmpty());
        tasks.addTask(task1);
        assertFalse(tasks.isEmpty());
    }

    @Test
    void testClearTasks() {
        tasks.addTask(task1);
        tasks.addTask(task2);
        tasks.addTask(task3);
        assertEquals(3, tasks.getSize());
        tasks.clearTasks();
        assertEquals(0, tasks.getSize());
        assertTrue(tasks.isEmpty());
    }

    @Test
    void testGetSize() {
        assertEquals(0, tasks.getSize());
        tasks.addTask(task1);
        assertEquals(1, tasks.getSize());
    }

    @Test
    void testGetTask() {
        tasks.addTask(task1);
        tasks.addTask(task2);
        tasks.addTask(task3);
        assertEquals(task1, tasks.getTask(0));
        assertEquals(task2, tasks.getTask(1));
        assertEquals(task3, tasks.getTask(2));
    }
}
