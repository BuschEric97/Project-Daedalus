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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame
{
    public static Tile[][] createArray(int ppi, int screenHeight, int screenWidth, int marginSize)
    {
        int maxTilesWidth = (screenWidth - (marginSize * 2)) / ppi; // maximum amount of tiles the width of the screen can hold with margins
        int maxTilesHeight = (screenHeight - (marginSize * 2)) / ppi; // maximum amount of tiles the height of the screen can hold with margins
        
        Tile[][] mapArray = new Tile[maxTilesHeight][maxTilesWidth]; // create the 2-dimentional array that holds the info for each tile
        
        for (int i = 0; i < maxTilesHeight; i++)
            for (int j = 0; j < maxTilesWidth; j++)
            {
                mapArray[i][j] = new Tile(ppi, marginSize + (j * ppi), marginSize + (i * ppi));
            }
        
        return mapArray;
    }
    
    // constructor for main - initializes all necessary variables
    public Main()
    {
        // General parameters
        int screenHeight = (int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        int screenWidth = (int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        int ppi = java.awt.Toolkit.getDefaultToolkit().getScreenResolution();
        int marginSize = ppi / 4;
        boolean state = false; // False to edit map, true to move tokens
        Tile[][] mapArray = createArray( ppi, screenHeight, screenWidth, marginSize );
        int delay = 500;
        
        // Build predetermined grid
        for ( int r = 0; r < mapArray.length; r++ )
        {
            for ( int c = 0; c < mapArray[0].length; c++ )
            {
                if ( r < mapArray.length / 2 && c < mapArray[0].length / 2 )
                    mapArray[r][c].setMap("wooden.png");
                else if ( r <= 1 || c <= 1 )
                    mapArray[r][c].setMap("dirt.png");
                else
                    mapArray[r][c].setMap("Grass.png");
            }
        }
        mapArray[mapArray.length / 2][mapArray[0].length / 2].setToken( new DnDToken() );
        mapArray[mapArray.length / 2][mapArray[0].length / 2].getToken().setMap("GnomeCircle.png");
        
        // Determine window settings
        JFrame f = new JFrame("Game Board");
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        f.setUndecorated(true); // remove the title bar
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // set the JFrame close operation to exit with the X button
        f.pack();
        f.setVisible(true); // make the board visible
        Canvas canvas = new Canvas( mapArray );
        f.setContentPane( canvas );
        f.addMouseListener(new MouseInput(marginSize, mapArray, ppi, screenHeight, screenWidth)); // add mouse listener
        
        // Create an instance of an anonymous subclass of ActionListener
        ActionListener updateTask = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                f.repaint();
            }
        };
        // Start and run the task at regular delay
        new Timer(delay, updateTask).start();
    }
    
    public static void main(String[] args)
    {
        // Run the GUI codes on the Event-Dispatching thread for thread safety
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main(); // Let the constructor do the job
            }
        });
    }
}
