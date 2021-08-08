package gui.buttons;

import javax.swing.*;

//Action listener for the quit button on the main menu
public class QuitButton extends JButton {

    //EFFECTS: constructs a new QuitButton button
    public QuitButton(String desc) {
        super(desc);
        this.setBounds(100,350,80,30);
    }
}
