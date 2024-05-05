package GUI.Frames;

import GUI.Panels.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminControlPanelFrame extends JFrame {

    private static AdminControlPanelFrame pointer;
    private Connection connection;

    public static AdminControlPanelFrame getInstance(){
        if (pointer == null){
            synchronized (AdminControlPanelFrame.class){
                if (pointer == null){
                    pointer = new AdminControlPanelFrame();
                }
            }
        }
        return pointer;
    }

    private AdminControlPanelFrame(){
        this.setTitle("Pomona Transit System");
        this.setLayout(new BorderLayout()); // border layout lets you add components to edges and center

        addWindowListener(new WindowAdapter() {
                              @Override
                              public void windowClosing(WindowEvent e) {
                                  try {
                                      connection.close();
                                      System.out.println("Connection Closed");
                                  } catch (SQLException ex) {
                                      throw new RuntimeException(ex);
                                  }
                              }
                          }
        );

        AdminPanel adminPanel = new AdminPanel();
        this.add(adminPanel, BorderLayout.CENTER);

        this.pack();
        this.setSize(720, 550);
        this.setVisible(true); // make frame  visible
        this.setResizable(false); // frame cannot be resized
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // terminate when closed

        adminPanel.getDisplayTripScheduleButton()
                .addActionListener(e -> {
                            PopUpFrame tsFrame = new PopUpFrame(new TripSchedulePanel());
                            tsFrame.setTitle("Trip Schedule Information");
                        }

                );

        adminPanel.getDeleteTripOfferingButton()
                .addActionListener(e -> {
                    PopUpFrame dtoFrame = new PopUpFrame(new DeleteTripOfferingPanel());
                    dtoFrame.setTitle("Delete Trip Offering");
                });

        adminPanel.getAddTripOfferingButton()
                .addActionListener(e -> {
                    PopUpFrame atoFrame = new PopUpFrame(new AddTripOfferingPanel());
                    atoFrame.setTitle("Add Trip Offering");
                });

        adminPanel.getAddDriverButton()
                .addActionListener(e -> {
                    PopUpFrame adoFrame = new PopUpFrame(new AddDriverPanel());
                    adoFrame.setTitle("Add Driver");
                });

        adminPanel.getChangeBusButton()
                .addActionListener(e -> {
                    PopUpFrame changeBusFrame = new PopUpFrame(new ChangeBusForTripOfferingPanel());
                    changeBusFrame.setTitle("Change Bus for Trip Offering");
                });

        adminPanel.getDeleteBusButton()
                .addActionListener(e -> {
                    PopUpFrame deleteBusFrame = new PopUpFrame(new DeleteBusPanel());
                    deleteBusFrame.setTitle("Delete Bus");
                });

        adminPanel.getChangeDriverButton()
                .addActionListener(e -> {
                    PopUpFrame changeDriverFrame = new PopUpFrame(new ChangeDriverForTripOfferingPanel());
                    changeDriverFrame.setTitle("Change Driver for Trip Offering");
                });

        adminPanel.getDisplayTripStopsButton()
                .addActionListener(e -> {
                    PopUpFrame dtsFrame = new PopUpFrame(new DisplayTripStopPanel());
                    dtsFrame.setTitle("Display Trip Stop");
                });

        adminPanel.getDisplayWeeklyScheduleButton()
                .addActionListener(e -> {
                    PopUpFrame weeklyFrame = new PopUpFrame(new DisplayWeeklySchedulePanel());
                    weeklyFrame.setTitle("Display Weekly Schedule");
                });

        adminPanel.getActualTripStopInfo()
                .addActionListener(e -> {
                    PopUpFrame actualTripStopFrame = new PopUpFrame(new ActualTripStopInfoPanel());
                    actualTripStopFrame.setTitle("Actual Trip Stop Info");
                });
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection){
        this.connection = connection;
    }

    public String displayAllTripOfferings(){
        String query = "SELECT * FROM TripOffering";

        try(PreparedStatement statement = connection.prepareStatement(query)){
            ResultSet resultSet = statement.executeQuery(query);
            StringWriter stringWriter = new StringWriter();
            while (resultSet.next()){
                stringWriter.write(String.format("%n====================================%n"));
                stringWriter.write(String.format("Trip Number: %s%n",  resultSet.getString("TripNumber")));
                stringWriter.write(String.format("Date: %s%n", resultSet.getString("Date")));
                stringWriter.write(String.format("Scheduled Start Time: %s%n",  resultSet.getString("ScheduledStartTime")));
                stringWriter.write(String.format("Scheduled Arrival Time: %s%n", resultSet.getString("ScheduledArrivalTime")));
                stringWriter.write(String.format("Driver: %s%n", resultSet.getString("DriverName")));
                stringWriter.write(String.format("Bus ID: %s%n", resultSet.getString("BusID")));
                stringWriter.write(String.format("====================================%n"));
            }
            return stringWriter.toString();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public String displayAllDrivers(){
        String query  = "SELECT * FROM Driver";

        try(PreparedStatement statement = connection.prepareStatement(query)){
            ResultSet resultSet = statement.executeQuery(query);
            StringWriter stringWriter = new StringWriter();
            while (resultSet.next()){
                stringWriter.write(String.format("%n====================================%n"));
                stringWriter.write(String.format("Driver: %s%n", resultSet.getString("DriverName")));
                stringWriter.write(String.format("Telephone Number: %s%n", resultSet.getString("DriverTelephoneNumber")));
                stringWriter.write(String.format("====================================%n"));
            }
            return stringWriter.toString();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String displayAllBuses(){
        String query = "SELECT * FROM Bus";
        try (PreparedStatement statement = connection.prepareStatement(query)){
            ResultSet resultSet = statement.executeQuery(query);
            StringWriter stringWriter = new StringWriter();
            while(resultSet.next()){
                stringWriter.write(String.format("%n====================================%n"));
                stringWriter.write(String.format("Bus ID: %s%n", resultSet.getString("BusID")));
                stringWriter.write(String.format("Model: %s%n", resultSet.getString("Model")));
                stringWriter.write(String.format("Year: %s%n", resultSet.getString("Year")));
                stringWriter.write(String.format("====================================%n"));
            }
            return stringWriter.toString();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public String displayAllActualTripInfo(){
        String query = "SELECT * FROM ActualTripStopInfo";
        try(PreparedStatement statement = connection.prepareStatement(query)){
            ResultSet resultSet = statement.executeQuery();
            StringWriter stringWriter = new StringWriter();
            while(resultSet.next()){
                stringWriter.write(String.format("%n====================================%n"));
                stringWriter.write(String.format("Trip Number: %s%n", resultSet.getString("TripNumber")));
                stringWriter.write(String.format("Date: %s%n", resultSet.getString("Date")));
                stringWriter.write(String.format("Scheduled Start Time: %s%n", resultSet.getString("ScheduledStartTime")));
                stringWriter.write(String.format("Stop Number: %s%n", resultSet.getString("StopNumber")));
                stringWriter.write(String.format("Scheduled Arrival Time: %s%n", resultSet.getString("ScheduledArrivalTime")));
                stringWriter.write(String.format("Actual Start Time: %s%n", resultSet.getString("ActualStartTime")));
                stringWriter.write(String.format("Actual Arrival Time: %s%n", resultSet.getString("ActualArrivalTime")));
                stringWriter.write(String.format("Number of Passengers In: %s%n", resultSet.getString("NumberOfPassengerIn")));
                stringWriter.write(String.format("Number of Passengers Out: %s%n", resultSet.getString("NumberOfPassengerOut")));
                stringWriter.write(String.format("====================================%n"));
            }
            return stringWriter.toString();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
