package GUI.ActionListeners;

import GUI.Frames.AdminControlPanelFrame;
import GUI.Panels.AddDriverPanel;
import GUI.Panels.ChangeDriverForTripOfferingPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ChangeDriverForTripOfferingListener implements ActionListener {
    // TODO: ChangeDriverForTripOfferingListener
    private final ChangeDriverForTripOfferingPanel changeDriverForTripOfferingPanel;

    public ChangeDriverForTripOfferingListener(
            ChangeDriverForTripOfferingPanel changeDriverForTripOfferingPanel
    ) {
        this.changeDriverForTripOfferingPanel = changeDriverForTripOfferingPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Connection connection = AdminControlPanelFrame
                .getInstance()
                .getConnection();

        int tripNumber = Integer.parseInt(
                changeDriverForTripOfferingPanel
                        .getTripNumber()
                        .getText()
        );

        Date date = Date.valueOf(
                changeDriverForTripOfferingPanel
                        .getDate()
                        .getText()
        );

        Time scheduledStartTime = Time.valueOf(
                changeDriverForTripOfferingPanel
                        .getScheduledStartTime()
                        .getText()
        );

        String newDriverName = changeDriverForTripOfferingPanel
                        .getNewDriverName()
                        .getText();


        String query = "UPDATE TripOffering SET DriverName = ? WHERE TripNumber = ? AND Date = ? AND ScheduledStartTime = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1, newDriverName);
            statement.setInt(2, tripNumber);
            statement.setDate(3, date);
            statement.setTime(4, scheduledStartTime);
            statement.executeUpdate();

            changeDriverForTripOfferingPanel.getResultsTextArea().setText(
                    AdminControlPanelFrame
                            .getInstance()
                            .displayAllTripOfferings()
            );
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
