package persistence;

import model.Activity;
import model.WorkoutOptions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            WorkoutOptions wo = reader.read();
            fail("Expected IOException was not thrown");
        } catch (IOException e) {
            //good
        }
    }

    @Test
    void testReaderEmptyWorkout() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyWorkoutOptions.json");
        try {
            WorkoutOptions wo = reader.read();
            assertEquals("My workout", wo.getName());
            List<Activity> options = wo.getOptions();
            List<Activity> finalWorkout = wo.getFinalWorkout();
            List<String> finalWorkoutDesc = wo.getFinalWorkoutDesc();
            assertEquals(0, options.size());
            assertEquals(0, finalWorkout.size());
            assertEquals(0, finalWorkoutDesc.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkout() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralWorkoutOptions.json");
        try {
            WorkoutOptions wo = reader.read();
            assertEquals("My workout", wo.getName());
            List<Activity> options = wo.getOptions();
            List<Activity> finalWorkout = wo.getFinalWorkout();
            List<String> finalWorkoutDesc = wo.getFinalWorkoutDesc();
            assertEquals(2, options.size());
            assertEquals(2, finalWorkout.size());
            assertEquals(0, finalWorkoutDesc.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
