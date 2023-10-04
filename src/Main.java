import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null, "Welcome to the time program.", "Time Program", JOptionPane.INFORMATION_MESSAGE);
        String[] options = {"Stopwatch", "Timer"};
        int option = JOptionPane.showOptionDialog(null, "What program do you want to use?", "Time Program", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, 0);
        if(option == 0) {
            new Stopwatch();
        }
        if (option == 1) {
            new TimerProgram();
        }

    }
}
