import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Main {
    private JButton time = new JButton("Time Calculator");
    private JButton basic = new JButton("Basic Calculator");
    private JButton big = new JButton("Big Int Calculator");
    private JButton graphing = new JButton("Graphing Calculator");
    private JTextField display = new JTextField(8);
    public static void main(String[] args) {
        Calculator calc = new TimeCalculator();
        JFrame mainWindow = new JFrame();
        mainWindow.setVisible(true);
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainWindow.setResizable(false);
        JPanel cont = new JPanel(new BorderLayout());
        cont.setBorder(new EmptyBorder(10,10,10,10));
        mainWindow.setLocationRelativeTo(null);
        cont.add(calc.getDisplay(),BorderLayout.PAGE_START);
        cont.add(calc.getButtons(),BorderLayout.CENTER);
        mainWindow.setContentPane(cont);
        mainWindow.pack();
        //mainWindow.setLayout(todo);

        //set a listener to check which button is pressed.
    }
}
