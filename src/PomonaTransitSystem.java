import java.sql.*;
import java.util.Scanner;

public class PomonaTransitSystem {
    public void displayTripSchedule(
            Connection con,
            String startLocation,
            String destination,
            Date date
    ) throws SQLException {


        String query = "SELECT TF.TripNumber, TF.ScheduledStartTime, TF.ScheduledArrivalTime, TF.DriverName, TF.BusID " +
                "FROM TripOffering TF " +
                "JOIN Trip T ON TF.TripNumber = T.TripNumber " +
                "WHERE T.StartLocationName = ? AND T.DestinationName = ? AND TF.Date = ?";

        try(PreparedStatement statement = con.prepareStatement(query)){
            statement.setString(1, startLocation);
            statement.setString(2, destination);
            statement.setDate(3, date);
            try(ResultSet resultSet = statement.executeQuery()){
                while(resultSet.next()){
                    System.out.println("\n====================================");
                    System.out.println("Trip Number: " + resultSet.getString("TripNumber"));
                    System.out.println("Scheduled Start Time: " + resultSet.getString("ScheduledStartTime"));
                    System.out.println("Scheduled Arrival Time: " + resultSet.getString("ScheduledArrivalTime"));
                    System.out.println("Driver Name: " + resultSet.getString("DriverName"));
                    System.out.println("Bus ID: " + resultSet.getString("BusID"));
                    System.out.println("====================================");
                }
            }
        }
    }

    // Delete Trip Offering
    public void deleteTripOffering(
            Connection connection,
            int tripNumber,
            Date date,
            Time scheduledStartTime
    ) throws SQLException {
        String query = "DELETE FROM TripOffering WHERE TripNumber = ? AND Date = ? AND ScheduledStartTime = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, tripNumber);
            statement.setDate(2, date);
            statement.setTime(3, scheduledStartTime);

            statement.executeUpdate();
        }
    }

    public void addTripOffering(
            Connection connection,
            int tripNumber,
            Date date,
            Time scheduledStartTime,
            Time scheduledArrivalTime,
            String driverName,
            int busID
    ) throws SQLException {
        String query = "INSERT INTO TripOffering (TripNumber, Date, ScheduledStartTime, ScheduledArrivalTime, DriverName, BusID) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, tripNumber);
            statement.setDate(2, date);
            statement.setTime(3, scheduledStartTime);
            statement.setTime(4, scheduledArrivalTime);
            statement.setString(5, driverName);
            statement.setInt(6, busID);

            statement.executeUpdate();
        }
    }
}
