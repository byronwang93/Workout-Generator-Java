package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import exceptions.InvalidRemovalException;

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
        assertEquals(0, options.getOptions().size());
        options.addActivity(act1);
        assertEquals(1, options.getOptions().size());
    }

    //Added exception handling for phase 4 task 2
    @Test
    void testRemoveActivityThatExists() {
        options.addActivity(act3);
        options.addActivity(act4);
        try {
            options.removeActivity(act4);
            //all good!
        } catch (InvalidRemovalException e) {
            fail("Unexpected exception was thrown");
        }
        assertEquals(options.getOptions().size(), 1);
    }

    //Added exception handling for phase 4 task 2
    @Test
    void testRemoveActivityThatDoesNotExist() {
        options.addActivity(act3);
        options.addActivity(act4);

        try {
            options.removeActivity(act1);
            fail("Expected exception was not thrown.");
        } catch (InvalidRemovalException e) {
            //all good!
        }
        assertEquals(options.getOptions().size(), 2);
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
        assertEquals(options.getFinalWorkout().size(), 2);
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
        options.addActivity(act2);
        options.setFinalWorkoutDesc();
        assertFalse(options.getFinalWorkoutIntensity().isEmpty());
        assertTrue(options.getFinalWorkoutDesc().contains("Squats"));
    }

}