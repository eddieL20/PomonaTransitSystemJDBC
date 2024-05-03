package GUI.ActionListeners;

import GUI.Frames.AdminControlPanelFrame;
import GUI.Panels.ChangeBusForTripOfferingPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ChangeBusForTripOfferingListener implements ActionListener {
    // TODO: ChangeBusForTripOfferingListener
    private ChangeBusForTripOfferingPanel changeBusForTripOfferingPanel;

    public ChangeBusForTripOfferingListener(
            ChangeBusForTripOfferingPanel changeBusForTripOfferingPanel
    ) {
        this.changeBusForTripOfferingPanel = changeBusForTripOfferingPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Connection connection = AdminControlPanelFrame
                .getInstance()
                .getConnection();

        int tripNumber = Integer.parseInt(
                changeBusForTripOfferingPanel
                .getTripNumber()
                .getText()
        );

        Date date = Date.valueOf(
                changeBusForTripOfferingPanel
                        .getDate()
                        .getText()
        );

        Time scheduledStartTime = Time.valueOf(
                changeBusForTripOfferingPanel
                        .getScheduledStartTime()
                        .getText()
        );

        int newBusID = Integer.parseInt(
                changeBusForTripOfferingPanel
                        .getNewBusID()
                        .getText()
        );

        String query = "UPDATE TripOffering SET BusID = ? WHERE TripNumber = ? AND Date = ? AND ScheduledStartTime = ?";

        try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1, newBusID);
            statement.setInt(2, tripNumber);
            statement.setDate(3, date);
            statement.setTime(4, scheduledStartTime);
            statement.executeUpdate();

            changeBusForTripOfferingPanel.getResultsTextArea().setText(
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
