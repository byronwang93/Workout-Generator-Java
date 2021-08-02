package persistence;

import com.sun.corba.se.spi.orbutil.threadpool.Work;
import model.Activity;
import model.WorkoutOptions;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads workoutOptions from JSON data stored in file
// Template cited from the JsonSerializationDemo project
public class JsonReader {
    private String source;

    //EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    //EFFECTS: reads workoutOptions from file and returns it;
    //throws IOException if an error occurs when reading data from the file
    public WorkoutOptions read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseWorkoutOptions(jsonObject);
    }

    //EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source),StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    //EFFECTS: parses workroom from JSON object and returns it
    private WorkoutOptions parseWorkoutOptions(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        WorkoutOptions wo = new WorkoutOptions(name);
        addOptions(wo, jsonObject);
//        addFinalWorkout(wo, jsonObject);
//        addFinalWorkoutDesc(wo, jsonObject);
        return wo;
    }

    //MODIFIES: wo
    //EFFECTS: parses options from JSON object and adds it to workoutOptions
    private void addOptions(WorkoutOptions wo, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("options");
        for (Object json : jsonArray) {
            JSONObject nextOption = (JSONObject) json;
            addOption(wo, nextOption);
        }
    }

//    //MODIFIES: wo
//    //EFFECTS: parses options from JSON object and adds it to workoutOptions
//    private void addFinalWorkout(WorkoutOptions wo, JSONObject jsonObject) {
//        JSONArray jsonArray = jsonObject.getJSONArray("finalWorkout");
//        for (Object json : jsonArray) {
//            JSONObject nextOption = (JSONObject) json;
//            addOption(wo, nextOption);
//        }
//    }

//    //MODIFIES: wo
//    //EFFECTS: parses options from JSON object and adds it to workoutOptions
//    private void addFinalWorkoutDesc(WorkoutOptions wo, JSONObject jsonObject) {
//        JSONArray jsonArray = jsonObject.getJSONArray("finalWorkoutDesc");
//        for (Object json : jsonArray) {
//            JSONObject nextOption = (JSONObject) json;
//            addOptionDesc(wo, nextOption);
//        }
//    }

    //MODIFIES: wo
    //EFFECTS: parses option from JSON object and adds it to workoutOptions
    private void addOption(WorkoutOptions wo, JSONObject jsonObject) {
        String description = jsonObject.getString("description");
        String intensity = jsonObject.getString("intensity");
        String muscGroup = jsonObject.getString("muscGroup");
        Boolean needsEquipment = jsonObject.getBoolean("needsEquipment");
        Activity activity = new Activity(description, intensity, muscGroup, needsEquipment);
        wo.addActivity(activity);
    }

//    //MODIFIES: wo
//    //EFFECTS: parses option from JSON object and adds it to workoutOptions
//    private void addOptionDesc(WorkoutOptions wo, JSONObject jsonObject) {
//        String description = jsonObject.getString("description");
//        String intensity = jsonObject.getString("intensity");
//        String muscGroup = jsonObject.getString("muscGroup");
//        Boolean needsEquipment = jsonObject.getBoolean("needsEquipment");
//        Activity activity = new Activity(description, intensity, muscGroup, needsEquipment);
//        wo.getFinalWorkoutDesc().add(activity.getDescription());
//    }
}
