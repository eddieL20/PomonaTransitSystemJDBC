import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException, IOException, ParseException {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(new FileReader("src/config.json"));

        JSONObject dbObject = (JSONObject) jsonObject.get("db");
        String url = (String) dbObject.get("url");
        String username = (String) dbObject.get("username");
        String password = (String) dbObject.get("password");

        String startLocation = "New York City";
        String destination = "Los Angeles";
        Date date = new Date(2024 - 1900, 4-1,22);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, username, password);

            Statement st = con.createStatement();

            PomonaTransitSystem pts = new PomonaTransitSystem();

            pts.displayTripSchedule(con, startLocation, destination, date);

            st.close();
            con.close();

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}