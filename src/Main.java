import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame implements ActionListener {

    JPanel navigator;
    Calculator calculator;
    JButton time,basic,bigInt,amButton,pmButton,colonButton;
    Main() {
        setTitle("Calculator");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(570, 600);
        setLayout(null);
        calculator = new TimeCalculator();

        navigator = new JPanel(null);
        navigator.setBounds(420,0,150,600);

        time = new JButton("Time");
        time.addActionListener(this);
        time.setBounds(0,25,100,40);
        basic = new JButton("Basic");
        basic.addActionListener(this);
        basic.setBounds(0,75,100,40);
        bigInt = new JButton("Big Integer");
        bigInt.addActionListener(this);
        bigInt.setBounds(0,125,100,40);
        amButton = new JButton("AM");
        amButton.addActionListener(this);
        bigInt.setBounds(0,175,100,40);
        pmButton = new JButton("PM");
        pmButton.addActionListener(this);
        bigInt.setBounds(0,225,100,40);
        colonButton = new JButton(":");
        colonButton.addActionListener(this);
        bigInt.setBounds(0,275,100,40);

        navigator.add(time);
        navigator.add(basic);

        add(calculator);
        add(navigator);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == time) {
            calculator = new TimeCalculator();
        }
        if (e.getSource() == basic) {
            calculator = new BasicCalculator();
        }
        if (e.getSource() == bigInt) {}
    }
}
