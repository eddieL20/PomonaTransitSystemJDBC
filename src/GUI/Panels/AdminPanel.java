package GUI.Panels;

import javax.swing.*;
import java.awt.*;

public class AdminPanel extends JPanel {
    private final JButton displayTripScheduleButton = new JButton("Display Trip Schedule");
    private final JButton deleteTripOfferingButton = new JButton("Delete Trip Offering");
    private final JButton addTripOfferingButton = new JButton("Add Trip Offering");
    private final JButton changeDriverButton = new JButton("Change Driver");
    private final JButton changeBusButton = new JButton(("Change Bus"));
    private final JButton displayTripStopsButton = new JButton("Display Trip Stops");
    private final JButton displayWeeklyScheduleButton = new JButton("Display Weekly Schedule");
    private final JButton actualTripStopInfo = new JButton("Actual Trip Stop Info");
    private final JButton addDriverButton = new JButton("Add Driver");
    private final JButton addBusButton = new JButton("Add Bus");
    private final JButton deleteBusButton = new JButton("Delete Bus");

    public AdminPanel(){
        GridBagConstraints c = new GridBagConstraints();

        this.setSize(500, 200);
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.GRAY);
        this.setSize(400, 200);

        c.gridx = 0; // sets the column value
        c.gridy = 0; // sets the row value
        c.fill = GridBagConstraints.HORIZONTAL; // fills in the space available
        c.insets = new Insets(5, 5, 5, 50); // sets padding
        this.add(displayTripScheduleButton, c); // add component to panel with the constraints

        c.gridy = 1;
        this.add(deleteTripOfferingButton, c);

        c.gridy = 2;
        this.add(addTripOfferingButton, c);

        c.gridy = 3;
        this.add(changeDriverButton, c);

        c.gridy = 4;
        this.add(changeBusButton, c);

        c.gridy = 5;
        this.add(displayTripStopsButton, c);

        c.gridy = 6;
        this.add(displayWeeklyScheduleButton, c);

        c.gridy = 7;
        this.add(addDriverButton, c);

        c.gridy = 8;
        this.add(addBusButton, c);

        c.gridy = 9;
        this.add(deleteBusButton, c);

        c.gridy = 10;
        this.add(actualTripStopInfo, c);

    }

    public JButton getDisplayTripScheduleButton() {
        return displayTripScheduleButton;
    }

    public JButton getDeleteTripOfferingButton() {
        return deleteTripOfferingButton;
    }

    public JButton getAddTripOfferingButton() {
        return addTripOfferingButton;
    }

    public JButton getChangeDriverButton() {
        return changeDriverButton;
    }

    public JButton getChangeBusButton() {
        return changeBusButton;
    }

    public JButton getDisplayTripStopsButton() {
        return displayTripStopsButton;
    }

    public JButton getDisplayWeeklyScheduleButton() {
        return displayWeeklyScheduleButton;
    }

    public JButton getAddDriverButton() {
        return addDriverButton;
    }

    public JButton getDeleteBusButton() {
        return deleteBusButton;
    }

    public JButton getActualTripStopInfo() {
        return actualTripStopInfo;
    }

    public JButton getAddBusButton() {
        return addBusButton;
    }
}
