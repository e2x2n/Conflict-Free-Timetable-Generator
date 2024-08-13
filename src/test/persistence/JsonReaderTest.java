package persistence;

import model.Schedule;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Schedule schedule = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptySchedule() {
        JsonReader reader = new JsonReader("./data/testReaderEmptySchedule.json");
        try {
            Schedule schedule = reader.read();
            assertEquals("My Schedule", schedule.getName());
            assertEquals(0, schedule.getSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralSchedule() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralSchedule.json");
        try {
            Schedule schedule = reader.read();
            assertEquals("My Schedule", schedule.getName());
            assertEquals(2, schedule.getSize());
            checkTask("1", "1", 1, "1",
                    "Mon 12:00-14:00, Tue 14:00-16:00", schedule.getTask(0));
            checkTask("2", "2", 2, "2",
                    "Fri 08:00-19:00", schedule.getTask(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
