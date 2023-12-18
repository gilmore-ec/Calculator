import java.awt.event.ActionEvent;

public class BasicCalculator extends Calculator {

    BasicCalculator() {}

    /**
     * Sets the format to the calculations JTextField
     */
    @Override
    public void formatDisplay() {
        calculations.setBounds(50,25,300,50);
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
        altA.setText("*");
        altB.setText("/");
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
