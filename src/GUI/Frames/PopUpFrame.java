package GUI.Frames;


import javax.swing.*;
import java.awt.*;

public class PopUpFrame extends JFrame {

    public PopUpFrame(JPanel panel){
        this.setTitle("Trip Schedule");
        this.setLayout(new BorderLayout()); // border layout lets you add components to edges and center

        this.add(panel, BorderLayout.CENTER);

        this.pack();
        this.setSize(720, 850);
        this.setVisible(true); // make frame  visible

    }


}
