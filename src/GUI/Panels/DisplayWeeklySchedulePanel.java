package GUI.Panels;

import GUI.ActionListeners.DisplayWeeklyScheduleListener;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;

public class DisplayWeeklySchedulePanel extends JPanel {
    private final JTextField driverName = new JTextField(20);
    private final JTextField startDate = new JTextField(20);
    private final JTextField endDate = new JTextField(20);
    private final JButton submitButton = new JButton("Submit");
    private final TextArea resultsTextArea = new TextArea();

    public DisplayWeeklySchedulePanel(){
        // TODO: DisplayWeeklySchedulePanel Constructor
        GridBagConstraints c = new GridBagConstraints();
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.GRAY);

        c.gridx = 0;
        c.gridy = 0;
        this.add(new JLabel("Driver Name: "), c);
        c.gridx = 1;
        this.add(driverName, c);

        c.gridx = 0;
        c.gridy = 1;
        this.add(new JLabel("Start Date: "), c);
        c.gridx = 1;
        this.add(startDate, c);

        c.gridx = 0;
        c.gridy = 2;
        this.add(new JLabel("End Date: "), c);
        c.gridx = 1;
        this.add(endDate, c);

        c.gridy = 3;
        this.add(submitButton, c);
        submitButton.addActionListener(
                new DisplayWeeklyScheduleListener(this)
        );

        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 2;
        resultsTextArea.setPreferredSize(new Dimension(475, 550));
        JScrollPane scrollPane = new JScrollPane(resultsTextArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(scrollPane, c);
    }

    public JTextField getDriverName() {
        return driverName;
    }

    public JTextField getStartDate() {
        return startDate;
    }

    public JTextField getEndDate() {
        return endDate;
    }

    public JButton getSubmitButton() {
        return submitButton;
    }

    public TextArea getResultsTextArea() {
        return resultsTextArea;
    }
}
