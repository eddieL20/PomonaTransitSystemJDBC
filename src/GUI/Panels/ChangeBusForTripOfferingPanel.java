package GUI.Panels;

import GUI.ActionListeners.ChangeBusForTripOfferingListener;
import GUI.Frames.AdminControlPanelFrame;

import javax.swing.*;
import java.awt.*;

public class ChangeBusForTripOfferingPanel extends JPanel {
    private final JTextField tripNumber = new JTextField(20);
    private final JTextField date = new JTextField(20);
    private final JTextField scheduledStartTime = new JTextField(20);
    private final JTextField newBusID = new JTextField(20);
    private final JButton submitButton = new JButton("Submit");
    private final TextArea resultsTextArea = new TextArea();

    public ChangeBusForTripOfferingPanel(){
        GridBagConstraints c = new GridBagConstraints();
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.GRAY);

        c.gridx = 0;
        c.gridy = 0;
        this.add(new JLabel("Trip Number: "));
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
        this.add(new JLabel("New Bus ID: "), c);
        c.gridx = 1;
        this.add(newBusID, c);

        c.gridy = 4;
        this.add(submitButton, c);
        submitButton.addActionListener(new ChangeBusForTripOfferingListener(this));

        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 2;
        resultsTextArea.setPreferredSize(new Dimension(475, 550));
        JScrollPane scrollPane = new JScrollPane(resultsTextArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(scrollPane, c);

        resultsTextArea.setText(
                AdminControlPanelFrame
                        .getInstance()
                        .displayAllTripOfferings()
                        .toString()
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

    public JTextField getNewBusID() {
        return newBusID;
    }

    public JButton getSubmitButton() {
        return submitButton;
    }

    public TextArea getResultsTextArea() {
        return resultsTextArea;
    }
}
