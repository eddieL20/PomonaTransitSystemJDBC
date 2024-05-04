package GUI.Panels;

import GUI.ActionListeners.DeleteBusListener;
import GUI.Frames.AdminControlPanelFrame;

import javax.swing.*;
import java.awt.*;

public class DeleteBusPanel extends JPanel {
    private final JTextField busID = new JTextField(20);
    private final JButton submitButton = new JButton("Submit");
    private final TextArea resultsTextArea = new TextArea();

    public DeleteBusPanel(){
        GridBagConstraints c = new GridBagConstraints();
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.GRAY);

        c.gridx = 0;
        c.gridy = 0;
        this.add(new JLabel("Bus ID: "), c);
        c.gridx = 1;
        this.add(busID, c);

        c.gridy = 1;
        this.add(submitButton, c);
        submitButton.addActionListener(new DeleteBusListener(this));

        c.gridx = 0;
        c.gridy = 2;
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
