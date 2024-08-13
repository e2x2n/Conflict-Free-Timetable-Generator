package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TimeIntervalTest {
    private TimeInterval interval1;
    private TimeInterval interval2;
    private TimeInterval interval3;
    private TimeInterval interval4;

    @BeforeEach
    void setUp() {
        interval1 = new TimeInterval(0, 1200);
        interval2 = new TimeInterval(1200, 2400);
        interval3 = new TimeInterval(0, 1201);
        interval4 = new TimeInterval(2399, 3600);
    }

    @Test
    void testConstructor() {
        TimeInterval interval = new TimeInterval(0, 2400);
        assertEquals(0, interval.getStartTime());
        assertEquals(2400, interval.getEndTime());
    }

    @Test
    void testConstructorEmpty() {
        TimeInterval interval = new TimeInterval();
        assertEquals(0, interval.getStartTime());
        assertEquals(0, interval.getEndTime());
    }

    @Test
    void testIsOverlapping() {
        assertFalse(interval1.isOverlapping(interval2));
        assertTrue(interval2.isOverlapping(interval3));
        assertTrue(interval2.isOverlapping(interval4));
    }

    @Test
    void testGetStartTime() {
        assertEquals(0, interval1.getStartTime());
        assertEquals(1200, interval2.getStartTime());
        assertEquals(0, interval3.getStartTime());
        assertEquals(2399, interval4.getStartTime());
    }

    @Test
    void testSetStartTime() {
        interval1.setStartTime(1200);
        assertEquals(1200, interval1.getStartTime());
    }

    @Test
    void testGetEndTime() {
        assertEquals(1200, interval1.getEndTime());
        assertEquals(2400, interval2.getEndTime());
        assertEquals(1201, interval3.getEndTime());
        assertEquals(3600, interval4.getEndTime());
    }

    @Test
    void testSetEndTime() {
        interval1.setEndTime(4800);
        assertEquals(4800, interval1.getEndTime());
    }
}
