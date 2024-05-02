package GUI.Panels;

import GUI.ActionListeners.DisplayTripScheduleListener;
import GUI.ActionListeners.TripScheduleViewListener;

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
        this.setSize(500, 200);
        this.setLayout(new GridBagLayout());

        this.setBackground(Color.GRAY);
        this.setSize(400, 700);
        c.gridx = 0;
        c.gridy = 0;
        this.add(new JLabel("Starting Location: "), c);

        c.gridx = 1; // sets the column value
        c.fill = GridBagConstraints.HORIZONTAL; // fills in the space available
        c.insets = new Insets(5, 5, 5, 50); // sets padding
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
        resultsTextArea.setPreferredSize(new Dimension(475, 250));
        resultsTextArea.setEditable(false);
        this.add(new JScrollPane(resultsTextArea), c);

        DisplayTripScheduleListener displayTripScheduleListener= new DisplayTripScheduleListener(this);
        submitButton.addActionListener(displayTripScheduleListener);


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
