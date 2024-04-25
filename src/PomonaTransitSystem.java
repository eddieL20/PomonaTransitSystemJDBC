import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class PomonaTransitSystem {

    private final String url;
    private final String username;
    private final String password;
    public PomonaTransitSystem() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(new FileReader("src/config.json"));

        JSONObject dbObject = (JSONObject) jsonObject.get("db");
        url = (String) dbObject.get("url");
        username = (String) dbObject.get("username");
        password = (String) dbObject.get("password");
    }
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

    public void changeDriverForTripOffering(
            Connection connection,
            int tripNumber,
            Date date,
            Time scheduledStartTime,
            String newDriverName
    ) throws SQLException {
        String query = "UPDATE TripOffering SET DriverName = ? WHERE TripNumber = ? AND Date = ? AND ScheduledStartTime = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, newDriverName);
            statement.setInt(2, tripNumber);
            statement.setDate(3, date);
            statement.setTime(4, scheduledStartTime);

            statement.executeUpdate();
        }
    }

    public void changeBusForTripOffering(
            Connection connection,
            int tripNumber,
            Date date,
            Time scheduledStartTime,
            int newBusID
    ) throws SQLException {
        String query = "UPDATE TripOffering SET BusID = ? WHERE TripNumber = ? AND Date = ? AND ScheduledStartTime = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, newBusID);
            statement.setInt(2, tripNumber);
            statement.setDate(3, date);
            statement.setTime(4, scheduledStartTime);

            statement.executeUpdate();
        }
    }

    public void displayTripStops(
            Connection connection,
            int tripNumber
    ) throws SQLException {
        String query = "SELECT * FROM TripStopInfo WHERE TripNumber = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, tripNumber);

            try (ResultSet resultSet = statement.executeQuery()) {
                while(resultSet.next()){
                    System.out.println("\n====================================");
                    System.out.println("Stop Number: " + resultSet.getString("StopNumber"));
                    System.out.println("Sequence Number: " + resultSet.getString("SequenceNumber"));
                    System.out.println("Drive Time: " + resultSet.getString("DrivingTime"));
                    System.out.println("====================================");
                }
            }
        }
    }

    public void displayWeeklySchedule(
            Connection connection,
            String driverName,
            Date startDate,
            Date endDate
    ) throws SQLException {
        String query = "SELECT TF.ScheduledStartTime, TF.ScheduledArrivalTime, T.StartLocationName, T.DestinationName " +
                "FROM TripOffering TF " +
                "JOIN Trip T ON TF.TripNumber = T.TripNumber " +
                "WHERE TF.DriverName = ? AND TF.Date BETWEEN ? AND ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, driverName);
            statement.setDate(2, startDate);
            statement.setDate(3, endDate);

            try (ResultSet resultSet = statement.executeQuery()) {
                while(resultSet.next()){
                    System.out.println("\n====================================");
                    System.out.println("Start Location: " + resultSet.getString("StartLocationName"));
                    System.out.println("Destination: " + resultSet.getString("DestinationName"));
                    System.out.println("Scheduled Start Time: " + resultSet.getString("ScheduledStartTime"));
                    System.out.println("Scheduled Arrival Time: " + resultSet.getString("ScheduledArrivalTime"));
                    System.out.println("====================================");
                }
            }
        }
    }

    public void addDriver(Connection connection,
                          String driverName,
                          String driverTelephoneNumber
    ) throws SQLException {
        String query = "INSERT INTO Driver (DriverName, DriverTelephoneNumber) VALUES (?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, driverName);
            statement.setString(2, driverTelephoneNumber);
            statement.executeUpdate();
            System.out.println("Driver added successfully.");
        }
    }

    public void deleteBus(
            Connection connection,
            int busID
    ) throws SQLException {
        String query = "DELETE FROM Bus WHERE BusID = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, busID);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Bus with ID " + busID + " deleted successfully.");
            } else {
                System.out.println("No bus found with ID " + busID + ".");
            }
        }
    }

    public void displayAllBuses(Connection connection) throws SQLException {
        String query = "SELECT * FROM Bus";
        try (PreparedStatement statement = connection.prepareStatement(query)){
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                System.out.println("\n====================================");
                System.out.println("Bus ID: " + resultSet.getString("BusID"));
                System.out.println("Model: " + resultSet.getString("Model"));
                System.out.println("Year: " + resultSet.getString("Year"));
                System.out.println("====================================");
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        System.out.println();
    }

    public void displayAllTripOfferings(Connection connection){
        String query = "SELECT * From TripOffering";
        try (PreparedStatement statement = connection.prepareStatement(query)){
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                System.out.println("\n====================================");
                System.out.println("Trip Number: " + resultSet.getString("TripNumber"));
                System.out.println("Date: " + resultSet.getString("Date"));
                System.out.println("Scheduled Start Time: " + resultSet.getString("ScheduledStartTime"));
                System.out.println("Scheduled Arrival Time: " + resultSet.getString("ScheduledArrivalTime"));
                System.out.println("Driver: " + resultSet.getString("DriverName"));
                System.out.println("Bus ID: " + resultSet.getString("BusID"));
                System.out.println("====================================");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println();
    }

    public void displayAllDrivers(Connection connection){
        String query = "SELECT * FROM Driver";
        try (PreparedStatement statement = connection.prepareStatement(query)){
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                System.out.println("\n====================================");
                System.out.println("Driver: " + resultSet.getString("Driver"));
                System.out.println("Telephone Number: " + resultSet.getString("TelephoneNumber"));
                System.out.println("====================================");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println();
    }
    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
