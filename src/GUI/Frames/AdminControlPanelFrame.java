package GUI.Frames;

import GUI.ActionListeners.TripScheduleViewListener;
import GUI.Panels.AdminPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
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

        TripScheduleViewListener dsvListener = new TripScheduleViewListener();

        adminPanel.getDisplayTripScheduleButton().addActionListener(dsvListener);

    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection){
        this.connection = connection;
    }
}
