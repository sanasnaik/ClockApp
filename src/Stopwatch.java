import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Stopwatch implements ActionListener {
    JFrame frame = new JFrame();
    JButton startButton = new JButton("start");
    JButton resetButton = new JButton("reset");
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
            elapsedTime += 1000; //updates time
            hours = (elapsedTime/3600000); //updates hours 
            minutes = (elapsedTime/60000) % 60; //updates minutes
            seconds = (elapsedTime/1000) % 60;

            secString = String.format("%02d", seconds);
            minString = String.format("%02d", minutes);
            hrString = String.format("%02d", hours);
            
            timeLabel.setText(hrString + ":" + minString + ":" + secString);
        }
    });

    //Sets up the format of the window, labels, buttons etc.
    Stopwatch() {
        timeLabel.setText(hrString + ":" + minString + ":" + secString);
        timeLabel.setBounds(200, 100, 200, 100);
        timeLabel.setFont(new Font("Courier",Font.PLAIN, 35));
        timeLabel.setBorder(BorderFactory.createBevelBorder(1));
        timeLabel.setOpaque(true);
        timeLabel.setHorizontalAlignment(JTextField.CENTER);

        startButton.setBounds(200,200,100,50);
        startButton.setFont(new Font("Courier", Font.PLAIN, 20));
        startButton.setFocusable(false);
        startButton.addActionListener(this);

        resetButton.setBounds(300,200,100,50);
        resetButton.setFont(new Font("Courier", Font.PLAIN, 20));
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);

        frame.add(startButton);
        frame.add(resetButton);
        frame.add(timeLabel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,500);
        frame.setTitle("Stopwatch");
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

    //Reset the stopwatch and set time to 0.
    public void reset() {
        timer.stop();

        elapsedTime = 0;
        seconds = 0;
        minutes = 0;
        hours = 0;

        secString = String.format("%02d", seconds);
        minString = String.format("%02d", minutes);
        hrString = String.format("%02d", hours);
            
        timeLabel.setText(hrString + ":" + minString + ":" + secString);
    }

    @Override
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
        if (e.getSource() == resetButton) {
            started = false;
            startButton.setText("start");
            reset();
        }
    }
}
