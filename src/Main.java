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
import java.awt.event.*;

public class Main extends JFrame
{
    // dynamically create the array for the game board and initialize each element
    public static Tile[][] createArray(int ppi, int screenHeight, int screenWidth, int marginSize)
    {
        int maxTilesWidth = (screenWidth - (marginSize * 2)) / ppi; // maximum amount of tiles the width of the screen can hold with margins
        int maxTilesHeight = (screenHeight - (marginSize * 2)) / ppi; // maximum amount of tiles the height of the screen can hold with margins
        
        Tile[][] mapArray = new Tile[maxTilesHeight][maxTilesWidth]; // create the 2-dimensional array that holds the info for each tile
        
        for (int i = 0; i < maxTilesHeight; i++)
            for (int j = 0; j < maxTilesWidth; j++) // loop through entire 2-dimensional array and initialize each tile with the appropriate coordinates
            {
                mapArray[i][j] = new Tile(ppi, marginSize + (j * ppi), marginSize + (i * ppi)); // set ppi as normal and calculate the x & y coordinates for the top left pixel
            }
        
        return mapArray; // return the created array
    }
    
    // constructor for main - initializes all necessary variables
    public Main()
    {
        // General parameters
        int screenHeight = (int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight(); // screen height in pixels
        int screenWidth = (int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth(); // screen width in pixels
        int ppi = java.awt.Toolkit.getDefaultToolkit().getScreenResolution(); // ppi - amount of pixels needed to display an inch
        int marginSize = ppi / 4; // margin around the grid - defined as 1/4 of ppi
        boolean state = false; // False to edit map, true to move tokens
        Tile[][] mapArray = createArray( ppi, screenHeight, screenWidth, marginSize ); // create the mapArray
        
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
        mapArray[mapArray.length / 2][mapArray[0].length / 2].getToken().setMap("GnomeCircle.png"); // add test gnome in center of grid
        
        // Determine window settings
        JFrame f = new JFrame("Game Board"); // define JFrame
        f.setExtendedState(JFrame.MAXIMIZED_BOTH); // make JFrame maximized
        f.setUndecorated(true); // remove the title bar
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // set the JFrame close operation to exit with the X button
        f.setVisible(true); // make the board visible
        Canvas canvas = new Canvas( mapArray ); // define a new canvas for drawing the board
        f.setContentPane( canvas ); // set canvas to the content pane
        
        // Action Listeners
        f.addMouseListener(new MouseListener()
        {
            int button; // -1 = none, 0 = exit, 1 = array, 2 = add token
            int startX; // x coordinate for token to be moved
            int startY; // y coordinate for token to be moved
            int endX; // x coordinate for tile to move token to
            int endY; // y coordinate for tile to move token to
            @Override
            public void mouseClicked(MouseEvent e) { }
            @Override
            public void mousePressed(MouseEvent e)
            {
      		int x = e.getX(); // get the X coordinate of the mouse click
                int y = e.getY(); // get the Y coordinate of the mouse click
                button = -1;
                if ( x < marginSize && y < marginSize ) // click starts on the exit button
                {
                    button = 0;
                } else if ( x > marginSize && x < marginSize+mapArray[0].length*ppi &&
                            y > marginSize && y < marginSize+mapArray.length*ppi ) // click starts in the array
                {
                    button = 1;
                    startX = x / ppi; // use integer division to determine the x coordinate (in the array) of the cell clicked
                    startY = y / ppi; // use integer division to determine the y coordinate (in the array) of the cell clicked
                } else if ( x < marginSize && y < 2*marginSize )
                {
                    button = 2;
                }
                f.repaint();
            }
            @Override
            public void mouseReleased(MouseEvent e)
            {
      		int x = e.getX(); // get the X coordinate of the mouse click
                int y = e.getY(); // get the Y coordinate of the mouse click
                if ( x < marginSize && y < marginSize && button == 0 ) // click starts and ends on the exit button
                {
                    System.exit(0); // quit the program if the user clicks the top left of the screen
                } else if ( x > marginSize && x < marginSize+mapArray[0].length*ppi &&
                            y > marginSize && y < marginSize+mapArray.length*ppi &&
                            button == 1 ) // click starts and ends in the array
                {
                    
                    endX = x / ppi; // use integer division to determine the x coordinate (in the array) of the cell clicked
                    endY = y / ppi; // use integer division to determine the y coordinate (in the array) of the cell clicked
                    if ( startX == endX && startY == endY ) // if the click in the array ends on the same index
                    {
                        System.out.println("Tooltip");
                        repaint();
                        return;
                    }
                    System.out.println("User started in cell: (" + startX + ", " + startY + ")"); // print coordinates (in the array) of mouse click to console
                    System.out.println("User ended in cell: (" + endX + ", " + endY + ")"); // print coordinates (in the array) of mouse click to console
                    if ( mapArray[startY][startX].getToken() != null && mapArray[endY][endX].getToken() == null )
                    {
                        mapArray[endY][endX].setToken( mapArray[startY][startX].getToken() );
                        mapArray[startY][startX].setToken( null );
                        System.out.println("Token moved.");
                    }
                } else if ( x < marginSize && y < 2*marginSize && button == 2 )
                {
                    System.out.println("Adding Token");
                    for ( int r = 0; r < mapArray.length; r++ )
                        for ( int c = 0; c < mapArray[0].length; c++ )
                            if ( mapArray[r][c].getToken() == null )
                            {
                                mapArray[r][c].setToken( new DnDToken() );
                                mapArray[r][c].getToken().setMap("blank.png");
                                f.repaint();
                                return;
                            }
                }
                f.repaint();
            }
            @Override
            public void mouseEntered(MouseEvent e) { }
            @Override
            public void mouseExited(MouseEvent e) { }
        }); // add mouse listener
    }

    public static void main(String[] args)
    {
        // Run the GUI codes on the Event-Dispatching thread for thread safety
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run()
            {
                new Main(); // Let the constructor do the job
            }
        });
    }
}
