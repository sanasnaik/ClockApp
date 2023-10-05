import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class TimerProgram implements ActionListener {
    JFrame frame = new JFrame();
    Sound sound = new Sound();

    JButton startButton = new JButton("start");
    //when pressed, timer starts countdown. 
    //only available if time is not 0.

    JButton setButton = new JButton("set");
    //when pressed, opens a dialog box where you need to
    //type the number of minutes, hours, and seconds
    //and it will start from that time

    JLabel timeLabel = new JLabel();
    int remainingTime = 0;
    int seconds = 0;
    int minutes = 0;
    int hours = 0;
    boolean started = false;
    String secString;
    String minString;
    String hrString;

    //every 1 second, do this
    Timer timer = new Timer(1000, new ActionListener() { 
        public void actionPerformed(ActionEvent e) {
            remainingTime -= 1000; //updates time
            if (remainingTime <= 0) {
                timeEnded();
            }

            hours = (remainingTime/3600000); //updates hours 
            minutes = (remainingTime/60000) % 60; //updates minutes
            seconds = (remainingTime/1000) % 60;

            secString = String.format("%02d", seconds);
            minString = String.format("%02d", minutes);
            hrString = String.format("%02d", hours);
            
            timeLabel.setText(hrString + ":" + minString + ":" + secString);
        }
    });

    TimerProgram() {
        timeLabel.setText("00:00:00");
        timeLabel.setBounds(200, 100, 200, 100);
        timeLabel.setFont(new Font("Courier",Font.PLAIN, 35));
        timeLabel.setBorder(BorderFactory.createBevelBorder(1));
        timeLabel.setOpaque(true);
        timeLabel.setHorizontalAlignment(JTextField.CENTER);

        startButton.setBounds(200,200,100,50);
        startButton.setFont(new Font("Courier", Font.PLAIN, 20));
        startButton.setFocusable(false);
        startButton.addActionListener(this);
        startButton.setEnabled(false);

        setButton.setBounds(300,200,100,50);
        setButton.setFont(new Font("Courier", Font.PLAIN, 20));
        setButton.setFocusable(false);
        setButton.addActionListener(this);

        frame.add(startButton);
        frame.add(setButton);
        frame.add(timeLabel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,400);
        frame.setTitle("Timer");
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public void start() {
        timer.start();
    }

    public void stop() {
        timer.stop();
    }

    //if the timer reaches 0, do this
    public void timeEnded() {
        started = false;
        startButton.setText("start");
                
        remainingTime = 0;
        hours = 0;
        minutes = 0;
        seconds = 0;

        timeLabel.setText("00:00:00");

        stop();
        sound.setFile(0);
        sound.play();
        setButton.setEnabled(true);
    }

    //Sets the stopwatch to the user input.
    public void set() {
        int hrInput = 0;
        int minInput = 0;
        int secInput = 0;

        try {
            hrInput = Integer.parseInt(JOptionPane.showInputDialog("Enter the hours you want to add (integer value)."));
            minInput = Integer.parseInt(JOptionPane.showInputDialog("Enter the minutes you want to add (integer value)."));
            secInput = Integer.parseInt(JOptionPane.showInputDialog("Enter the seconds you want to add (integer value)."));
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Wrong input, try again!");
        }

        if (hrInput < 0 || minInput < 0 || secInput < 0) {
            JOptionPane.showMessageDialog(null, "Wrong input, try again!");
        }

        remainingTime = hrInput*3600000 + minInput*60000 + secInput*1000; //updates time
        hours = remainingTime/3600000; //updates hours 
        minutes = remainingTime/60000 % 60; //updates minutes
        seconds = remainingTime/1000 % 60;

        secString = String.format("%02d", seconds);
        minString = String.format("%02d", minutes);
        hrString = String.format("%02d", hours);
            
        timeLabel.setText(hrString + ":" + minString + ":" + secString);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            if (!started) {
                started = true;
                startButton.setText("stop");
                start();
                setButton.setEnabled(false);
            }
            else {
                started = false;
                startButton.setText("start");
                stop();
                setButton.setEnabled(true);
            }
        }

        if (e.getSource() == setButton) {
            set();
            startButton.setEnabled(true);
        }
    }
}
