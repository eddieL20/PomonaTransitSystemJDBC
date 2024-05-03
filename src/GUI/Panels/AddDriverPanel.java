package GUI.Panels;

import GUI.ActionListeners.AddDriverListener;
import GUI.Frames.AdminControlPanelFrame;

import javax.swing.*;
import java.awt.*;

public class AddDriverPanel extends JPanel {
    private final JTextField driverName = new JTextField(20);
    private final JTextField telephoneNumber = new JTextField(20);
    private final JButton submitButton = new JButton("Submit");
    private final TextArea resultsTextArea = new TextArea();

    public AddDriverPanel(){
        GridBagConstraints c = new GridBagConstraints();
        this.setLayout(new GridBagLayout());

        this.setBackground(Color.GRAY);

        c.gridx = 0;
        c.gridy = 0;
        this.add(new JLabel("Driver's Name"), c);

        c.gridx = 1;
        this.add(driverName, c);

        c.gridx = 0;
        c.gridy = 1;
        this.add(new JLabel("Telephone Number: "),c);

        c.gridx = 1;
        this.add(telephoneNumber, c);

        c.gridy = 2;
        this.add(submitButton, c);
        submitButton.addActionListener(new AddDriverListener(this));

        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;

        resultsTextArea.setPreferredSize(new Dimension(475, 550));
        JScrollPane scrollPane = new JScrollPane(resultsTextArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(scrollPane, c);

        resultsTextArea.setText(
                AdminControlPanelFrame
                        .getInstance()
                        .displayAllDrivers()
                        .toString()
        );
    }
    public JTextField getDriverName() {
        return driverName;
    }

    public JTextField getTelephoneNumber() {
        return telephoneNumber;
    }

    public JButton getSubmitButton() {
        return submitButton;
    }

    public TextArea getResultsTextArea() {
        return resultsTextArea;
    }
}
