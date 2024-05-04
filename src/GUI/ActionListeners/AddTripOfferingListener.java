package GUI.ActionListeners;

import GUI.Frames.AdminControlPanelFrame;
import GUI.Panels.AddTripOfferingPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AddTripOfferingListener implements ActionListener {
    private AddTripOfferingPanel addTripOfferingPanel;

    public AddTripOfferingListener(AddTripOfferingPanel addTripOfferingPanel){
        this.addTripOfferingPanel = addTripOfferingPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Connection connection = AdminControlPanelFrame.getInstance().getConnection();

        int tripNumber =  Integer.parseInt(
                addTripOfferingPanel
                        .getTripNumber()
                        .getText()
        );
        Date date = Date.valueOf(
                addTripOfferingPanel
                        .getDate()
                        .getText()
        );


        Time scheduledStartTime = Time.valueOf(
                addTripOfferingPanel
                        .getScheduledStartTime()
                        .getText()
        );

        Time scheduledArrivalTime = Time.valueOf(
                addTripOfferingPanel
                        .getScheduledArrivalTime()
                        .getText()
        );

        String driverName = addTripOfferingPanel
                .getDriverName()
                .getText();

        int busID = Integer.parseInt(addTripOfferingPanel
                .getBusID()
                .getText()
        );

        String query = "INSERT INTO TripOffering " +
                "(TripNumber, Date, ScheduledStartTime, ScheduledArrivalTime, DriverName, BusID) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1, tripNumber);
            statement.setDate(2, date);
            statement.setTime(3, scheduledStartTime);
            statement.setTime(4, scheduledArrivalTime);
            statement.setString(5, driverName);
            statement.setInt(6, busID);
            statement.executeUpdate();

            addTripOfferingPanel.getResultsTextArea().setText(
                    AdminControlPanelFrame
                            .getInstance()
                            .displayAllTripOfferings()
            );

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
