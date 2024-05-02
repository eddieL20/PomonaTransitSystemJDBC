package GUI.ActionListeners;

import GUI.Frames.TripScheduleFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TripScheduleViewListener implements ActionListener {


    @Override
    public void actionPerformed(ActionEvent e) {
        //TODO: Open new frame that shows trip schedule
        TripScheduleFrame tsFrame = new TripScheduleFrame();
        tsFrame.setTitle("Trip Schedule Information");

    }
}
