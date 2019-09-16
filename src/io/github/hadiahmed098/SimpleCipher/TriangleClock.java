package io.github.hadiahmed098.SimpleCipher;
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
        bodyWrapper.add(clockBody,JLayeredPane.DEFAULT_LAYER);

        TriangleClockMechanism clockFace = new TriangleClockMechanism(new Rectangle(0,0,getWidth(),getHeight()));
        clockFace.setOpaque(false);
        bodyWrapper.add(clockFace,JLayeredPane.MODAL_LAYER);

        setResizable(false);
        setVisible(true);

        //Repaints the frame every 100 ms to force update the graphics on the frame.
        //Uses a lambda to avoid creating an anonymous ActionListener every run.
        Timer t = new Timer(100, e ->
                repaint());

        t.setRepeats(true);
        t.setCoalesce(false);
        t.start();

    }
}
