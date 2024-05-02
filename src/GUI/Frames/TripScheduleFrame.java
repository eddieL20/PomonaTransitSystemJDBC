package GUI.Frames;


import GUI.Panels.TripSchedulePanel;

import javax.swing.*;
import java.awt.*;

public class TripScheduleFrame extends JFrame {

    public  TripScheduleFrame(){
        this.setTitle("Trip Schedule");
        this.setLayout(new BorderLayout()); // border layout lets you add components to edges and center

        TripSchedulePanel tripSchedulePanel = new TripSchedulePanel();

        this.add(tripSchedulePanel, BorderLayout.CENTER);

        this.pack();
        this.setSize(720, 550);
        this.setVisible(true); // make frame  visible
        this.setResizable(false); // frame cannot be resized

    }


}
