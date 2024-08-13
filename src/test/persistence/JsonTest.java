package persistence;

import model.Task;

import static org.junit.jupiter.api.Assertions.*;

public class JsonTest {
    protected void checkTask(String title, String description, int priority,
                             String category, String timeSlot, Task task) {
        assertEquals(title, task.getTitle());
        assertEquals(description, task.getDescription());
        assertEquals(priority, task.getPriority());
        assertEquals(category, task.getCategory());
        assertEquals(timeSlot, task.getTimeSlot());
    }
}
