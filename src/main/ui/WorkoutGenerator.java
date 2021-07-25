package ui;

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
    //EFFECTS: processes the user's input
    private void processCommand(String command) {
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

    //EFFECTS: displays a menu of intensities to the user
    private void displayOptions() {
        System.out.println("\nSelect intensity level:");
        System.out.println("\neasy");
        System.out.println("\nmedium");
        System.out.println("\nhard");
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
