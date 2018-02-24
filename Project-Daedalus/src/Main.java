/* Project-Daedalus
    Digital Tabletop map utility with token integration
*/

/**
 * @author Christopher Nguyen
 *          Eric Busch
 *          Tyler Eley
 *          William Hopkins
 */
import javax.swing.*;
import java.awt.*;

public class Main
{
    // @param args the command line arguments
    public static void main(String[] args)
    {
        Dimension screen = java.awt.Toolkit.getDefaultToolkit().getScreenSize(); // get screen dimensions
        int pixelHeight = (int) screen.getHeight(); // get the screen's height and cast it to int
        int pixelWidth = (int) screen.getWidth(); // get the screen's width and cast it to int
        
        System.out.println("Screen Height: " + pixelHeight); // print the screen's height
        System.out.println("Screen Width: " + pixelWidth); // print the screen's width
        
        JFrame f = new JFrame("Game Board"); // create new JFrame
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // set the JFrame close operation to exit with the X button
        f.setSize(pixelWidth, pixelHeight); // set JFrame window size to monitor's size
        f.setUndecorated(true); // remove the title bar
        f.setVisible(true); // make the board visible
        f.addMouseListener(new MouseInput()); // add mouse listener
    }
}
