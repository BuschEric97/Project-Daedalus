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
                mapArray[i][j].setTopLeftX(marginSize + (j * ppi)); // set x coordinate for the top left pixel of the tile
                mapArray[i][j].setTopLeftY(marginSize + (j * ppi)); // set y coordinate for the top left pixel of the tile
                mapArray[i][j].setPPI(ppi); // set ppi for the tile
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
        Canvas c = new Canvas( mapArray );
        f.add( c );
        f.repaint();
    }
    
    class Canvas extends JPanel
    {
        Tile[][] mapArray;
        int screenHeight;
        int screenWidth;
        int ppi;
        int marginSize;
        
        public Canvas( Tile[][] m )
        {
            mapArray = m;
            screenHeight = (int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight();
            screenWidth = (int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth();
            ppi = java.awt.Toolkit.getDefaultToolkit().getScreenResolution();
            marginSize = ppi / 4;
        }
        @Override
        public void paintComponent( Graphics g )
        {
            setBackground( Color.WHITE );
            g.setColor( Color.BLACK );
            g.fillRect(0, 0, screenWidth, marginSize);
            g.fillRect(0, 0, marginSize, screenHeight);
            g.fillRect(0, screenHeight-marginSize, screenWidth, marginSize);
            g.fillRect(screenWidth-marginSize, 0, marginSize, screenHeight);
            g.setColor( Color.RED );
            //for( int r = 0; r < mapArray.size(); r ++)
            g.fillRect(0, 0, marginSize, marginSize);
        }
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
