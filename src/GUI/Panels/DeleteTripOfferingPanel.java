package GUI.Panels;

import GUI.ActionListeners.DeleteTripOfferingListener;
import GUI.Frames.AdminControlPanelFrame;

import javax.swing.*;
import java.awt.*;

public class DeleteTripOfferingPanel extends JPanel {
    private final JTextField tripNumberTextField = new JTextField(20);
    private final JTextField dateTextField = new JTextField(20);
    private final JTextField scheduledStartTime = new JTextField(20);
    private final JButton submitButton = new JButton("Submit");
    private final TextArea resultsTextArea = new TextArea();

    public DeleteTripOfferingPanel() {

        GridBagConstraints c = new GridBagConstraints();
        this.setLayout(new GridBagLayout());

        this.setBackground(Color.GRAY);
        c.gridx = 0;
        c.gridy = 0;
        this.add(new JLabel("Trip Number: "), c);

        c.gridx = 1; // sets the column value
        c.fill = GridBagConstraints.HORIZONTAL; // fills in the space available
        this.add(tripNumberTextField, c); // add component to panel with the constraints


        c.gridx = 0;
        c.gridy = 1;

        this.add(new JLabel("Date: "), c);

        c.gridx = 1;
        this.add(dateTextField, c);

        c.gridx = 0;
        c.gridy = 2;
        this.add(new JLabel("Start Time: "), c);

        c.gridx = 1;
        this.add(scheduledStartTime, c);

        c.gridy = 3;
        this.add(submitButton, c);

        c.gridy = 4;
        c.gridwidth = 2;
        resultsTextArea.setPreferredSize(new Dimension(475, 650));
        JScrollPane scrollPane = new JScrollPane(resultsTextArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(scrollPane, c);

        resultsTextArea.setText(AdminControlPanelFrame
                .getInstance()
                .displayAllTripOfferings().toString()
        );

        submitButton.addActionListener(new DeleteTripOfferingListener(this));

    }


    public JTextField getTripNumberTextField() {
        return tripNumberTextField;
    }

    public JTextField getDateTextField() {
        return dateTextField;
    }

    public JTextField getScheduledStartTime() {
        return scheduledStartTime;
    }

    public JButton getSubmitButton() {
        return submitButton;
    }

    public TextArea getResultsTextArea() {
        return resultsTextArea;
    }
}
