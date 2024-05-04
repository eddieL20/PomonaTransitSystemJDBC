package GUI.ActionListeners;

import GUI.Frames.AdminControlPanelFrame;
import GUI.Panels.DisplayTripStopPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DisplayTripStopsListener implements ActionListener {
    // TODO: DisplayTripStopsListener
    private final DisplayTripStopPanel displayTripStopPanel;

    public DisplayTripStopsListener(DisplayTripStopPanel displayTripStopPanel) {
        this.displayTripStopPanel = displayTripStopPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Connection connection = AdminControlPanelFrame
                        .getInstance()
                        .getConnection();

        int tripNumber = Integer.parseInt(
                displayTripStopPanel
                .getTripNumber()
                .getText()
        );

        String query = "SELECT * FROM TripStopInfo WHERE TripNumber = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1, tripNumber);
            try(ResultSet resultSet = statement.executeQuery()){

                StringWriter stringWriter = new StringWriter();
                while (resultSet.next()){
                    stringWriter.write(String.format("%n====================================%n"));
                    stringWriter.write(String.format("Stop Number: %s%n", resultSet.getString("StopNumber")));
                    stringWriter.write(String.format("Sequence Number: %s%n", resultSet.getString("SequenceNumber")));
                    stringWriter.write(String.format("Drive Time: %s%n", resultSet.getString("DrivingTime")));
                    stringWriter.write(String.format("====================================%n"));
                }
                displayTripStopPanel.getResultsTextArea().setText(
                        stringWriter.toString()
                );
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
