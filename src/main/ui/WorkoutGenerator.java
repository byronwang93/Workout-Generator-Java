package ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import exceptions.InvalidIntensityException;
import model.Activity;
import model.WorkoutOptions;
import persistence.JsonReader;
import persistence.JsonWriter;

// Workout generator application
public class WorkoutGenerator {
    private static final String JSON_STORE = "./data/workoutOptions.json";
    private WorkoutOptions workout;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    //EFFECTS: constructs workout and runs the workout generator
    public WorkoutGenerator() throws FileNotFoundException {
        input = new Scanner(System.in);
        workout = new WorkoutOptions("My workout");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runGenerator();
    }

    //MODIFIES: this
    //EFFECTS: takes in and processes the user's input
    public void runGenerator() {
        boolean keepRunning = true;
        String command = null;

        initialize();

        while (keepRunning) {
            displayOptions();
            command = input.next();
            command = command.toLowerCase();

            processCommand(command);
        }
    }

    //MODIFIES: this
    //EFFECTS: processes the user's input of choice
    private void processCommand(String command) {
        if (command.equals("1")) {
            displayIntensityOptions();
            command = input.next();
            command = command.toLowerCase();
            processIntensityCommand(command);
        } else if (command.equals("2")) {
            addUserExercise();
        } else if (command.equals("3")) {
            removeUserExercise();
        } else if (command.equals("4")) {
            changeIntensity();
        } else if (command.equals("5")) {
            showOptions();
        } else if (command.equals("6")) {
            saveWorkout();
        } else if (command.equals("7")) {
            loadWorkout();
        } else if (command.equals("8")) {
            System.exit(0);
        } else {
            System.out.println("Input not valid.");
        }
    }

    //MODIFIES: this
    //EFFECTS: shows user all the exercise option descriptions in generator
    private void showOptions() {

        List<String> finalWorkoutDesc = new ArrayList<>();

        for (Activity act : workout.getOptions()) {
            finalWorkoutDesc.add(act.getDescription());
        }

        System.out.println("All option descriptions:");

        for (String name : finalWorkoutDesc) {
            System.out.println(name);
        }
    }

    //MODIFIES: this
    //EFFECTS: changes the intensity of the user's desired workout
    private void changeIntensity() {
        String intensity;
        String desc;
        Scanner input2 = new Scanner(System.in);
        System.out.println("Description of activity you want to change");
        String command2 = input2.next();
        desc = command2;
        Scanner input = new Scanner(System.in);
        System.out.println("What intensity do you want to change your exercise option to? (easy, medium, or hard)");
        String command = input.next();
        intensity = command;

        changeIntensityMethod(intensity, desc);
    }

    //MODIFIES: this
    //EFFECTS: changes the intensity of the user's desired workout
    private void changeIntensityMethod(String intensity, String desc) {
        for (Activity act : workout.getFinalWorkout()) {
            if (act.getDescription().equals(desc)) {
                workout.getOptions().remove(act);
                workout.getFinalWorkout().remove(act);
                try {
                    act.changeIntensity(intensity);
                } catch (InvalidIntensityException e) {
                    System.out.println("Invalid intensity entered");
                }
                workout.getOptions().add(act);
                workout.getFinalWorkout().add(act);
                return;
            } else {
                //nothing
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: adds a user's input into the workout options
    private void addUserExercise() {
        String desc;
        String intensity;
        String muscGroup;
        String equip;
        Scanner input = new Scanner(System.in);
        String command = null;
        System.out.println("Put its description here! (ex. 5 x 20 pushups)");
        command = input.nextLine();
        desc = command;
        System.out.println("Intensity of workout! (easy, medium or hard)");
        command = input.next();
        intensity = command;
        System.out.println("Muscle group? (arms, legs, or core)");
        command = input.next();
        muscGroup = command;
        System.out.println("Equipment needed? (true or false)");
        command = input.next();
        equip = command;
        Activity newAct = new Activity(desc, intensity, muscGroup, Boolean.parseBoolean(equip));
        workout.addActivity(newAct);
    }

    //MODIFIES: this
    //EFFECTS: adds a user's input into the workout options
    private void removeUserExercise() {
        String desc;
        Scanner input = new Scanner(System.in);
        String command = null;
        System.out.println("Description of workout you want removed.");
        command = input.nextLine();
        desc = command;

        final boolean b = workout.getFinalWorkout().removeIf(act -> act.getDescription().equals(desc));
        final boolean bb = workout.getOptions().removeIf(act -> act.getDescription().equals(desc));
    }

    //MODIFIES: this
    //EFFECTS: processes the user's input for intensity
    private void processIntensityCommand(String command) {
        if (command.equals("easy")) {
            workout.filterIntensity("easy");
            processMuscGroupAndEquipment();
        } else if (command.equals("medium")) {
            workout.filterIntensity("medium");
            processMuscGroupAndEquipment();
        } else if (command.equals("hard")) {
            workout.filterIntensity("hard");
            processMuscGroupAndEquipment();
        } else {
            System.out.println("Intensity is not valid.");
        }
    }

    //MODIFIES: this
    //EFFECTS: initializes workout options
    private void initialize() {
        workout = new WorkoutOptions("My workout");
        input = new Scanner(System.in);
    }

    //EFFECTS: displays a main menu that allows user to add, remove or generate a workout
    private void displayOptions() {
        System.out.println("Welcome! Choose an option below!");
        System.out.println("- Generate Workout (enter 1)");
        System.out.println("- Add an activity (enter 2)");
        System.out.println("- Remove an activity (enter 3)");
        System.out.println("- Change the intensity of an existing option (enter 4)");
        System.out.println("- View the possible workout options (enter 5)");
        System.out.println("- Save current workout options to file (enter 6)");
        System.out.println("- Load workout options from file (enter 7)");
        System.out.println("- Quit (enter 8)");
    }

    //EFFECTS: displays a menu of intensities to the user
    private void displayIntensityOptions() {
        System.out.println("Select intensity level:");
        System.out.println("easy");
        System.out.println("medium");
        System.out.println("hard");
    }

    //MODIFIES: this
    //EFFECTS: processes user's choice of muscle group and gym equipment
    private void processMuscGroupAndEquipment() {
        WorkoutOptions choices = selectMuscGroup();
        System.out.println("Do you have access to gym equipment?");
        System.out.println("enter true if yes");
        System.out.println("enter false if no");
        boolean bool = input.nextBoolean();

        if (bool == true || bool == false) {
            choices.filterNeedsEquipment(bool);
            printWorkout(choices);
        } else {
            System.out.println("Value entered was not true or false.");
        }
    }

    //EFFECTS: prompts the user to select the muscle group
    private WorkoutOptions selectMuscGroup() {
        String selection = "";

        System.out.println("a for an arm workout");
        System.out.println("l for a legs workout");
        System.out.println("c for a core workout");
        selection = input.next();
        selection = selection.toLowerCase();

        if (selection.equals("a")) {
            workout.filterMuscGroup("arms");
            return workout;
        } else if (selection.equals("l")) {
            workout.filterMuscGroup("legs");
            return workout;
        } else {
            workout.filterMuscGroup("core");
            return workout;
        }
    }

    //EFFECTS: prints the final generated workout
    private void printWorkout(WorkoutOptions finalWorkout) {
        System.out.println("Your workout:");

        finalWorkout.setFinalWorkoutDesc();

        for (String name : finalWorkout.getFinalWorkoutDesc()) {
            System.out.println(name);
        }

        System.out.println("\nEnjoy your workout!");
        System.exit(0);
    }

    //EFFECTS: saves the workout to file
    private void saveWorkout() {
        try {
            jsonWriter.open();
            jsonWriter.write(workout);
            jsonWriter.close();
            System.out.println("Saved " + workout.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    //MODIFIES: this
    //EFFECTS: loads workout from file
    private void loadWorkout() {
        try {
            workout = jsonReader.read();
            System.out.println("Loaded " + workout.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
