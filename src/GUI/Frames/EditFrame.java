package GUI.Frames;


import GUI.Panels.TripSchedulePanel;

import javax.swing.*;
import java.awt.*;

public class EditFrame extends JFrame {

    public EditFrame(JPanel panel){
        this.setTitle("Trip Schedule");
        this.setLayout(new BorderLayout()); // border layout lets you add components to edges and center

        this.add(panel, BorderLayout.CENTER);

        this.pack();
        this.setSize(720, 850);
        this.setVisible(true); // make frame  visible

    }


}
