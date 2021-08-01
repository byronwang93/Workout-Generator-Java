package persistence;

import model.Activity;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkActivity(String description, String intensity, String muscGroup, boolean needsEquipment,
                                 Activity activity) {
        assertEquals(description, activity.getDescription());
        assertEquals(intensity, activity.getIntensity());
        assertEquals(muscGroup, activity.getMuscGroup());
        assertEquals(needsEquipment, activity.getNeedsEquipment());
    }
}
