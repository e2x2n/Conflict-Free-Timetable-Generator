package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    private Task task1;
    private Task task2;
    private Task task3;
    private Task task4;
    private Task task5;
    private Task task6;
    private Task task7;

    @BeforeEach
    public void runBefore() {
        task1 = new Task("1", "1", 1, "1", "Mon 12:00-15:00");
        task2 = new Task("2", "2", 1, "2", "Tue 12:00-15:00");
        task3 = new Task("3", "3", 1, "3", "Wed 12:00-15:00");
        task4 = new Task("4", "4", 1, "4", "Thu 12:00-15:00");
        task5 = new Task("5", "5", 1, "5", "Fri 12:00-15:00");
        task6 = new Task("6", "6", 1, "6", "Sat 12:00-15:00");
        task7 = new Task("7", "7", 1, "7", "Sun 12:00-15:00");
    }

    @Test
    void testConstructor() {
        Task task = new Task("1", "1", 1, "1", "Mon 12:00-15:00");
        assertEquals("1", task.getTitle());
        assertEquals("1", task.getDescription());
        assertEquals(1, task.getPriority());
        assertEquals("1", task.getCategory());
        assertEquals("Mon 12:00-15:00", task.getTimeSlot());
    }

    @Test
    void testConstructorEmpty() {
        Task task = new Task();
        assertEquals("", task.getTitle());
        assertEquals("", task.getDescription());
        assertEquals(0, task.getPriority());
        assertEquals("", task.getCategory());
        assertEquals("", task.getTimeSlot());
    }

    @Test
    void testConstructorWithIntervals() {
        ArrayList<TimeInterval> intervals;
        intervals = new ArrayList<>();
        intervals.add(new TimeInterval(1200, 1500));
        intervals.add(new TimeInterval(4600, 4800));
        Task task = new Task("1", intervals);
        assertEquals("1", task.getTitle());
        assertEquals(intervals, task.getIntervals());
    }

    @Test
    void testCopyConstructor() {
        Task task = new Task(task1);
        assertEquals("1", task.getTitle());
        assertEquals("1", task.getDescription());
        assertEquals(1, task.getPriority());
        assertEquals("1", task.getCategory());
        assertEquals("Mon 12:00-15:00", task.getTimeSlot());
    }

    @Test
    void testPrintTask() {
        assertEquals("Title: 1\nDescription: 1\nPriority: 1\nCategory: 1\nSlot: Mon 12:00-15:00",
                task1.printTask());
    }

    @Test
    void testDigestTimeslotSun() {
        task7.digestTimeslot();
        ArrayList<TimeInterval> intervals;
        intervals = new ArrayList<>();
        intervals.add(new TimeInterval(1200, 1500));
        task7.setIntervals(intervals);
        assertEquals(intervals, task7.getIntervals());
    }

    @Test
    void testDigestTimeslotMon() {
        task1.digestTimeslot();
        ArrayList<TimeInterval> intervals;
        intervals = new ArrayList<>();
        intervals.add(new TimeInterval(3600, 3900));
        task1.setIntervals(intervals);
        assertEquals(intervals, task1.getIntervals());
    }

    @Test
    void testDigestTimeslotTue() {
        task2.digestTimeslot();
        ArrayList<TimeInterval> intervals;
        intervals = new ArrayList<>();
        intervals.add(new TimeInterval(6000, 6300));
        task2.setIntervals(intervals);
        assertEquals(intervals, task2.getIntervals());
    }

    @Test
    void testDigestTimeslotWed() {
        task3.digestTimeslot();
        ArrayList<TimeInterval> intervals;
        intervals = new ArrayList<>();
        intervals.add(new TimeInterval(8400, 8700));
        task3.setIntervals(intervals);
        assertEquals(intervals, task3.getIntervals());
    }

    @Test
    void testDigestTimeslotThu() {
        task4.digestTimeslot();
        ArrayList<TimeInterval> intervals;
        intervals = new ArrayList<>();
        intervals.add(new TimeInterval(10800, 11100));
        task4.setIntervals(intervals);
        assertEquals(intervals, task4.getIntervals());
    }

    @Test
    void testDigestTimeslotFri() {
        task5.digestTimeslot();
        ArrayList<TimeInterval> intervals;
        intervals = new ArrayList<>();
        intervals.add(new TimeInterval(13200, 13500));
        task5.setIntervals(intervals);
        assertEquals(intervals, task5.getIntervals());
    }

    @Test
    void testDigestTimeslotSat() {
        task6.digestTimeslot();
        ArrayList<TimeInterval> intervals;
        intervals = new ArrayList<>();
        intervals.add(new TimeInterval(15600, 15900));
        task6.setIntervals(intervals);
        assertEquals(intervals, task6.getIntervals());
    }

    @Test
    void testDigestTimeslotMultiple() {
        task1 = new Task("1", "1", 1, "1", "Mon 12:00-15:00, Mon 15:00-18:00");
        task1.digestTimeslot();
        ArrayList<TimeInterval> intervals;
        intervals = new ArrayList<>();
        intervals.add(new TimeInterval(3600, 3900));
        intervals.add(new TimeInterval(3900, 4200));
        task1.setIntervals(intervals);
        assertEquals(intervals, task1.getIntervals());
    }

    @Test
    void testDigestTimeslotMultipleDays() {
        task1 = new Task("1", "1", 1, "1", "Mon 12:00-15:00, Tue 15:00-18:00");
        task1.digestTimeslot();
        ArrayList<TimeInterval> intervals;
        intervals = new ArrayList<>();
        intervals.add(new TimeInterval(3600, 3900));
        intervals.add(new TimeInterval(8700, 9000));
        task1.setIntervals(intervals);
        assertEquals(intervals, task1.getIntervals());
    }

    @Test
    void testDigestTimeslotInvalid() {
        task1 = new Task("1", "1", 1, "1", "Tuu 12:00-15:00");
        task1.digestTimeslot();
        ArrayList<TimeInterval> intervals;
        intervals = new ArrayList<>();
        intervals.add(new TimeInterval(25200, 25500));
        task1.setIntervals(intervals);
        assertEquals(intervals, task1.getIntervals());
    }

    @Test
    void testReturnTimeslot() {
        TimeInterval interval = new TimeInterval(3600, 3900);
        assertEquals("Mon 12:00-15:00", task1.returnTimeslot(interval));
    }

    @Test
    void testDayOfWeek() {
        assertEquals("Mon", task1.dayOfWeek(1));
        assertEquals("Tue", task1.dayOfWeek(2));
        assertEquals("Wed", task1.dayOfWeek(3));
        assertEquals("Thu", task1.dayOfWeek(4));
        assertEquals("Fri", task1.dayOfWeek(5));
        assertEquals("Sat", task1.dayOfWeek(6));
        assertEquals("Sun", task1.dayOfWeek(0));
        assertEquals("Invalid day", task1.dayOfWeek(7));
    }

    @Test
    void testGetTitle() {
        assertEquals("1", task1.getTitle());
    }

    @Test
    void testSetTitle() {
        task1.setTitle("2");
        assertEquals("2", task1.getTitle());
    }

    @Test
    void testGetDescription() {
        assertEquals("1", task1.getDescription());
    }

    @Test
    void testSetDescription() {
        task1.setDescription("2");
        assertEquals("2", task1.getDescription());
    }

    @Test
    void testGetPriority() {
        assertEquals(1, task1.getPriority());
    }

    @Test
    void testSetPriority() {
        task1.setPriority(2);
        assertEquals(2, task1.getPriority());
    }

    @Test
    void testGetCategory() {
        assertEquals("1", task1.getCategory());
    }

    @Test
    void testSetCategory() {
        task1.setCategory("2");
        assertEquals("2", task1.getCategory());
    }

    @Test
    void testGetTimeSlot() {
        assertEquals("Mon 12:00-15:00", task1.getTimeSlot());
    }

    @Test
    void testSetTimeSlot() {
        task1.setTimeSlot("Tue 12:00-15:00");
        assertEquals("Tue 12:00-15:00", task1.getTimeSlot());
    }

    @Test
    void testGetIntervals() {
        task1.digestTimeslot();
        ArrayList<TimeInterval> intervals;
        intervals = new ArrayList<>();
        intervals.add(new TimeInterval(3600, 3900));
        task1.setIntervals(intervals);
        assertEquals(intervals, task1.getIntervals());
    }

    @Test
    void testSetIntervals() {
        ArrayList<TimeInterval> intervals;
        intervals = new ArrayList<>();
        intervals.add(new TimeInterval(3600, 3900));
        task1.setIntervals(intervals);
        assertEquals(intervals, task1.getIntervals());
    }
}
