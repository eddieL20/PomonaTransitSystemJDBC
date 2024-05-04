package GUI.Panels;

import GUI.ActionListeners.DisplayTripStopsListener;

import javax.swing.*;
import java.awt.*;

public class DisplayTripStopPanel extends JPanel {
    private final JTextField tripNumber = new JTextField(20);
    private final JButton submitButton = new JButton("Submit");
    private final TextArea resultsTextArea = new TextArea();

    public DisplayTripStopPanel(){
        GridBagConstraints c = new GridBagConstraints();
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.GRAY);

        c.gridx = 0;
        c.gridy = 0;
        this.add(new JLabel("Trip Number: "), c);
        c.gridx = 1;
        this.add(tripNumber, c);

        c.gridy = 2;
        this.add(submitButton, c);
        submitButton.addActionListener(
                new DisplayTripStopsListener(this)
        );

        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        resultsTextArea.setPreferredSize(new Dimension(475, 550));
        JScrollPane scrollPane = new JScrollPane(resultsTextArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(scrollPane, c);
    }

    public JTextField getTripNumber() {
        return tripNumber;
    }

    public JButton getSubmitButton() {
        return submitButton;
    }

    public TextArea getResultsTextArea() {
        return resultsTextArea;
    }
}
