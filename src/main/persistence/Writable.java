package persistence;

import org.json.JSONObject;

// Template cited from the JsonSerializationDemo project
public interface Writable {
    //EFFECTS: returns this as JSON object
    JSONObject toJson();
}
