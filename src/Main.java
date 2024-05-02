import GUI.Frames.AdminControlPanelFrame;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Main {
    public static final String URL = "jdbc:mysql://localhost:3306/pomona_transit_system";
    public static final String USER = "user";
    public static final String PASSWORD = "password";


    public static void main(String[] args) {
        try {
            PomonaTransitSystem pts = new PomonaTransitSystem();
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Create connection with Pomona Transit System credentials
            Connection con = DriverManager.getConnection(
                    URL,
                    USER,
                    PASSWORD
            );
            AdminControlPanelFrame.getInstance();
            AdminControlPanelFrame.getInstance().setConnection(con);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}