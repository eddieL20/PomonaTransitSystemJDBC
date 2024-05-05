package GUI.Panels;

import GUI.ActionListeners.ActualTripStopInfoListener;
import GUI.Frames.AdminControlPanelFrame;

import javax.swing.*;
import java.awt.*;

public class ActualTripStopInfoPanel extends JPanel {
     private final JTextField tripNumber = new JTextField(20);
     private final JTextField date = new JTextField(20);
     private final JTextField scheduledStartTime = new JTextField(20);
     private final JTextField stopNumber = new JTextField(20);
     private final JTextField scheduledArrivalTime = new JTextField(20);
     private final JTextField actualStartTime = new JTextField(20);
     private final JTextField actualArrivalTime = new JTextField(20);
     private final JTextField numberOfPassengersIn = new JTextField(20);
     private final JTextField numberOfPassengersOut = new JTextField(20);
     private final JButton submitButton = new JButton("Submit");
     private final TextArea resultsTextArea = new TextArea();

    public ActualTripStopInfoPanel(){
        GridBagConstraints c = new GridBagConstraints();
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.GRAY);

        c.gridx = 0;
        c.gridy = 0;
        this.add(new JLabel("Trip Number: "), c);
        c.gridx = 1;
        this.add(tripNumber, c);

        c.gridx = 0;
        c.gridy = 1;
        this.add(new JLabel("Date: "), c);
        c.gridx = 1;
        this.add(date, c);

        c.gridx = 0;
        c.gridy = 2;
        this.add(new JLabel("Scheduled Start Time: "), c);
        c.gridx = 1;
        this.add(scheduledStartTime, c);

        c.gridx = 0;
        c.gridy = 3;
        this.add(new JLabel("Stop Number: "), c);
        c.gridx =1;
        this.add(stopNumber, c);

        c.gridx = 0;
        c.gridy = 4;
        this.add(new JLabel("Scheduled Arrival Time: "), c);
        c.gridx = 1;
        this.add(scheduledArrivalTime, c);

        c.gridx = 0;
        c.gridy = 5;
        this.add(new JLabel("Actual Start Time: "), c);
        c.gridx = 1;
        this.add(actualStartTime, c);

        c.gridx = 0;
        c.gridy = 6;
        this.add(new JLabel("Actual Arrival Time: "), c);
        c.gridx = 1;
        this.add(actualArrivalTime, c);

        c.gridx = 0;
        c.gridy = 7;
        this.add(new JLabel("Number of Passengers: "), c);
        c.gridx = 1;
        this.add(numberOfPassengersIn, c);

        c.gridx = 0;
        c.gridy = 8;
        this.add(new JLabel("Number of Passengers Out: "), c);
        c.gridx = 1;
        this.add(numberOfPassengersOut, c);

        c.gridy = 9;
        this.add(submitButton, c);
        submitButton.addActionListener(new ActualTripStopInfoListener(this));

        c.gridx = 0;
        c.gridy = 10;
        c.gridwidth = 2;
        resultsTextArea.setPreferredSize(new Dimension(475, 450));
        JScrollPane scrollPane = new JScrollPane(resultsTextArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(scrollPane, c);

        resultsTextArea.setText(
                AdminControlPanelFrame
                        .getInstance()
                        .displayAllActualTripInfo()
        );
    }

    public JTextField getTripNumber() {
        return tripNumber;
    }

    public JTextField getDate() {
        return date;
    }

    public JTextField getScheduledStartTime() {
        return scheduledStartTime;
    }

    public JTextField getStopNumber() {
        return stopNumber;
    }

    public JTextField getScheduledArrivalTime() {
        return scheduledArrivalTime;
    }

    public JTextField getActualStartTime() {
        return actualStartTime;
    }

    public JTextField getActualArrivalTime() {
        return actualArrivalTime;
    }

    public JTextField getNumberOfPassengersIn() {
        return numberOfPassengersIn;
    }

    public JTextField getNumberOfPassengersOut() {
        return numberOfPassengersOut;
    }

    public JButton getSubmitButton() {
        return submitButton;
    }

    public TextArea getResultsTextArea() {
        return resultsTextArea;
    }
}
