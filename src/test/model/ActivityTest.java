package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActivityTest {
    private Activity act1;
    private Activity act2;
    private Activity act3;
    private Activity act4;

    @BeforeEach
    void runBefore() {
        act1 = new Activity("Push-ups", "medium", "arms", false);
        act2 = new Activity("Squats", "hard", "legs", false);
        act3 = new Activity("Plank", "easy", "core", false);
        act4 = new Activity("Bench-press", "medium", "arms", true);
    }

    @Test
    void testChangeActivityDescription() {
        act1.changeName("Up and downs");
        assertEquals(act1.getDescription(), "Up and downs");
    }

    @Test
    void testChangeActivityIntensity() {
        act2.changeIntensity("Easy");
        assertEquals("Easy", act2.getIntensity());
    }

    @Test
    void testChangeActivityEquipmentNeeded() {
        act3.changeNeedsEquipment(false);
        assertFalse(act3.getNeedsEquipment());
        act4.changeNeedsEquipment(false);
        assertFalse(act4.getNeedsEquipment());
    }

}