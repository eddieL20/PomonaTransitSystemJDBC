import java.sql.*;

public class PomonaTransitSystem {
    public void displayTripSchedule(
            Connection con,
            String startLocation,
            String destination,
            Date date
    ) throws SQLException {
        String query = "SELECT TO.*, TO.ScheduledStartTime, TO.ScheduledArrivalTime, TO.DriverName, TO.BusID " +
                "FROM TripOffering TO " +
                "JOIN Trip T ON TO.TripNumber = T.TripNumber " +
                "WHERE T.StartLocationName = ? AND T.DestinationName = ? AND TO.Date = ?";

        try(PreparedStatement statement = con.prepareStatement(query)){
            statement.setString(1, startLocation);
            statement.setString(2, destination);
            statement.setString(3, String.valueOf(date));
            try(ResultSet resultSet = statement.executeQuery()){
                // Process result set and display stops
            }
        }
    }
}
