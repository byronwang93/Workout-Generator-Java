package model;

import java.util.ArrayList;
import java.util.List;

public class WorkoutOptions {
    private List<Activity> options;
    private List<Activity> easyOptions;
    private List<Activity> mediumOptions;
    private List<Activity> hardOptions;
    private List<Activity> finalWorkout;
    private List<String> finalWorkoutDesc;
    private Activity act1;
    private Activity act2;
    private Activity act3;

    //EFFECTS: constructs a new list of workout options to choose from
    public WorkoutOptions() {
        options = new ArrayList<>();
        easyOptions = new ArrayList<>();
        mediumOptions = new ArrayList<>();
        hardOptions = new ArrayList<>();
        finalWorkout = new ArrayList<>();
        finalWorkoutDesc = new ArrayList<>();
        act1 = new Activity("pushups", "hard", "arms", false);
        act2 = new Activity("squats", "medium", "legs", false);
        act3 = new Activity("plank", "hard", "arms", false);
        addActivity(act1);
        addActivity(act2);
        addActivity(act3);

    }

    //getters
    public List<Activity> getOptions() {
        return options;
    }

    public List<Activity> getEasyOptions() {
        return easyOptions;
    }

    public List<Activity> getMediumOptions() {
        return mediumOptions;
    }

    public List<Activity> getHardOptions() {
        return hardOptions;
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
//        finalWorkoutDesc.add(act.getDescription());
        if (act.getIntensity().equals("easy")) {
            easyOptions.add(act);
        }
        if (act.getIntensity().equals("medium")) {
            mediumOptions.add(act);
        }
        if (act.getIntensity().equals("hard")) {
            hardOptions.add(act);
        }
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
