package GUI.ActionListeners;

import GUI.Frames.AdminControlPanelFrame;
import GUI.Panels.DisplayWeeklySchedulePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.StringWriter;
import java.sql.*;

public class DisplayWeeklyScheduleListener implements ActionListener {
    // TODO: DisplayWeeklyScheduleListener
    private final DisplayWeeklySchedulePanel displayWeeklySchedulePanel;

    public DisplayWeeklyScheduleListener(DisplayWeeklySchedulePanel displayWeeklySchedulePanel) {
        this.displayWeeklySchedulePanel = displayWeeklySchedulePanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Connection connection = AdminControlPanelFrame
                .getInstance()
                .getConnection();

        String driverName = displayWeeklySchedulePanel
                .getDriverName()
                .getText();

        Date startDate = Date.valueOf(displayWeeklySchedulePanel
                .getStartDate()
                .getText()
        );

        Date endDate = Date.valueOf(
                displayWeeklySchedulePanel
                        .getEndDate()
                        .getText()
        );

        String query = "SELECT TF.ScheduledStartTime, TF.ScheduledArrivalTime, T.StartLocationName, T.DestinationName " +
                "FROM TripOffering TF " +
                "JOIN Trip T ON TF.TripNumber = T.TripNumber " +
                "WHERE TF.DriverName = ? AND TF.Date BETWEEN ? AND ?";

        try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1, driverName);
            statement.setDate(2, startDate);
            statement.setDate(3, endDate);
            try(ResultSet resultSet = statement.executeQuery()){

                StringWriter stringWriter = new StringWriter();
                while (resultSet.next()){
                    stringWriter.write(String.format("%n====================================%n"));
                    stringWriter.write(String.format("Start Location: %s%n", resultSet.getString("StartLocationName")));
                    stringWriter.write(String.format("Destination: %s%n", resultSet.getString("DestinationName")));
                    stringWriter.write(String.format("Scheduled Start Time: %s%n", resultSet.getString("ScheduledStartTime")));
                    stringWriter.write(String.format("Scheduled Arrival Time: %s%n", resultSet.getString("ScheduledArrivalTime")));
                    stringWriter.write(String.format("====================================%n"));
                }
                displayWeeklySchedulePanel.getResultsTextArea().setText(
                        stringWriter.toString()
                );
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
