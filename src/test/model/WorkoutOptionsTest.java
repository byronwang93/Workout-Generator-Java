package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WorkoutOptionsTest {
    private WorkoutOptions options;
    private Activity act1;
    private Activity act2;
    private Activity act3;
    private Activity act4;

    @BeforeEach
    void runBefore() {
        options = new WorkoutOptions("My workout");
        act1 = new Activity("Push-ups", "medium", "arms", false);
        act2 = new Activity("Squats", "hard", "legs", false);
        act3 = new Activity("Plank", "easy", "core", false);
        act4 = new Activity("Bench-press", "medium", "arms", true);
    }

    @Test
    void testWorkoutOptionsName() {
        assertEquals("My workout", options.getName());
    }

    @Test
    void testAddActivity() {
        assertEquals(3, options.getOptions().size());
        options.addActivity(act1);
        assertEquals(4, options.getOptions().size());
    }

    @Test
    void testRemoveActivityThatExists() {
        options.addActivity(act3);
        options.addActivity(act4);
        assertTrue(options.removeActivity(act4));
        assertEquals(options.getOptions().size(), 4);
    }

    @Test
    void testRemoveActivityThatDoesNotExist() {
        options.addActivity(act3);
        options.addActivity(act4);
        assertFalse(options.removeActivity(act1));
        assertEquals(options.getOptions().size(), 5);
    }

    @Test
    void testFilterOptionsByIntensity() {
        options.addActivity(act1);
        options.addActivity(act2);
        options.addActivity(act3);
        options.addActivity(act4);
        options.filterIntensity("easy");
        assertEquals(options.getFinalWorkout().size(), 1);
        assertTrue(options.getFinalWorkout().contains(act3));
    }

    @Test
    void testFilterOptionsByMuscGroup() {
        options.addActivity(act1);
        options.addActivity(act2);
        options.addActivity(act3);
        options.addActivity(act4);
        options.filterMuscGroup("arms");
        assertEquals(options.getFinalWorkout().size(), 4);
        assertTrue(options.getFinalWorkout().contains(act1));
        assertTrue(options.getFinalWorkout().contains(act4));
    }

    @Test
    void testFilterNeedsEquipment() {
        options.addActivity(act1);
        options.addActivity(act2);
        options.addActivity(act3);
        options.addActivity(act4);
        options.filterNeedsEquipment(true);
        assertEquals(options.getFinalWorkout().size(), 1);
        assertTrue(options.getFinalWorkout().contains(act4));
    }

    @Test
    void testGettingFinalWorkoutNames() {
        options.setFinalWorkoutDesc();
        assertTrue(options.getFinalWorkoutDesc().contains("squats"));
    }

}