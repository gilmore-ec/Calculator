import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

public abstract class Calculator extends JPanel implements ActionListener, KeyListener {

    JTextField calculations;
    JButton[] buttons = new JButton[10];
    JButton addB,subB,altA,altB,equalsB;
    JButton delB,clrB,hstB,decB;
    JPanel panel;
    double num1,num2,result;
    char operator;
    JFrame historyFrame;
    JPanel historyPane;
    JTextArea history;
    boolean negative;
    Calculator() {
        setSize(420,600);
        setLayout(null);

        historyFrame = new JFrame("History");
        historyPane = new JPanel();
        historyFrame.setSize(250,400);
        history = new JTextArea();
        historyPane.add(history);
        historyFrame.add(historyPane);
        historyFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

        //instantiate the input display
        calculations = new JTextField();
        calculations.setEditable(false);

        //this method formats the input display
        formatDisplay();

        //instantiate number buttons
        for (int i = 0; i<10 ; i++) {
            buttons[i] = new JButton(i+"");
            buttons[i].addActionListener(this);
            buttons[i].setFocusable(false);
        }

        //instantiate buttons
        addB = new JButton("+");
        addB.addActionListener(this);
        addB.setFocusable(false);
        subB = new JButton("-");
        subB.addActionListener(this);
        subB.setFocusable(false);
        altB = new JButton("alt-b");
        altB.addActionListener(this);
        altB.setFocusable(false);
        altA = new JButton("alt-a");
        altA.addActionListener(this);
        altA.setFocusable(false);
        equalsB = new JButton("=");
        equalsB.addActionListener(this);
        equalsB.setFocusable(false);
        altA.setFocusable(false);
        decB = new JButton(".");
        decB.addActionListener(this);
        decB.setFocusable(false);
        delB = new JButton("Delete");
        delB.addActionListener(this);
        delB.setFocusable(false);
        delB.setBounds(50,430,75,50);
        clrB = new JButton("Clear");
        clrB.addActionListener(this);
        clrB.setFocusable(false);
        clrB.setBounds(275,430,75,50);
        hstB = new JButton("Show History");
        hstB.addActionListener(this);
        hstB.setFocusable(false);
        hstB.setBounds(125,430,150,50);

        //this method allows buttons to be assigned new values as needed:
        modifyButtons();

        //instantiate panel for buttons
        panel = new JPanel();
        panel.setLayout(new GridLayout(4,4,7,7));
        panel.setBounds(50,100,300,300);
        panel.add(buttons[1]);
        panel.add(buttons[2]);
        panel.add(buttons[3]);
        panel.add(altB);
        panel.add(buttons[4]);
        panel.add(buttons[5]);
        panel.add(buttons[6]);
        panel.add(altA);
        panel.add(buttons[7]);
        panel.add(buttons[8]);
        panel.add(buttons[9]);
        panel.add(subB);
        panel.add(decB);
        panel.add(buttons[0]);
        panel.add(equalsB);
        panel.add(addB);

        //finalize the application's JFrame
        addSpecial();
        add(delB);
        add(hstB);
        add(clrB);
        add(panel);
        add(calculations);
        setVisible(true);
    }

    /**
     * formats class-specific JButtons for the calculator
     */
    public abstract void modifyButtons();

    /**
     * Sets the format to the calculations JTextField
     */
    public abstract void formatDisplay();

    /**
     * Adds class-specific JComponents to the JFrame
     */
    public abstract void addSpecial();

}
