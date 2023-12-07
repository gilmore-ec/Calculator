import javax.print.attribute.standard.DocumentName;
import javax.swing.*;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.PlainDocument;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Calculator {
    private JPanel calc;
    private JPanel buttons;
    private JPanel display;
    Calculator() {
        calc = new JPanel();
        calc.setLayout(new BorderLayout());
        generateButtons();
        generateDisplay();
        calc.add(display,BorderLayout.CENTER);
        calc.add(buttons, BorderLayout.CENTER);
    }


    /**
     * Generates the standard calculator buttons
     */
    private void generateButtons() {
        buttons = new JPanel(new GridLayout(4,3));
        JButton b0 = new JButton("0");
        JButton b1 = new JButton("1");
        JButton b2 = new JButton("2");
        JButton b3 = new JButton("3");
        JButton b4 = new JButton("4");
        JButton b5 = new JButton("5");
        JButton b6 = new JButton("6");
        JButton b7 = new JButton("7");
        JButton b8 = new JButton("8");
        JButton b9 = new JButton("9");
        JButton bPlus = new JButton("+");
        JButton bMinus = new JButton("-");
        buttons.add(b7);
        buttons.add(b8);
        buttons.add(b9);
        buttons.add(b4);
        buttons.add(b5);
        buttons.add(b6);
        buttons.add(b1);
        buttons.add(b2);
        buttons.add(b3);
        buttons.add(bPlus);
        buttons.add(b0);
        buttons.add(bMinus);
        buttons.setSize(200,300);
        buttons.isOpaque();
    }
    /**
     * Generates the standard text display
     */
    private void generateDisplay() {
        this.display = new JPanel();
        this.display.isOpaque();
        JTextField history = new JTextField();
        history.setText("History: ");
        history.setEditable(false);
        this.display.add(history);
        this.display.add(new JLabel("Enter calculation: "));
        this.display.add(new JTextField(20));
        display.setLayout(new BoxLayout(display,BoxLayout.Y_AXIS));
    }
    /**
     *
     * @return the JPanel of the current instance's buttons
     */
    public JPanel getButtons() {
        return buttons;
    }
    public JPanel getDisplay() {
        return display;
    }
}