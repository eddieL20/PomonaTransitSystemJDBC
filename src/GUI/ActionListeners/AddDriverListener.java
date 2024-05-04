package GUI.ActionListeners;

import GUI.Frames.AdminControlPanelFrame;
import GUI.Panels.AddDriverPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddDriverListener implements ActionListener {
    private final AddDriverPanel addDriverPanel;

    public AddDriverListener(AddDriverPanel addDriverPanel) {
        this.addDriverPanel = addDriverPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Connection connection = AdminControlPanelFrame
                .getInstance()
                .getConnection();

        String driverName = addDriverPanel
                .getDriverName()
                .getText();

        String telephoneNumber = addDriverPanel
                .getTelephoneNumber()
                .getText();

        String query = "INSERT INTO Driver (DriverName, DriverTelephoneNumber) VALUES (?, ?)";

        try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1, driverName);
            statement.setString(2, telephoneNumber);
            statement.executeUpdate();

            addDriverPanel.getResultsTextArea().setText(
                    AdminControlPanelFrame
                            .getInstance()
                            .displayAllDrivers()
            );
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
