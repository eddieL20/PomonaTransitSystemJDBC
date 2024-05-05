package GUI.ActionListeners;

import GUI.Frames.AdminControlPanelFrame;
import GUI.Panels.ActualTripStopInfoPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ActualTripStopInfoListener implements ActionListener {
    private final ActualTripStopInfoPanel actualTripStopInfoPanel;

    public ActualTripStopInfoListener(ActualTripStopInfoPanel actualTripStopInfoPanel){
        this.actualTripStopInfoPanel = actualTripStopInfoPanel;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Connection connection = AdminControlPanelFrame
                .getInstance()
                .getConnection();

        int tripNumber = Integer.parseInt(
                actualTripStopInfoPanel
                        .getTripNumber()
                        .getText()
        );

        Date date = Date.valueOf(
                actualTripStopInfoPanel
                        .getDate()
                        .getText()
        );


        Time scheduledStartTime = Time.valueOf(
                actualTripStopInfoPanel
                        .getScheduledStartTime()
                        .getText()
        );

        int stopNumber = Integer.parseInt(
                actualTripStopInfoPanel
                        .getStopNumber()
                        .getText()
        );


        Time scheduledArrivalTime = Time.valueOf(
                actualTripStopInfoPanel
                        .getScheduledArrivalTime()
                        .getText()
        );


        Time actualStartTime = Time.valueOf(
                actualTripStopInfoPanel
                        .getActualStartTime()
                        .getText()
        );


        Time actualArrivalTime = Time.valueOf(
                actualTripStopInfoPanel
                        .getActualArrivalTime()
                        .getText()
        );

        int numberOfPassengersIn = Integer.parseInt(
                actualTripStopInfoPanel
                        .getNumberOfPassengersIn()
                        .getText()
        );

        int numberOfPassengersOut = Integer.parseInt(
                actualTripStopInfoPanel
                        .getNumberOfPassengersOut()
                        .getText()
        );

        String query = "INSERT INTO ActualTripStopInfo " +
                "(TripNumber, Date, ScheduledStartTime, StopNumber, ScheduledArrivalTime, ActualStartTime, " +
                "ActualArrivalTime, NumberOfPassengerIn, NumberOfPassengerOut) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1, tripNumber);
            statement.setDate(2, date);
            statement.setTime(3, scheduledStartTime);
            statement.setInt(4,stopNumber);
            statement.setTime(5, scheduledArrivalTime);
            statement.setTime(6, actualStartTime);
            statement.setTime(7, actualArrivalTime);
            statement.setInt(8, numberOfPassengersIn);
            statement.setInt(9, numberOfPassengersOut);
            statement.executeUpdate();

            actualTripStopInfoPanel.getResultsTextArea().setText(
                    AdminControlPanelFrame
                            .getInstance()
                            .displayAllActualTripInfo()
            );
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}