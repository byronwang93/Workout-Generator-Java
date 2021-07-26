package ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import model.Activity;
import model.WorkoutOptions;

// Workout generator application
public class WorkoutGenerator {
    private WorkoutOptions workout;
    private Scanner input;

    //EFFECTS: runs the workout generator
    public WorkoutGenerator() {
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
        String command2 = null;
        System.out.println("Description of activity you want to change");
        command2 = input2.next();
        desc = command2;
        Scanner input = new Scanner(System.in);
        String command = null;
        System.out.println("What intensity do you want to change your exercise option to? (easy, medium, or hard)");
        command = input.next();
        intensity = command;

        for (Activity act : workout.getFinalWorkout()) {
            if (act.getDescription().equals(desc)) {
                act.changeIntensity(intensity);
            } else {
                System.out.printf("That exercise doesn't exist");
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
        workout = new WorkoutOptions();
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
        System.out.println("- Quit (enter 6)");
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


}
