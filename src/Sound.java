import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
    Clip clip;
    URL soundURL[] = new URL[1];
    
    public Sound() {
        soundURL[0] = getClass().getResource("ClockApp\\Windows Error - (Sound effect).wav");
    }

    //Sets audio file to be played
    public void setFile(int i) {
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
        }
        catch (Exception e) {
            System.out.println("Error");
        }
    }

    //Plays audio
    public void play() {
        clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }
}
