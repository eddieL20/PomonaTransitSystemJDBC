import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Main {
    public static int choice = -1;
    public static int tripNumber;
    public static int busID;
    public static char resume;
    public static Date date;
    public static Date endDate;
    public static Time scheduledStartTime = null;
    public static Time scheduledArrivalTime = null;
    public static String date_str;
    public static String time_str;
    public static String driverName;
    public static String startLocation;
    public static String destination;
    public static String telephoneNumber;
    public static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    public static Scanner scanner = new Scanner(System.in);
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

            do {
                System.out.println("1: Display Trip Schedule");
                System.out.println("2: Delete Trip Offering");
                System.out.println("3: Add Trip Offering");
                System.out.println("4: Change Driver for Trip Offering");
                System.out.println("5: Change Bus for Trip Offering");
                System.out.println("6: Display Trip Stops");
                System.out.println("7: Display Weekly Schedule");
                System.out.println("8: Add Driver");
                System.out.println("9: Delete Bus");
                System.out.println("0: Quit");

                // If integer continue, else throw exception
                if(scanner.hasNextInt()){
                    choice = scanner.nextInt();
                    scanner.nextLine();

                    switch(choice){
                        case 0:
                            resume = 'N';
                            break;
                        case 1:
                            setDisplayTripOfferingInfo();
                            pts.displayTripSchedule(
                                    con,
                                    startLocation,
                                    destination,
                                    date
                            );
                            continueQuestion();
                            break;
                        case 2:
                            pts.displayAllTripOfferings(con);
                            setDeleteTripOfferingInfo();
                            pts.deleteTripOffering(
                                    con,
                                    tripNumber,
                                    date,
                                    scheduledStartTime
                            );
                            pts.displayAllTripOfferings(con);
                            continueQuestion();
                            break;
                        case 3:
                            pts.displayAllTripOfferings(con);
                            setAddTripOfferingInfo();
                            pts.addTripOffering(
                                    con,
                                    tripNumber,
                                    date,
                                    scheduledStartTime,
                                    scheduledArrivalTime,
                                    driverName,
                                    busID
                            );
                            pts.displayAllTripOfferings(con);
                            continueQuestion();
                            break;
                        case 4:
                            pts.displayAllTripOfferings(con);
                            setChangeDriverInfo();
                            pts.changeDriverForTripOffering(
                                    con,
                                    tripNumber,
                                    date,
                                    scheduledStartTime,
                                    driverName
                            );
                            pts.displayAllTripOfferings(con);
                            continueQuestion();
                            break;
                        case 5:
                            pts.displayAllTripOfferings(con);
                            setChangeBusForTripOfferingInfo();
                            pts.changeBusForTripOffering(
                                    con,
                                    tripNumber,
                                    date,
                                    scheduledStartTime,
                                    busID
                            );
                            pts.displayAllTripOfferings(con);
                            continueQuestion();
                            break;
                        case 6:
                            System.out.print("\nEnter Trip Number: ");
                            tripNumber = scanner.nextInt();
                            scanner.nextLine();

                            pts.displayTripStops(
                                    con,
                                    tripNumber
                            );

                            continueQuestion();
                            break;
                        case 7:
                            setWeeklyScheduleInfo();
                            pts.displayWeeklySchedule(
                                    con,
                                    driverName,
                                    date,
                                    endDate
                            );
                            continueQuestion();
                            break;
                        case 8:
                            pts.displayAllDrivers(con);
                            setDriverInfo();
                            pts.addDriver(
                                    con,
                                    driverName,
                                    telephoneNumber
                            );
                            pts.displayAllDrivers(con);
                            continueQuestion();
                            break;
                        case 9:
                            pts.displayAllBuses(con);
                            setDeleteBusInfo();
                            pts.deleteBus(
                                    con,
                                    busID
                            );
                            pts.displayAllBuses(con);
                        default:
                            break;
                    }
                } else {
                    System.out.println("Wrong input, try again...\n");
                    scanner.nextLine();
                }
            } while(resume == 'Y');

            System.out.println("Goodbye...");

            con.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static void setDisplayTripOfferingInfo(){
        System.out.print("\nEnter Start Location: ");
        startLocation = scanner.nextLine();

        System.out.print("Enter Destination: ");
        destination = scanner.nextLine();

        System.out.print("Enter Date (yyyy-mm-dd): ");
        date_str = scanner.next();
        date = Date.valueOf(date_str);
    }

    // Used after an action on the database
    public static void continueQuestion(){
        System.out.print("\nContinue? (Y/y)Yes, (Any Key)No: ");
        resume = scanner.next().charAt(0);
        String r = String.valueOf(resume).toUpperCase();
        resume = r.charAt(0);
    }

    public static void setDeleteTripOfferingInfo(){
        // Set Trip Number
        System.out.print("\nEnter Trip Number: ");
        tripNumber = scanner.nextInt();
        scanner.nextLine();

        // Set Date
        System.out.print("Enter Date (yyyy-mm-dd): ");
        date_str = scanner.nextLine();
        date = Date.valueOf(date_str);

        // Set Scheduled Start Time
        System.out.print("Enter Scheduled Start Time: ");
        time_str = scanner.nextLine();

        try{
            java.util.Date date1 = sdf.parse(time_str);
            scheduledStartTime = new Time(date1.getTime());
        } catch (java.text.ParseException e){
            System.out.println("Error parsing time: " + e.getMessage());
        }

    }

    public static void setAddTripOfferingInfo(){
        // Set Trip Number
        System.out.print("\nEnter Trip Number: ");
        tripNumber = scanner.nextInt();
        scanner.nextLine();

        // Set Date
        System.out.print("Enter Date (yyyy-mm-dd): ");
        date_str = scanner.nextLine();
        date = Date.valueOf(date_str);

        // Set Scheduled Start Time
        System.out.print("Enter Scheduled Start Time: ");
        time_str = scanner.nextLine();

        try{
            java.util.Date date1 = sdf.parse(time_str);
            scheduledStartTime = new Time(date1.getTime());
        } catch (java.text.ParseException e){
            System.out.println("Error parsing time: " + e.getMessage());
        }

        // Set Scheduled Arrival Time
        System.out.print("Enter Scheduled Arrival Time: ");
        time_str = scanner.nextLine();
        sdf = new SimpleDateFormat("HH:mm:ss");

        try{
            java.util.Date date1 = sdf.parse(time_str);
            scheduledArrivalTime = new Time(date1.getTime());
        } catch (java.text.ParseException e){
            System.out.println("Error parsing time: " + e.getMessage());
        }

        // Set Driver's Name
        System.out.print("Enter Driver's Name: ");
        driverName = scanner.nextLine();

        // Set Bus ID
        System.out.print("Enter Bus ID: ");
        busID = scanner.nextInt();
        scanner.nextLine();
    }

    public static void setChangeDriverInfo(){
        // Set Trip Number
        tripNumber = scanner.nextInt();
        scanner.nextLine();

        // Set Date
        System.out.print("Enter Date (yyyy-mm-dd): ");
        date_str = scanner.nextLine();
        date = Date.valueOf(date_str);

        // Set Scheduled Start Time
        System.out.print("Enter Scheduled Start Time: ");
        time_str = scanner.nextLine();

        try{
            java.util.Date date1 = sdf.parse(time_str);
            scheduledStartTime = new Time(date1.getTime());
        } catch (java.text.ParseException e){
            System.out.println("Error parsing time: " + e.getMessage());
        }

        // Set New Driver's Name
        System.out.print("Enter Driver's Name: ");
        driverName = scanner.nextLine();
    }

    public static void setChangeBusForTripOfferingInfo(){
        // Set Trip Number
        System.out.print("\nEnter Trip Number: ");
        tripNumber = scanner.nextInt();
        scanner.nextLine();

        // Set Date
        System.out.print("Enter Date (yyyy-mm-dd): ");
        date_str = scanner.nextLine();
        date = Date.valueOf(date_str);

        // Set Scheduled Start Time
        System.out.print("Enter Scheduled Start Time: ");
        time_str = scanner.nextLine();

        try{
            java.util.Date date1 = sdf.parse(time_str);
            scheduledStartTime = new Time(date1.getTime());
        } catch (java.text.ParseException e){
            System.out.println("Error parsing time: " + e.getMessage());
        }

        // Set Bus ID
        System.out.print("Enter Bus ID: ");
        busID = scanner.nextInt();
        scanner.nextLine();
    }

    public static void setWeeklyScheduleInfo(){
        // Set Driver's Name
        System.out.print("Enter Driver's Name: ");
        driverName = scanner.nextLine();

        // Set Start Date
        System.out.print("Enter Date (yyyy-mm-dd): ");
        date_str = scanner.nextLine();
        date = Date.valueOf(date_str);

        // Set Start Date
        System.out.print("Enter Date (yyyy-mm-dd): ");
        date_str = scanner.nextLine();
        endDate = Date.valueOf(date_str);
    }

    public static void setDriverInfo(){
        // Set New Driver's Name
        System.out.print("Enter Driver's Name: ");
        driverName = scanner.nextLine();

        // Set Telephone Number
        System.out.print("Enter Telephone Number : ");
        telephoneNumber = scanner.nextLine();

    }
    public static void setDeleteBusInfo(){
        // Set Bus ID
        System.out.print("Enter Bus ID: ");
        busID = scanner.nextInt();
        scanner.nextLine();
    }
}