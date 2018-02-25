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
import java.math.*;

public class Main
{
    // @param args the command line arguments
    public static void main(String[] args)
    {   
        Dimension screen = java.awt.Toolkit.getDefaultToolkit().getScreenSize(); // get screen dimensions
        int pixelHeight = (int) screen.getHeight(); // get the screen's height and cast it to int
        int pixelWidth = (int) screen.getWidth(); // get the screen's width and cast it to int
        int ppi = java.awt.Toolkit.getDefaultToolkit().getScreenResolution();
        
        Tile[][] mapArray = CreateArray.createArray();
        
        TileBorder sqr = new TileBorder(ppi, 20, 20); // test tile
        
        System.out.println("Screen Height: " + pixelHeight); // print the screen's height
        System.out.println("Screen Width: " + pixelWidth); // print the screen's width
        
        JFrame f = new JFrame("Game Board"); // create new JFrame
        
        f.add(sqr); // add the test tile to the frame
        
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // set the JFrame close operation to exit with the X button
        f.setSize(pixelWidth, pixelHeight); // set JFrame window size to monitor's size
        f.setUndecorated(true); // remove the title bar
        f.setVisible(true); // make the board visible
        f.addMouseListener(new MouseInput()); // add mouse listener
    }
    
    public static Tile[][] createArray(int ppi, int screenHeight, int screenWidth)
    {
        int marginSize = ppi / 4; // size in pixels of the margins
        int maxTilesWidth = (screenWidth - (marginSize * 2)) / ppi; // maximum amount of tiles the width of the screen can hold with margins
        int maxTilesHeight = (screenHeight - (marginSize * 2)) / ppi; // maximum amount of tiles the height of the screen can hold with margins
        
        Tile[][] mapArray = new Tile[maxTilesWidth][maxTilesHeight]; // create the 2-dimentional array that holds the info for each tile
        
        return mapArray;
    }

}
