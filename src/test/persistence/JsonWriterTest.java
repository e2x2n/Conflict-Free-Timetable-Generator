package persistence;

import model.Schedule;
import model.Task;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Schedule schedule = new Schedule("My Schedule");
            JsonWriter writer = new JsonWriter("./data/\0invalid:file.json");
            writer.open();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptySchedule() {
        try {
            Schedule schedule = new Schedule("My Schedule");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptySchedule.json");
            writer.open();
            writer.write(schedule);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptySchedule.json");
            schedule = reader.read();
            assertEquals("My Schedule", schedule.getName());
            assertEquals(0, schedule.getSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralSchedule() {
        try {
            Schedule schedule = new Schedule("My Schedule");
            schedule.addTask(new Task("1", "1", 1, "1",
                    "Mon 12:00-14:00, Tue 14:00-16:00"));
            schedule.addTask(new Task("2", "2", 2, "2",
                    "Fri 08:00-19:00"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralSchedule.json");
            writer.open();
            writer.write(schedule);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralSchedule.json");
            schedule = reader.read();
            assertEquals("My Schedule", schedule.getName());
            assertEquals(2, schedule.getSize());
            checkTask("1", "1", 1, "1",
                    "Mon 12:00-14:00, Tue 14:00-16:00", schedule.getTask(0));
            checkTask("2", "2", 2, "2",
                    "Fri 08:00-19:00", schedule.getTask(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
