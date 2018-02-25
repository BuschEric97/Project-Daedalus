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
    
    public Main()
    {
        int screenHeight = (int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        int screenWidth = (int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        int ppi = java.awt.Toolkit.getDefaultToolkit().getScreenResolution();
        int marginSize = ppi / 4;
        boolean state = false; // False to edit map, true to move tokens
        Tile[][] mapArray = createArray( ppi, screenHeight, screenWidth, marginSize );
        JFrame f = new JFrame("Game Board");
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        f.setUndecorated(true); // remove the title bar
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // set the JFrame close operation to exit with the X button
        f.pack();
        f.addMouseListener(new MouseInput(marginSize, mapArray)); // add mouse listener
        f.setVisible(true); // make the board visible
        Canvas canvas = new Canvas( mapArray );
        for( int r = 0; r < mapArray.length; r++)
            for( int c = 0; c < mapArray[0].length; c++ )
                mapArray[r][c].setMap("grass.png");
        f.add( canvas );
        f.repaint();
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
