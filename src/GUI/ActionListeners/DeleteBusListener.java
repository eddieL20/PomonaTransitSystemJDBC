package GUI.ActionListeners;

import GUI.Frames.AdminControlPanelFrame;
import GUI.Panels.DeleteBusPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteBusListener implements ActionListener {
    // TODO: DeleteBusListener
    private DeleteBusPanel deleteBusPanel;

    public DeleteBusListener(DeleteBusPanel deleteBusPanel) {
        this.deleteBusPanel = deleteBusPanel;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Connection connection = AdminControlPanelFrame
                .getInstance()
                .getConnection();

        int busID = Integer.parseInt(
                deleteBusPanel
                        .getBusID()
                        .getText()
        );


        String query = "DELETE FROM Bus WHERE BusID = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, busID);
            statement.executeUpdate();

            deleteBusPanel.getResultsTextArea().setText(
                    AdminControlPanelFrame
                            .getInstance()
                            .displayAllBuses()
            );
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
