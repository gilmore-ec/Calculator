import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.*;
import java.util.Date;

public class TimeCalculator extends Calculator {
    private final JTextField start;
    private boolean startMorning;
    private final JTextField end;
    private boolean endMorning;
    TimeCalculator() {
        //set up the displays
        start = new JTextField();
        start.setBounds(50,35,40,30);
        start.setEditable(false);
        end = new JTextField();
        end.setEditable(false);
        end.setFocusable(false);
        end.setBounds(285,35,40,30);

        getSystemTime();

        //add labels to the input displays
        JLabel startLabel = new JLabel("Start:");
        startLabel.setBounds(50,10,50,15);
        startLabel.setFocusable(false);
        JLabel endLabel = new JLabel("End:");
        endLabel.setBounds(285,10,50,15);
        endLabel.setFocusable(false);
        JLabel startPartOfDay = new JLabel("AM");
        startPartOfDay.setBounds(150,45,25,15);
        startPartOfDay.setFocusable(false);
        JLabel endPartOfDay = new JLabel("AM");
        endPartOfDay.setBounds(302,45,25,15);
        endPartOfDay.setFocusable(false);

        //add the new Display to the frame
        add(start);
        add(end);
        add(startLabel);
        add(endLabel);
        add(startPartOfDay);
        add(endPartOfDay);
    }

    /**
     * retrieves system time and sets the value for the start
     */
    private void getSystemTime() {
        Date date = new Date();
        String startTime = date.toString().substring(11,16);
        int hours = Integer.parseInt(startTime.substring(0,2));
        if (hours > 12) {
            startMorning = true;
        }

        hours = (startMorning? hours:hours%12);

        startTime = String.format("%02d%s",hours,startTime.substring(2));

        start.setText(startTime);

        //testing purposes
        System.out.println("Current Time: " + startTime + (startMorning? " AM":" PM"));

    }
    /**
     * Sets the format to the calculations JTextField
     */
    @Override
    public void formatDisplay() {
        calculations.setFocusable(true);
        calculations.setEditable(true);
        calculations.setBounds(100,35,80,30);
    }

    /**
     * Adds class-specific JComponents to the JFrame
     */
    @Override
    public void addSpecial() {
    }

    /**
     * Formats class-specific JButtons for the calculator
     */
    @Override
    public void modifyButtons() {
        decB.setText(":");
        altA.setText("AM");
        altB.setText("PM");
    }

    /**
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i<10 ; i++) {
            if (e.getSource() == buttons[i]) {
                calculations.setText(calculations.getText()+i);
            }
        }
        if (e.getSource() == decB) {
            calculations.setText(calculations.getText()+(calculations.getText().contains(".")? "": "."));
        }
        if (e.getSource() == delB && !calculations.getText().isEmpty()) {
            calculations.setText(calculations.getText().substring(0,calculations.getText().length()-1));
        }
        if (e.getSource() == clrB) {
            if (!calculations.getText().isEmpty()) {
                calculations.setText("");
            } else num1 = 0;
        }
        if (e.getSource() == addB && !("" + num1).isEmpty()) {
            operator = '+';
            num1 = Double.parseDouble((negative? "-":"") + calculations.getText());
            calculations.setText("");
        }
        if (e.getSource() == subB && calculations.getText().isEmpty()) {
            calculations.setText("-");
        }
        else if (e.getSource() == subB && !("" + num1).isEmpty()) {
            operator = '-';
            num1 = Double.parseDouble((negative? "-":"") + calculations.getText());
            calculations.setText("");
        }
        else if (e.getSource() == subB && ("" + num1).isEmpty()) {
            negative = true;
        }
        if (e.getSource() == altB && !("" + num1).isEmpty()) {
            operator = '/';
            num1 = Double.parseDouble((negative? "-":"") + calculations.getText());
            calculations.setText("");
        }
        if (e.getSource() == altA && !("" + num1).isEmpty()) {
            operator = '*';
            num1 = Double.parseDouble((negative? "-":"") + calculations.getText());
            calculations.setText("");
        }
        if (e.getSource() == equalsB) {
            if (!calculations.getText().isEmpty()) {
                num2 = Double.parseDouble(calculations.getText());
                if (!Double.toString(num1).isEmpty() && !Double.toString(num2).isEmpty()) {
                    switch (operator) {
                        case '+' -> {
                            result = num1 + num2;
                            calculations.setText("" + result);
                        }
                        case '-' -> {
                            result = num1 - num2;
                            calculations.setText("" + result);
                        }
                        case '*' -> {
                            result = num1 * num2;
                            calculations.setText("" + result);
                        }
                        case '/' -> {
                            if (num2 == 0.0) {
                                calculations.setText("Cannot divide by 0!");
                            } else {
                                result = num1 / num2;
                                calculations.setText("" + result);
                            }
                        }
                    }
                } else if (Double.toString(num1).isEmpty()) {
                    if (!calculations.getText().isEmpty()) {
                        num1 = Double.parseDouble(calculations.getText());
                    }
                }
                if (history.getText().isEmpty()) {
                    history.append(num1 + " " + operator + " " + num2 + " = " + result);
                } else {
                    history.append("\n" + num1 + " " + operator + " " + num2 + " = " + result);
                }
            }
        }
        if (e.getSource().equals(hstB)) {
            historyFrame.setVisible(true);
        }
        //add a keyboard input check here
    }
}
