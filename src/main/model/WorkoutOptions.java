package model;

import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

//Represents a list of the possible activities that the generator can select from. Contains fields that
//represent the options split by intensity levels, the final workout list, a list of the final workout list's
//activity names and 3 initial exercises
public class WorkoutOptions implements Writable {
    private String name;
    private List<Activity> options;
    private List<Activity> finalWorkout;
    private List<String> finalWorkoutDesc;

    //EFFECTS: constructs a new list of workout options to choose from and initializes the possible options with 3
    // starting activities to choose from (more added later)
    public WorkoutOptions(String name) {
        this.name = name;
        options = new ArrayList<>();
        finalWorkout = new ArrayList<>();
        finalWorkoutDesc = new ArrayList<>();
    }

    //getters
    public String getName() {
        return name;
    }

    public List<Activity> getOptions() {
        return options;
    }

    public List<Activity> getFinalWorkout() {
        return finalWorkout;
    }

    public List<String> getFinalWorkoutDesc() {
        return finalWorkoutDesc;
    }

    //MODIFIES: this
    //EFFECTS: adds an activity to the list of options, the final workout and also to a list of options
    // categorized by intensity
    public void addActivity(Activity act) {
        options.add(act);
        finalWorkout.add(act);
    }

    //MODIFIES: this
    //EFFECTS: removes an activity from the list of options. If successful, return true
    // otherwise, output false
    public boolean removeActivity(Activity act) {
        for (Activity activity : options) {
            if (options.contains(act)) {
                options.remove(act);
                return true;
            }
        }
        return false;
    }

    //MODIFIES: this
    //EFFECTS: filters out options by intensity
    public void filterIntensity(String intense) {
        final boolean b = finalWorkout.removeIf(act -> !act.getIntensity().equals(intense));
    }


    //MODIFIES: this
    //EFFECTS: filters out options by muscle group
    public void filterMuscGroup(String musc) {
        final boolean b = finalWorkout.removeIf(act -> !act.getMuscGroup().equals(musc));
    }

    //MODIFIES: this
    //EFFECTS: filters out options by equipment requirements
    public void filterNeedsEquipment(boolean bool) {
        final boolean b = finalWorkout.removeIf(act -> !act.getNeedsEquipment() == bool);
    }

    //MODIFIES: this
    //EFFECTS: inputs the descriptions of the final workout into a list
    public void setFinalWorkoutDesc() {
        for (Activity act : finalWorkout) {
            finalWorkoutDesc.add(act.getDescription());
        }
    }
}
