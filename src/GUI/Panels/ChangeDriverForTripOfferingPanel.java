package GUI.Panels;

import GUI.ActionListeners.ChangeDriverForTripOfferingListener;
import GUI.Frames.AdminControlPanelFrame;

import javax.swing.*;
import java.awt.*;

public class ChangeDriverForTripOfferingPanel extends JPanel {
    private final JTextField tripNumber = new JTextField(20);
    private final JTextField date = new JTextField(20);
    private final JTextField scheduledStartTime = new JTextField(20);
    private final JTextField newDriverName = new JTextField(20);
    private final JButton submitButton = new JButton("Submit");
    private final TextArea resultsTextArea = new TextArea();

    public ChangeDriverForTripOfferingPanel(){
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
        this.add(new JLabel("Date (YYY-mm-dd): "), c);
        c.gridx = 1;
        this.add(date, c);

        c.gridx = 0;
        c.gridy = 2;
        this.add(new JLabel("Scheduled Start Time: "), c);
        c.gridx = 1;
        this.add(scheduledStartTime, c);

        c.gridx = 0;
        c.gridy = 3;
        this.add(new JLabel("New Driver's Name: "), c);
        c.gridx = 1;
        this.add(newDriverName, c);

        c.gridy = 4;
        this.add(submitButton, c);
        submitButton.addActionListener(
                new ChangeDriverForTripOfferingListener(this)
        );

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

    public JTextField getNewDriverName() {
        return newDriverName;
    }

    public JButton getSubmitButton() {
        return submitButton;
    }

    public TextArea getResultsTextArea() {
        return resultsTextArea;
    }
}
