package persistence;

import model.Activity;
import model.WorkoutOptions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            WorkoutOptions wo = new WorkoutOptions("My workout");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            //good
        }
    }

    @Test
    void testWriterEmptyWorkout() {
        try {
            WorkoutOptions wo = new WorkoutOptions("My workout");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkoutOptions.json");
            writer.open();
            writer.write(wo);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkoutOptions.json");
            wo = reader.read();
            assertEquals("My workout", wo.getName());
            List<Activity> options = wo.getOptions();
            List<Activity> finalWorkout = wo.getFinalWorkout();
            List<String> finalWorkoutDesc = wo.getFinalWorkoutDesc();
            assertEquals(0, options.size());
            assertEquals(0, finalWorkout.size());
            assertEquals(0, finalWorkoutDesc.size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkout() {
        try {
            WorkoutOptions wo = new WorkoutOptions("My workout");
            wo.addActivity(new Activity("plank", "hard", "arms", false));
            wo.addActivity(new Activity("squats", "medium", "legs", false));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkoutOptions.json");
            writer.open();
            writer.write(wo);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkoutOptions.json");
            wo = reader.read();
            assertEquals("My workout", wo.getName());
            List<Activity> options = wo.getOptions();
            List<Activity> finalWorkout = wo.getFinalWorkout();
            List<String> finalWorkoutDesc = wo.getFinalWorkoutDesc();
            assertEquals(2, options.size());
            assertEquals(2, finalWorkout.size());
            assertEquals(0, finalWorkoutDesc.size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
