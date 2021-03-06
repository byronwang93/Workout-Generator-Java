package model;

import org.json.JSONObject;
import persistence.Writable;
import exceptions.InvalidIntensityException;

// Represents an activity with a description, an intensity level, a muscle group
// and whether or not it needs equipment
public class Activity implements Writable {
    private String description;
    private String intensity;
    private String muscGroup;
    private boolean needsEquipment;

    //EFFECTS: constructs a new activity with a name, intensity, muscle group, and if it needs equipment
    public Activity(String name, String intensity, String muscGroup, boolean needsEquipment) {
        this.description = name;
        this.intensity = intensity;
        this.muscGroup = muscGroup;
        this.needsEquipment = needsEquipment;
    }

    //getters
    public String getDescription() {
        return this.description;
    }

    public String getIntensity() {
        return this.intensity;
    }

    public String getMuscGroup() {
        return this.muscGroup;
    }

    public boolean getNeedsEquipment() {
        return this.needsEquipment;
    }

    //REQUIRES: input is a non-empty string
    //MODIFIES: this
    //EFFECTS: changes the name of this activity
    public void changeName(String description) {
        this.description = description;
    }

    //REQUIRES: input either "hard", "medium", or "easy"
    //MODIFIES: this
    //EFFECTS: changes the intensity of this activity
    public void changeIntensity(String intense) throws InvalidIntensityException {
        if (intense.equals("hard") | intense.equals("medium") | intense.equals("easy")) {
            this.intensity = intense;
        } else {
            throw new InvalidIntensityException();
        }
    }

    //MODIFIES: this
    //EFFECTS: changes whether or not this activity needs equipment
    public void changeNeedsEquipment(boolean equip) {
        this.needsEquipment = equip;
    }

    //EFFECTS: returns this as JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("description", description);
        json.put("intensity", intensity);
        json.put("muscGroup", muscGroup);
        json.put("needsEquipment", needsEquipment);
        return json;
    }
}
