package gui;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;
import java.io.File;

// constructs a sound class that plays sounds
public class PlaySound {

    public PlaySound(String soundFile) {
        playSound(soundFile);
    }

    public void playSound(String sound) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(sound).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            System.out.println("Error encountered when playing the sound");
            e.printStackTrace();
        }
    }
}
