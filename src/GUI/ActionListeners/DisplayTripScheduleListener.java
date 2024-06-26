package GUI.ActionListeners;

import GUI.Frames.AdminControlPanelFrame;
import GUI.Panels.AdminPanel;
import GUI.Panels.TripSchedulePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.StringWriter;
import java.sql.*;

public class DisplayTripScheduleListener implements ActionListener {

    private final TripSchedulePanel tripSchedulePanel;

    public DisplayTripScheduleListener(TripSchedulePanel tripSchedulePanel){
        this.tripSchedulePanel = tripSchedulePanel;
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        String startLocation = tripSchedulePanel.getStartLocationTextField().getText();
        String destination = tripSchedulePanel.getDestinationTextField().getText();
        Date date = Date.valueOf(tripSchedulePanel.getDateTextField().getText());
        Connection connection = AdminControlPanelFrame.getInstance().getConnection();

        String query = "SELECT TF.TripNumber, TF.ScheduledStartTime, TF.ScheduledArrivalTime, TF.DriverName, TF.BusID " +
                "FROM TripOffering TF " +
                "JOIN Trip T ON TF.TripNumber = T.TripNumber " +
                "WHERE T.StartLocationName = ? AND T.DestinationName = ? AND TF.Date = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1, startLocation);
            statement.setString(2, destination);
            statement.setDate(3, date);
            try (ResultSet resultSet = statement.executeQuery()){
                StringWriter stringWriter = new StringWriter();
                while(resultSet.next()){
                    stringWriter.write(String.format("%n====================================%n"));
                    stringWriter.write(String.format("Start Location: %s%n", startLocation));
                    stringWriter.write(String.format("Destination: %s%n", destination));
                    stringWriter.write(String.format("Date: %s%n", date.toString()));
                    stringWriter.write(String.format("Trip Number: %s%n", resultSet.getString("TripNumber")));
                    stringWriter.write(String.format("Scheduled Start Time: %s%n", resultSet.getString("ScheduledStartTime")));
                    stringWriter.write(String.format("Scheduled Arrival Time: %s%n", resultSet.getString("ScheduledArrivalTime")));
                    stringWriter.write(String.format("Driver Name: %s%n", resultSet.getString("DriverName")));
                    stringWriter.write(String.format("Bus ID: %s%n", resultSet.getString("BusID")));
                    stringWriter.write(String.format("====================================%n"));
                }
                tripSchedulePanel.getResultsTextArea().setText(stringWriter.toString());
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }
}
