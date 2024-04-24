import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, IOException, ParseException {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(new FileReader("src/config.json"));

        JSONObject dbObject = (JSONObject) jsonObject.get("db");
        String url = (String) dbObject.get("url");
        String username = (String) dbObject.get("username");
        String password = (String) dbObject.get("password");

        int choice = -1;
        char resume = 'Y';
        Date date;
        Time scheduledStartTime = null;
        String date_str;
        String time_str;
        Scanner scanner = new Scanner(System.in);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, username, password);
            Statement st = con.createStatement();

            PomonaTransitSystem pts = new PomonaTransitSystem();

            do {
                System.out.println("1: Display Trip Schedule");
                System.out.println("2: Delete Trip Offering");
                System.out.println("3: Add Trip Offering");
                System.out.println("4: Change Driver for Trip Offering");
                System.out.println("5: Change Bus for Trip Offering");
                System.out.println("6: Display Trip Stops");
                System.out.println("7: Display Weekly Schedule");
                System.out.println("8: Add Driver");
                System.out.println("0: Quit");

                if(scanner.hasNextInt()){
                    choice = scanner.nextInt();
                    scanner.nextLine();

                    switch(choice){
                        case 0:
                            resume = 'N';
                            break;
                        case 1:
                            System.out.print("\nEnter Start Location: ");
                            String startLocation = scanner.nextLine();
                            System.out.print("Enter Destination: ");
                            String destination = scanner.nextLine();
                            System.out.print("Enter Date (yyyy-mm-dd): ");
                            date_str = scanner.next();
                            date = Date.valueOf(date_str);

                            pts.displayTripSchedule(
                                    con,
                                    startLocation,
                                    destination,
                                    date
                            );

                            System.out.print("\nContinue? (Y)Yes, (N)Any other key: ");
                            resume = scanner.next().charAt(0);
                            String r = String.valueOf(resume).toUpperCase();
                            resume = r.charAt(0);
                            break;
                        case 2:
                            System.out.print("\nEnter Trip Number: ");
                            int tripNumber = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("Enter Date (yyyy-mm-dd): ");

                            date_str = scanner.nextLine();
                            date = Date.valueOf(date_str);

                            System.out.print("Enter Scheduled Start Time: ");
                            time_str = scanner.nextLine();
                            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                            try{
                                java.util.Date date1 = sdf.parse(time_str);
                                scheduledStartTime = new Time(date1.getTime());
                            } catch (java.text.ParseException e){
                                System.out.println("Error parsing time: " + e.getMessage());
                            }

                            pts.deleteTripOffering(
                                    con,
                                    tripNumber,
                                    date,
                                    scheduledStartTime
                            );
                        default:
                            break;
                    }
                } else {
                    System.out.println("Wrong input, try again...\n");
                    scanner.nextLine();
                }
            } while(resume == 'Y');

            System.out.println("Goodbye...");

            st.close();
            con.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}