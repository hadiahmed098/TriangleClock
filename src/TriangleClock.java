import javax.swing.*;
import java.awt.*;

public class TriangleClock extends JFrame {
    public static void main(String[] args) {
        new TriangleClock("Triangle Clock");
    }

    public TriangleClock(String title) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(title);
        setMinimumSize(new Dimension(500, 500));

        //Sets up the LayeredPane to display the clock body and the clock face.
        JLayeredPane bodyWrapper = getLayeredPane();
        bodyWrapper.setBounds(0,0,getWidth(),getHeight());

        ClockBody clockBody = new ClockBody(new Rectangle(0,0,getWidth(),getHeight()));
        clockBody.setOpaque(false);
        bodyWrapper.add(clockBody,0);

        TriangleClockMechanism clockFace = new TriangleClockMechanism(new Rectangle(0,0,getWidth(),getHeight()));
        clockFace.setOpaque(false);
        bodyWrapper.add(clockFace,1);

        setResizable(false);
        setVisible(true);

        while (true) {      //Repaints the frame constantly to force update the graphics on the frame.
            repaint();      //This way lets the Swing Manager handle the repainting of frame, otherwise frame never repaints.
                            //Using a plain setText on JLabel was causing the label to be placed on top of the old one.
        }
    }
}
