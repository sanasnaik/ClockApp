import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TimerProgram implements ActionListener {
    JFrame frame = new JFrame();

    JButton startButton = new JButton("start");
    //when pressed, timer starts countdown. only available if time is not 0.

    JButton setButton = new JButton("set");
    //when pressed, opens a dialog box where you need to
    //type the number of minutes, hours, and seconds
    //and it will start from that time

    JLabel timeLabel = new JLabel();
    int elapsedTime = 0;
    int seconds = 0;
    int minutes = 0;
    int hours = 0;
    boolean started = false;
    String secString = String.format("%02d", seconds);
    String minString = String.format("%02d", minutes);
    String hrString = String.format("%02d", hours);
    
    Timer timer = new Timer(1000, new ActionListener() { //every 1 second, do this
        public void actionPerformed(ActionEvent e) {
            elapsedTime -= 1000; //updates time
            hours = (elapsedTime/3600000); //updates hours 
            minutes = (elapsedTime/60000) % 60; //updates minutes
            seconds = (elapsedTime/1000) % 60;

            secString = String.format("%02d", seconds);
            minString = String.format("%02d", minutes);
            hrString = String.format("%02d", hours);
            
            timeLabel.setText(hrString + " : " + minString + " : " + secString);
        }
    });

    TimerProgram() {
        timeLabel.setText(hrString + " : " + minString + " : " + secString);
        timeLabel.setBounds(200, 100, 200, 100);
        timeLabel.setFont(new Font("Roboto",Font.PLAIN, 35));
        timeLabel.setBorder(BorderFactory.createBevelBorder(1));
        timeLabel.setOpaque(true);
        timeLabel.setHorizontalAlignment(JTextField.CENTER);

        startButton.setBounds(200,200,100,50);
        startButton.setFont(new Font("Roboto", Font.PLAIN, 20));
        startButton.setFocusable(false);
        startButton.addActionListener(this);

        setButton.setBounds(300,200,100,50);
        setButton.setFont(new Font("Roboto", Font.PLAIN, 20));
        setButton.setFocusable(false);
        setButton.addActionListener(this);

        frame.add(startButton);
        frame.add(setButton);
        frame.add(timeLabel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,500);
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

    //Sets the stopwatch to the user input.
    public void set() {
        JOptionPane.showInputDialog(null, "Enter the hours you want to add.", "Timer: Set Hours", null, null, null, null);
        timer.stop();

        elapsedTime = 0;
        seconds = 0;
        minutes = 0;
        hours = 0;

        secString = String.format("%02d", seconds);
        minString = String.format("%02d", minutes);
        hrString = String.format("%02d", hours);
            
        timeLabel.setText(hrString + " : " + minString + " : " + secString);
    }

    public void setTime() {

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            if (!started) {
                started = true;
                startButton.setText("stop");
                start();
            }
            else {
                started = false;
                startButton.setText("start");
                stop();
            }
        }

        if (e.getSource() == setButton) {
            set();
        }
    }

}
