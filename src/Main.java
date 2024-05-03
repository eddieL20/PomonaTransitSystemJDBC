import GUI.Frames.AdminControlPanelFrame;

import java.sql.*;

public class Main {
    public static final String URL = "jdbc:mysql://localhost:3306/pomona_transit_system";
    public static final String USER = "user";
    public static final String PASSWORD = "password";


    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Create connection with Pomona Transit System credentials
            Connection connection = DriverManager.getConnection(
                    URL,
                    USER,
                    PASSWORD
            );
            AdminControlPanelFrame.getInstance();
            AdminControlPanelFrame.getInstance().setConnection(connection);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}