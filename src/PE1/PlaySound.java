package PE1;

import java.io.File;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

import javax.swing.Timer;
import java.awt.event.*;

public class PlaySound implements ActionListener {
    Clip clip;
    int tim;
    int lim = 0;
    Timer timer;

    public void actionPerformed(ActionEvent e) {
        tim++;
        if (tim == lim) {
            clip.start();
            timer.stop();
        }
    }

    public void playEffect(String s, int delay) {
        try {
            File yourFile = new File(s);
            AudioInputStream stream;
            AudioFormat format;
            DataLine.Info info;

            stream = AudioSystem.getAudioInputStream(yourFile);
            format = stream.getFormat();
            info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(stream);

            lim = delay;
            tim = 0;
            timer = new Timer(1000, this);
            timer.start();

        } catch (Exception err) {
            System.out.println(err.toString());
        }
    }

    public void playEffect(String s) {
        try {
            File yourFile = new File(s);
            AudioInputStream stream;
            AudioFormat format;
            DataLine.Info info;

            stream = AudioSystem.getAudioInputStream(yourFile);
            format = stream.getFormat();
            info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(stream);
            clip.start();

        } catch (Exception err) {
            System.out.println(err.toString());
        }
    }
    
    public void playBackgroundMusic(String s) {
        try {
            File yourFile = new File(s);
            AudioInputStream stream;
            AudioFormat format;
            DataLine.Info info;

            stream = AudioSystem.getAudioInputStream(yourFile);
            format = stream.getFormat();
            info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(stream);
            clip.loop(Clip.LOOP_CONTINUOUSLY); // Loop the background music continuously

        } catch (Exception err) {
            System.out.println(err.toString());
        }
    }
}