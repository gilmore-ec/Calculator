import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Date;

public class TimeCalculator extends Calculator {
    private final JTextField start,end;
    private final JButton editStart;
    private final JLabel startPartOfDay,endPartOfDay;
    private boolean startMorning,endMorning,startEditable;
    TimeCalculator() {
        //sets default variables:
        operator = '+';
        endMorning = false;
        startEditable = false;

        //set up the displays
        start = new JTextField();
        start.setBounds(50,35,40,30);
        start.setEditable(false);
        start.setFocusable(false);
        end = new JTextField();
        end.setEditable(false);
        end.setFocusable(false);
        end.setBounds(285,35,40,30);

        //add labels to the input displays
        startPartOfDay = new JLabel();
        startPartOfDay.setBounds(92,45,25,15);
        startPartOfDay.setFocusable(false);
        getSystemTime();
        endPartOfDay = new JLabel();
        endPartOfDay.setBounds(327,45,25,15);
        endPartOfDay.setFocusable(false);
        JLabel startLabel = new JLabel("Start:");
        startLabel.setBounds(50,10,50,15);
        startLabel.setFocusable(false);
        JLabel inputLabel = new JLabel("Input:");
        inputLabel.setBounds(125,10,50,15);
        inputLabel.setFocusable(false);
        JLabel endLabel = new JLabel("End:");
        endLabel.setBounds(285,10,50,15);
        endLabel.setFocusable(false);

        editStart = new JButton("Edit Start?");
        editStart.setBounds(50,70,95,25);
        editStart.setFocusable(false);

        //add custom elements to the frame
        add(start);
        add(end);
        add(startLabel);
        add(inputLabel);
        add(endLabel);
        add(startPartOfDay);
        add(endPartOfDay);
        add(editStart);
    }

    /**
     * retrieves system time and sets the value for the start
     */
    private void getSystemTime() {
        Date date = new Date();
        String startTime = date.toString().substring(11,16);
        int hours = Integer.parseInt(startTime.substring(0,2));
        if (hours < 12) {
            startMorning = true;
        }
        startPartOfDay.setText(startMorning? "AM":"PM");
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
        calculations.requestFocus();
        calculations.setBounds(125,35,100,30);
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
        altA.setText("AM");
        altB.setText("PM");
    }

    /**
     *
     */
    private void startToNum() {
        int hours = Integer.parseInt(start.getText().substring(0,2));
        double minutes = Double.parseDouble(start.getText().substring(3));
        minutes = minutes/60;
        num1 = hours+minutes;
    }

    /**
     *
     * @return the resulting HH:MM of the calculation
     */
    private String returnEnd(double result) {
        int hours = (int)result;
        int minutes = (int)((result-hours)*60);

        //converts military time to standard
        if (hours>11) {
            hours = hours%12;
            endMorning = false;
        } else {
            endMorning = true;
        }

        //sets 0AM to 12AM
        hours = (hours==0?12:hours);

        //testing purposes
        System.out.printf("%02d:%02d%n",hours,minutes);

        return String.format("%02d:%02d",hours,minutes);
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
            } else {
                end.setText("");
                endPartOfDay.setText("");
            }
        }
        if (e.getSource() == addB) {
            operator = '+';
        }
        if (e.getSource() == subB) {
            operator = '-';
        }
        if (e.getSource() == altB) {
            startPartOfDay.setText("PM");
            startMorning = false;
        }
        if (e.getSource() == altA) {
            startPartOfDay.setText("AM");
            startMorning = true;
        }
        if (e.getSource() == equalsB) {
            if (!calculations.getText().isEmpty()) {
                startToNum();
                num2 = Double.parseDouble(calculations.getText());
                if (!Double.toString(num1).isEmpty() && !Double.toString(num2).isEmpty()) {
                    switch (operator) {
                        case '+' -> {
                            result = (num1 + num2)%24;
                            endPartOfDay.setText(result>11? "PM":"AM");
                            end.setText(returnEnd(result));
                        }
                        case '-' -> {
                            result = num1 - num2;
                            endPartOfDay.setText(result>11? "PM":"AM");
                            end.setText(returnEnd(result));
                        }
                    }
                } else if (Double.toString(num1).isEmpty()) {
                    getSystemTime();
                }
                //history logging:
                if (history.getText().isEmpty()) {
                    String hist = String.format("%s %s %c %f hours = %s %s",returnEnd(num1),(endMorning? "AM":"PM"),operator,num2,returnEnd(result),(endMorning? "AM":"PM"));
                    history.append(hist);
                    //history.append(returnEnd(num1) + " " + operator + " " + num2 + "hours = " + returnEnd);
                } else {
                    String hist = String.format("%s %s %c %f hours = %s %s",returnEnd(num1),(endMorning? "AM":"PM"),operator,num2,returnEnd(result),(endMorning? "AM":"PM"));
                    history.append("\n" + hist);
                }
            }
            calculations.setText("");
        }
        //show history:
        if (e.getSource().equals(hstB)) {
            historyFrame.setVisible(true);
        }
        //edit start:
        if (e.getSource().equals(editStart)) {
            startEditable = !startEditable;
            start.setEditable(startEditable);
            start.setFocusable(startEditable);
            System.out.println(startEditable);
        }
        //add a keyboard input check here
    }
}
