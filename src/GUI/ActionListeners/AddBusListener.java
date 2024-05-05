package GUI.ActionListeners;

import GUI.Frames.AdminControlPanelFrame;
import GUI.Panels.AddBussPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddBusListener implements ActionListener {
    private final AddBussPanel addBussPanel;

    public AddBusListener(AddBussPanel addBussPanel) {
        this.addBussPanel = addBussPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Connection connection = AdminControlPanelFrame
                .getInstance()
                .getConnection();

        int busID = Integer.parseInt(
                addBussPanel
                        .getBudID()
                        .getText()
        );

        String model = addBussPanel
                .getModel()
                .getText();

        int year = Integer.parseInt(
                addBussPanel
                        .getYear()
                        .getText()
        );

        String query = "INSERT INTO Bus (BusID, Model, Year) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1, busID);
            statement.setString(2, model);
            statement.setInt(3, year);
            statement.executeUpdate();

            addBussPanel.getResultsTextArea().setText(
                    AdminControlPanelFrame
                            .getInstance()
                            .displayAllBuses()
            );
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
