package gui;

import gui.buttons.*;
import model.Activity;
import model.WorkoutOptions;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

// Workout generator GUI
public class GeneratorGUI extends JFrame implements ActionListener {
    private WorkoutOptions workoutOptions;
    private static final String JSON_STORE = "./data/workoutOptions.json";
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;
    private AddActivityButton addButton;
    private RemoveActivityButton removeButton;
    private ViewOptionsButton viewOptionsButton;
    private SaveButton saveButton;
    private LoadButton loadButton;
    private QuitButton quitButton;
    private JFrame jframe;
    private JLabel optionsLabel;
    private JPanel mainPanel;
    private JPanel addPanel;
    private JPanel removePanel;
    private JPanel showOptionsPanel;
    private JPanel switchIntensityPanel;

    private JLabel newDescLabel;
    private JTextField newDescTextField;
    private String desc;
    private JLabel newIntensityLabel;
    private JTextField intensityTextField;
    private String intensity;
    private JLabel newPartLabel;
    private JTextField partTextField;
    private String part;
    private JLabel newEquipLabel;
    private JTextField equipTextField;
    private String equipment;
    private JButton submitAddButton;
    private JLabel addLabel;

    private JLabel removeLabel;
    private JTextField textField;
    private JButton submitRemovalButton;
    private String actToRemove;

    private JButton switchIntensityButton;
    private JButton saveSwitchingOptionsButton;
    private JLabel changedIntensityLabel;
    private JTextField newIntensityTextField;
    private String newIntensity;
    private JLabel targetDescLabel;
    private JTextField targetDescTextField;
    private String targetDesc;

    //EFFECTS: initiates the workout generator GUI.
    public GeneratorGUI() {
        instantiateButtons();
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);
        jframe = new JFrame("Workout Generator Main Menu");
        mainPanelSetup();

        addPanel = new JPanel();
        addPanel.setBounds(0,250,300,350);
        addPanel.setBackground(Color.BLUE);

        addPanelLabelsAndTextFields();

        addPanelSetup();

        removePanelSetup();

        showOptionsPanelSetup();

        switchIntensityPanelSetup();

        addButtons(mainPanel);

        showOptionsPanel.add(optionsLabel);

        jframeSetup();

    }

    // MODIFIES: this
    // EFFECTS: adds panel labels, text fields, and buttons to the addPanel
    private void addPanelLabelsAndTextFields() {
        newDescLabel = new JLabel("Description");
        newDescTextField = new JTextField();
        newDescTextField.setPreferredSize(new Dimension(250,40));

        newIntensityLabel = new JLabel("Intensity (easy, medium, hard)");
        intensityTextField = new JTextField();
        intensityTextField.setPreferredSize(new Dimension(250,40));

        newPartLabel = new JLabel("Muscle group (arms, legs, core");
        partTextField = new JTextField();
        partTextField.setPreferredSize(new Dimension(250,40));

        newEquipLabel = new JLabel("Need equipment? (true or false)");
        equipTextField = new JTextField();
        equipTextField.setPreferredSize(new Dimension(250,40));
    }

    // MODIFIES: this
    // EFFECTS: adds panel labels, text fields and buttons to the removePanel
    private void removePanelSetup() {
        removePanel = new JPanel();
        removePanel.setBounds(300,250,300,250);
        removePanel.setBackground(Color.YELLOW);
        textField = new JTextField();
        textField.setPreferredSize(new Dimension(250,40));
        submitRemovalButton = new JButton("Submit");
        submitRemovalButton.addActionListener(this);
        removeLabel = new JLabel("Description of activity you want removed");
        removePanel.add(removeLabel);
        removePanel.add(submitRemovalButton);
        removePanel.add(textField);
    }

    // MODIFIES: this
    // EFFECTS: adds panel labels, text fields and buttons to the switchIntensityPanel
    private void switchIntensityPanelSetup() {
        switchIntensityPanel = new JPanel();
        switchIntensityPanel.setBounds(300,500,300,250);
        switchIntensityPanel.setBackground(Color.GRAY);
        switchIntensityButton = new JButton("Switch Intensity");
        switchIntensityButton.addActionListener(this);
        saveSwitchingOptionsButton = new JButton("Save settings");
        saveSwitchingOptionsButton.addActionListener(this);
        targetDescLabel = new JLabel("Description of the target activity");
        targetDescTextField = new JTextField();
        targetDescTextField.setPreferredSize(new Dimension(250,40));
        changedIntensityLabel = new JLabel("Intensity you want to change it to");
        newIntensityTextField = new JTextField();
        newIntensityTextField.setPreferredSize(new Dimension(250, 40));
        switchIntensityPanel.add(switchIntensityButton);
        switchIntensityPanel.add(saveSwitchingOptionsButton);
        switchIntensityPanel.add(targetDescLabel);
        switchIntensityPanel.add(targetDescTextField);
        switchIntensityPanel.add(changedIntensityLabel);
        switchIntensityPanel.add(newIntensityTextField);
    }

    // MODIFIES: this
    // EFFECTS: sets up the jframe's size and visibility and adds the previously made panels
    private void jframeSetup() {
        jframe.add(mainPanel);
        jframe.add(addPanel);
        jframe.add(removePanel);
        jframe.add(showOptionsPanel);
        jframe.add(switchIntensityPanel);
        jframe.setSize(700,900);
        jframe.setLayout(null);
        jframe.setVisible(true);
        jframe.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    // MODIFIES: this
    // EFFECTS: adds panel labels, text fields and buttons to the showOptionsPanel
    private void showOptionsPanelSetup() {
        showOptionsPanel = new JPanel();
        showOptionsPanel.setBounds(300,0,300,250);
        showOptionsPanel.setBackground(Color.GREEN);
        optionsLabel = new JLabel("These are your possible options: ");
    }

    // MODIFIES: this
    // EFFECTS: sets up the mainPanel's colour and dimensions
    private void mainPanelSetup() {
        mainPanel = new JPanel();
        mainPanel.setBounds(0,0,300,250);
        mainPanel.setBackground(Color.RED);
    }

    // MODIFIES: this
    // EFFECTS: adds the previously made panel labels, text fields and buttons to the addPanel
    private void addPanelSetup() {
        submitAddButton = new JButton("Submit");
        submitAddButton.addActionListener(this);
        addLabel = new JLabel("Description of activity you want to add");
        addPanel.add(addLabel);
        addPanel.add(submitAddButton);
        addPanel.add(newDescLabel);
        addPanel.add(newDescTextField);
        addPanel.add(newIntensityLabel);
        addPanel.add(intensityTextField);
        addPanel.add(newPartLabel);
        addPanel.add(partTextField);
        addPanel.add(newEquipLabel);
        addPanel.add(equipTextField);
    }

    // EFFECTS: constructs the mainPanel buttons and adds their action listener function
    private void instantiateButtons() {
        workoutOptions = new WorkoutOptions("My Workout");
        addButton = new AddActivityButton("Add a workout option");
        addButton.addActionListener(this);
        removeButton = new RemoveActivityButton("Remove a workout option");
        removeButton.addActionListener(this);
        saveButton = new SaveButton("Save the current workout options");
        saveButton.addActionListener(this);
        loadButton = new LoadButton("Load previously saved workout options");
        loadButton.addActionListener(this);
        viewOptionsButton = new ViewOptionsButton("View the workout options");
        viewOptionsButton.addActionListener(this);
        quitButton = new QuitButton("Quit (save or your options will be lost!)");
        quitButton.addActionListener(this);
    }

    // MODIFIES: this
    // EFFECTS: adds the buttons to the main menu frame
    private void addButtons(JPanel mainPanel) {
        mainPanel.add(addButton);
        mainPanel.add(removeButton);
        mainPanel.add(viewOptionsButton);
        mainPanel.add(saveButton);
        mainPanel.add(loadButton);
        mainPanel.add(quitButton);
    }

    // EFFECTS: runs the program
    public static void main(String[] args) {
        new GeneratorGUI();
    }

    //EFFECTS: carries out the appropriate action depending on the button pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton | e.getSource() == submitAddButton | e.getSource() == submitRemovalButton
                | e.getSource() == removeButton) {
            addAndRemoveActions(e);
        }
        if (e.getSource() == saveButton | e.getSource() == loadButton) {
            saveAndLoadButtonActions(e);
        }
        if (e.getSource() == viewOptionsButton) {
            viewingOptionsMethod();
        }
        if (e.getSource() == quitButton) {
            System.exit(0);
        }
        if (e.getSource() == saveSwitchingOptionsButton) {
            newIntensity = newIntensityTextField.getText();
            targetDesc = targetDescTextField.getText();
        }
        if (e.getSource() == switchIntensityButton) {
            switchIntensityMethod();
        }
    }

    // MODIFIES: this
    // EFFECTS: switches the intensity of a chosen activity
    private void switchIntensityMethod() {
        for (Activity act : workoutOptions.getFinalWorkout()) {
            if (act.getDescription().equals(targetDesc)) {
                workoutOptions.getOptions().remove(act);
                workoutOptions.getFinalWorkout().remove(act);
                act.changeIntensity(newIntensity);
                workoutOptions.getOptions().add(act);
                workoutOptions.getFinalWorkout().add(act);
                return;
            } else {
                //nothing
            }
        }
    }

    // EFFECTS: chooses the appropriate action depending on the button that's pressed
    private void addAndRemoveActions(ActionEvent e) {
        if (e.getSource() == addButton) {
            addActivityMethod();
        }
        if (e.getSource() == submitAddButton) {
            newActivityDetails();
        }
        if (e.getSource() == submitRemovalButton) {
            removeActivityDetails();
        }
        if (e.getSource() == removeButton) {
            removeActivityMethod();
        }
    }

    // EFFECTS: chooses the appropriate action of either loading or saving
    private void saveAndLoadButtonActions(ActionEvent e) {
        if (e.getSource() == saveButton) {
            saveMethod();
        }
        if (e.getSource() == loadButton) {
            loadMethod();
        }
    }

    // MODIFIES: this
    // EFFECTS: sets the actToRemove field as the text from the remove activity text box
    private void removeActivityDetails() {
        actToRemove = textField.getText();
        System.out.println("test");
    }

    // MODIFIES: this
    // EFFECTS: stores the values from the new activity text boxes
    private void newActivityDetails() {
        desc = newDescTextField.getText();
        intensity = intensityTextField.getText();
        part = partTextField.getText();
        equipment = equipTextField.getText();
    }

    // MODIFIES: this
    // EFFECTS: removes a given activity from the list of workout options
    private void removeActivityMethod() {
        final boolean b = workoutOptions.getFinalWorkout().removeIf(act -> act.getDescription().equals(actToRemove));
        final boolean bb = workoutOptions.getOptions().removeIf(act -> act.getDescription().equals(actToRemove));
        JOptionPane.showMessageDialog(null, "Removed activity!");
    }

    // MODIFIES: this
    // EFFECTS: adds a new activity given the user's inputs
    private void addActivityMethod() {
        Activity newAct = new Activity(desc, intensity, part, Boolean.parseBoolean(equipment));
        workoutOptions.addActivity(newAct);
        new PlaySound("cheering.wav");
        JOptionPane.showMessageDialog(null, "Added activity!");
    }

    //EFFECTS: saves the workout to file
    private void saveMethod() {
        try {
            jsonWriter.open();
            jsonWriter.write(workoutOptions);
            jsonWriter.close();
            JOptionPane.showMessageDialog(null, "Saved " + workoutOptions.getName() + " to " + JSON_STORE);
            System.out.println("Saved " + workoutOptions.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException exception) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    //MODIFIES: this
    //EFFECTS: loads workout from file
    private void loadMethod() {
        jsonReader = new JsonReader(JSON_STORE);
        try {
            workoutOptions = jsonReader.read();
            new PlaySound("fanfare_x.wav");
            JOptionPane.showMessageDialog(null, "Loaded " + workoutOptions.getName() + " from " + JSON_STORE);
            System.out.println("Loaded " + workoutOptions.getName() + " from " + JSON_STORE);
        } catch (IOException exception) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    //MODIFIES: this
    //EFFECTS: shows the user all the possible workout options
    private void viewingOptionsMethod() {
        int counter = 0;
        showOptionsPanel.removeAll();
        showOptionsPanel.add(optionsLabel);
        List<String> finalWorkoutDesc = workoutOptions.getFinalWorkoutDesc();
        List<String> finalWorkoutIntensity = workoutOptions.getFinalWorkoutIntensity();

        for (Activity act : workoutOptions.getOptions()) {
            finalWorkoutDesc.add(act.getDescription());
            finalWorkoutIntensity.add(act.getIntensity());
        }

        System.out.println("All option descriptions:");

        for (String name : finalWorkoutDesc) {
            JLabel descLabel = new JLabel(name + "(" + finalWorkoutIntensity.get(counter) + "), ");
            showOptionsPanel.add(descLabel);
            showOptionsPanel.revalidate();
            showOptionsPanel.repaint();
            System.out.println(name);
            counter++;
        }

        finalWorkoutDesc.clear();

    }

}
