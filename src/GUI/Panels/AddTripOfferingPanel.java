package GUI.Panels;

import GUI.ActionListeners.AddTripOfferingListener;
import GUI.Frames.AdminControlPanelFrame;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Time;

public class AddTripOfferingPanel extends JPanel {

    private final JTextField tripNumber = new JTextField(20);
    private final JTextField date = new JTextField(20);
    private final JTextField scheduledStartTime = new JTextField(20);
    private final JTextField scheduledArrivalTime = new JTextField(20);
    private final JTextField driverName = new JTextField(20);
    private final JTextField busID = new JTextField(20);
    private final JButton submitButton = new JButton("Submit");
    private final TextArea resultsTextArea = new TextArea();

    public AddTripOfferingPanel(){

        GridBagConstraints c = new GridBagConstraints();
        this.setLayout(new GridBagLayout());

        this.setBackground(Color.GRAY);
        c.gridx = 0;
        c.gridy = 0;
        this.add(new JLabel("Trip Number: "), c);

        c.gridx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(tripNumber, c);

        c.gridx = 0;
        c.gridy = 1;
        this.add(new JLabel("Date (YYYY-mm-dd): "), c);

        c.gridx = 1;
        this.add(date, c);

        c.gridx = 0;
        c.gridy = 2;
        this.add(new JLabel("Scheduled Start Time: "), c);

        c.gridx = 1;
        this.add(scheduledStartTime, c);

        c.gridx = 0;
        c.gridy = 3;
        this.add(new JLabel("Scheduled Arrival Time: "), c);

        c.gridx = 1;
        this.add(scheduledArrivalTime, c);

        c.gridx = 0;
        c.gridy = 4;
        this.add(new JLabel("Driver: "), c);

        c.gridx = 1;
        this.add(driverName, c);

        c.gridx = 0;
        c.gridy = 5;
        this.add(new JLabel("Bus ID: "), c);

        c.gridx = 1;
        this.add(busID, c);

        c.gridx = 0;
        c.gridy = 6;
        this.add(submitButton, c);

        c.gridy = 7;
        c.gridwidth = 2;
        resultsTextArea.setPreferredSize(new Dimension(475, 550));
        JScrollPane scrollPane = new JScrollPane(resultsTextArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(scrollPane, c);

        submitButton.addActionListener(new AddTripOfferingListener(this));

        resultsTextArea.setText(AdminControlPanelFrame
                .getInstance()
                .displayAllTripOfferings()
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

    public JTextField getScheduledArrivalTime() {
        return scheduledArrivalTime;
    }

    public JTextField getDriverName() {
        return driverName;
    }

    public JTextField getBusID() {
        return busID;
    }

    public JButton getSubmitButton() {
        return submitButton;
    }

    public TextArea getResultsTextArea() {
        return resultsTextArea;
    }
}
