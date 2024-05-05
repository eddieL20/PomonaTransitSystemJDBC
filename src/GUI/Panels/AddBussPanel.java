package GUI.Panels;

import GUI.ActionListeners.AddBusListener;
import GUI.Frames.AdminControlPanelFrame;

import javax.swing.*;
import java.awt.*;

public class AddBussPanel extends JPanel {
    private final JTextField budID = new JTextField(20);
    private final JTextField model = new JTextField(20);
    private final JTextField year = new JTextField(20);
    private final JButton submitButton = new JButton("Submit");
    private final TextArea resultsTextArea = new TextArea();

    public AddBussPanel(){
        GridBagConstraints c = new GridBagConstraints();
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.GRAY);

        c.gridx = 0;
        c.gridy = 0;
        this.add(new JLabel("Bus ID: "), c);
        c.gridx = 1;
        this.add(budID, c);

        c.gridx = 0;
        c.gridy = 1;
        this.add(new JLabel("Model: "), c);
        c.gridx = 1;
        this.add(model, c);

        c.gridx = 0;
        c.gridy = 2;
        this.add(new JLabel("Year: "), c);
        c.gridx = 1;
        this.add(year, c);

        c.gridy = 3;
        this.add(submitButton, c);
        submitButton.addActionListener(new AddBusListener(this));

        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 2;
        resultsTextArea.setPreferredSize(new Dimension(475, 550));
        JScrollPane scrollPane = new JScrollPane(resultsTextArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(scrollPane, c);

        resultsTextArea.setText(
                AdminControlPanelFrame
                        .getInstance()
                        .displayAllBuses()
        );
    }

    public JTextField getBudID() {
        return budID;
    }

    public JTextField getModel() {
        return model;
    }

    public JTextField getYear() {
        return year;
    }

    public JButton getSubmitButton() {
        return submitButton;
    }

    public TextArea getResultsTextArea() {
        return resultsTextArea;
    }
}
