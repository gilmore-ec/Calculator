import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.*;

public class TimeCalculator extends Calculator {
    private JTextField start;
    private JTextField end;
    TimeCalculator() {
        //set up the displays
        start = new JTextField();
        start.setBounds(50,25,100,50);
        start.setEditable(false);
        end = calculations;
        end.setBounds(202,25,100,50);

        String sysTime = getSystemTime();

        //add labels to the input displays
        JLabel startLabel = new JLabel("Start");
        startLabel.setBounds(50,10,50,15);
        startLabel.setFocusable(false);
        JLabel endLabel = new JLabel("End");
        endLabel.setBounds(202,10,50,15);
        endLabel.setFocusable(false);
        JLabel startPartOfDay = new JLabel("AM");
        startPartOfDay.setBounds(150,45,25,15);
        startPartOfDay.setFocusable(false);
        JLabel endPartOfDay = new JLabel("AM");
        endPartOfDay.setBounds(302,45,25,15);
        endPartOfDay.setFocusable(false);

        //add the new Display to the frame
        add(start);
        add(startLabel);
        add(endLabel);
        add(startPartOfDay);
        add(endPartOfDay);
    }

    /**
     *
     */
    private String getSystemTime() {
        try {
            InetAddress localhost = InetAddress.getLocalHost();
            String url = "https://timeapi.io/api/Time/current/ip=" + localhost.toString();
            URL api = new URL(url);
            HttpURLConnection c = (HttpURLConnection) api.openConnection();
            c.setRequestMethod("GET");
            if (c.getResponseCode() == 200) {
                System.out.println("success");
                return "success";
            }
            else {
                System.out.println(c.getResponseCode());
                System.out.println(url);
                return "fail";
            }
        }
        catch (IOException e) {
            return "fail";
        }
    }
    /**
     * Sets the format to the calculations JTextField
     */
    @Override
    public void formatDisplay() {}

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
        //todo
    }
}
