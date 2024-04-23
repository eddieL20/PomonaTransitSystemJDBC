import java.sql.*;

public class PomonaTransitSystem {
    public void displayTripSchedule(
            Connection con,
            String startLocation,
            String destination,
            Date date
    ) throws SQLException {
        String query = "SELECT TOF.ScheduledStartTime, TOF.ScheduledArrivalTime, TOF.DriverName, TOF.BusID " +
                "FROM TripOffering TOF " +
                "JOIN Trip T ON TOF.TripNumber = T.TripNumber " +
                "WHERE T.StartLocationName = ? AND T.DestinationName = ? AND TOF.Date = ?";

        try(PreparedStatement statement = con.prepareStatement(query)){
            statement.setString(1, startLocation);
            statement.setString(2, destination);
            statement.setString(3, String.valueOf(date));
            try(ResultSet resultSet = statement.executeQuery()){
                while(resultSet.next()){
                    System.out.println("Scheduled Start Time: " + resultSet.getString("ScheduledStartTime"));
                    System.out.println("Scheduled Arrival Time: " + resultSet.getString("ScheduledArrivalTime"));
                    System.out.println("Driver Name: " + resultSet.getString("DriverName"));
                    System.out.println("Bus ID: " + resultSet.getString("BusID") + "\n");
                }
            }
        }
    }
}
