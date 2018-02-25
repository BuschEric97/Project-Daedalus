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
        f.addMouseListener(new MouseInput(marginSize, mapArray, ppi, screenHeight, screenWidth)); // add mouse listener
    }
    
    // Mouse listener
    class MouseInput implements MouseListener
    {
        int marginSize; // size (in pixels) of the margins around the grid
        Tile[][] mapArray; // array with the tiles of the grid
        int ppi; // size of ppi
        int screenWidth; // size (in pixels) of screen width
        int screenHeight; // size (in pixels) of screen height
        boolean tokenSelected = false; // boolean variable to determine which coordinate variables to record into
        int tokenToMoveX; // x coordinate for token to be moved
        int tokenToMoveY; // y coordinate for token to be moved
        int moveTokenToX; // x coordinate for tile to move token to
        int moveTokenToY; // y coordinate for tile to move token to

        @Override
        public void mouseClicked(MouseEvent me)
        {
            repaint();
            int mx = me.getX(); // get the X coordinate of the mouse click
            int my = me.getY(); // get the Y coordinate of the mouse click
            int cellWidth; // variable for x coordinate of the cell if the user clicked on the grid
            int cellHeight; // variable for y coordinate of the cell if the user clicked on the grid

            if (mx < marginSize && my < marginSize)
                System.exit(0); // quit the program if the user clicks the top left of the screen

            if (mx < marginSize || my < marginSize || mx > (screenWidth - marginSize) || my > (screenHeight - marginSize))
                System.out.println("User clicked at position: (" + mx + ", " + my + ")"); // print coordinates (in pixels) of mouse click to console
            else
            {
                cellWidth = mx / ppi; // use integer division to determine the x coordinate (in the array) of the cell clicked
                cellHeight = my / ppi; // use integer division to determine the y coordinate (in the array) of the cell clicked
                System.out.println("User clicked in cell: (" + cellWidth + ", " + cellHeight + ")"); // print coordinates (in the array) of mouse click to console

                if (!tokenSelected)
                {
                    tokenToMoveX = cellWidth; // set the x coordinate of the cell clicked
                    tokenToMoveY = cellHeight; // set the y coordinate of the cell clicked
                    tokenSelected = true; // set tokenSelected to true for next iteration
                }
                else
                {
                    moveTokenToX = cellWidth; // set the x coordinate of the cell
                    moveTokenToY = cellHeight; // set the y coordinate of the cell
                    tokenSelected = false; // set tokenSelected back to false for next iteration

                    if (mapArray[moveTokenToY][moveTokenToX].getToken() == null) // check that target cell is empty
                    {
                        mapArray[moveTokenToY][moveTokenToX].setToken(mapArray[tokenToMoveY][tokenToMoveX].getToken()); // copy the selected token into its new position
                        mapArray[tokenToMoveY][tokenToMoveX].setToken(null); // delete the selected token from its old position
                    }
                    else
                        System.err.println("error: target cell is not empty!"); // if target cell is not empty, print an error message
                }
                repaint();
            }
        }
        @Override public void mousePressed(MouseEvent e) { }
        @Override public void mouseReleased(MouseEvent e) { }
        @Override public void mouseEntered(MouseEvent e) { }
        @Override public void mouseExited(MouseEvent e) { }
    
        // constructor for class - requires marginSize
        MouseInput(int tempMarginSize, Tile[][] tempMapArray, int tempPPI, int tempHeight, int tempWidth)
        {
            this.marginSize = tempMarginSize;
            this.mapArray = tempMapArray;
            this.ppi = tempPPI;
            this.screenHeight = tempHeight;
            this.screenWidth = tempWidth;
        }
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
