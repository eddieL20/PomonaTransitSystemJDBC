package GUI.ActionListeners;

import GUI.Frames.AdminControlPanelFrame;
import GUI.Panels.DeleteTripOfferingPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DeleteTripOfferingListener implements ActionListener {
    private final DeleteTripOfferingPanel delTripOfferingPanel;

    public DeleteTripOfferingListener(DeleteTripOfferingPanel tripOfferingPanel) {
        this.delTripOfferingPanel = tripOfferingPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Connection connection = AdminControlPanelFrame.getInstance().getConnection();

        // Get text from Trim Number text field
        int tripNumber = Integer.parseInt(
                delTripOfferingPanel
                        .getTripNumberTextField()
                        .getText()
        );

        // Get date from Date text field
        Date date = Date.valueOf(
                delTripOfferingPanel
                        .getDateTextField()
                        .getText()
        );

        // Get time from Scheduled Start Time text file
        Time scheduledStartTime = Time.valueOf(
                delTripOfferingPanel
                        .getScheduledStartTime()
                        .getText()
        );

        // Create formatted query string
        String query = "DELETE FROM TripOffering WHERE TripNumber = ? AND Date = ? AND ScheduledStartTime = ?";

        // Set all the values for the query
        try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1, tripNumber);
            statement.setDate(2, date);
            statement.setTime(3, scheduledStartTime);
            statement.executeUpdate();

            // Display all the Trip Offerings after the deletion;
            delTripOfferingPanel.getResultsTextArea().setText(
                    AdminControlPanelFrame
                            .getInstance()
                            .displayAllTripOfferings()
                            .toString()
            );
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }


    }
}
