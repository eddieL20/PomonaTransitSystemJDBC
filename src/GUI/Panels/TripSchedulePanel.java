package GUI.Panels;

import GUI.ActionListeners.DisplayTripScheduleListener;

import javax.swing.*;
import java.awt.*;

public class TripSchedulePanel extends JPanel {
    private final JTextField startLocationTextField = new JTextField(20);
    private final JTextField destinationTextField = new JTextField(20);
    private final JTextField dateTextField = new JTextField(20);
    private final JButton submitButton = new JButton("Submit");
    private final JTextArea resultsTextArea = new JTextArea();
    public TripSchedulePanel(){

        GridBagConstraints c = new GridBagConstraints();
        this.setLayout(new GridBagLayout());

        this.setBackground(Color.GRAY);
        c.gridx = 0;
        c.gridy = 0;
        this.add(new JLabel("Starting Location: "), c);

        c.gridx = 1; // sets the column value
        c.fill = GridBagConstraints.HORIZONTAL; // fills in the space available
        this.add(startLocationTextField, c); // add component to panel with the constraints


        c.gridx = 0;
        c.gridy = 1;

        this.add(new JLabel("Destination: "), c);

        c.gridx = 1;
        this.add(destinationTextField, c);

        c.gridx = 0;
        c.gridy = 2;
        this.add(new JLabel("Date: "), c);

        c.gridx = 1;
        this.add(dateTextField, c);

        c.gridy = 3;
        this.add(submitButton, c);

        c.gridy = 4;
        c.gridwidth = 2;
        resultsTextArea.setPreferredSize(new Dimension(475, 650));
        JScrollPane scrollPane = new JScrollPane(resultsTextArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(scrollPane, c);

        submitButton.addActionListener(new DisplayTripScheduleListener(this));

    }

    public JTextField getStartLocationTextField() {
        return startLocationTextField;
    }

    public JTextField getDestinationTextField() {
        return destinationTextField;
    }

    public JTextField getDateTextField() {
        return dateTextField;
    }

    public JButton getSubmitButton() {
        return submitButton;
    }

    public JTextArea getResultsTextArea() {
        return resultsTextArea;
    }
}
