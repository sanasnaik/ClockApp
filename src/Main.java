import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null, "Welcome to the clock program.", "Clock", JOptionPane.INFORMATION_MESSAGE);
        String[] options = {"Clock", "Timer", "Stopwatch"};
        int option = JOptionPane.showOptionDialog(null, "What program do you want to use?", "Clock", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, 0);
        if (option == 2) {
            new Stopwatch();
        }
    }
}
