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

        adminPanel
                .getDisplayTripScheduleButton()
                .addActionListener(e -> {
                            EditFrame tsFrame = new EditFrame(new TripSchedulePanel());
                            tsFrame.setTitle("Trip Schedule Information");
                        }

                );

        adminPanel
                .getDeleteTripOfferingButton()
                .addActionListener(e -> {
                    EditFrame dtoFrame = new EditFrame(new DeleteTripOfferingPanel());
                    dtoFrame.setTitle("Delete Trip Offering");
                });

        adminPanel
                .getAddTripOfferingButton()
                .addActionListener(e -> {
                    EditFrame atoFrame = new EditFrame(new AddTripOfferingPanel());
                    atoFrame.setTitle("Add Trip Offering");
                });

        adminPanel
                .getAddDriverButton()
                .addActionListener(e -> {
                    EditFrame adoFrame = new EditFrame(new AddDriverPanel());
                    adoFrame.setTitle("Add Driver");
                });

        adminPanel
                .getChangeBusButton()
                .addActionListener(e -> {
                    EditFrame changeBusFrame = new EditFrame(new ChangeBusForTripOfferingPanel());
                    changeBusFrame.setTitle("Change Bus for Trip Offering");
                });
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection){
        this.connection = connection;
    }

    public StringWriter displayAllTripOfferings(){
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
            return stringWriter;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public StringWriter displayAllDrivers(){
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
            return stringWriter;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
