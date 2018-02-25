/*
 * Program by: Eric Busch
 *DESCRIPTION: Uses mouse events and listeners to get user input from the mouse.
 * 
 *METHODS USED:
 * mouseClicked(MouseEvent me)
 *      Triggered when the mouse is clicked.
 *      
 * mousePressed(MouseEvent me)
 *      Triggered when any mouse button is pressed
 * 
 *  mouseReleased(MouseEvent me)
 *      Triggered when a mouse button is released
 * 
 * mouseEntered(MouseEvent me)
 *      Triggered when the mouse enters the screen
 * 
 *  mouseExited(MouseEvent me)
 *      Triggered when the mouse exits the screen
*/

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener
{
    int marginSize; // size (in pixels) of the margins around the grid
    Tile[][] mapArray; // array with the tiles of the grid
    int ppi; // size of ppi
    int screenWidth; // size (in pixels) of screen width
    int screenHeight; // size (in pixels) of screen height
    static boolean tokenSelected = false; // boolean variable to determine which coordinate variables to record into
    static int tokenToMoveX; // x coordinate for token to be moved
    static int tokenToMoveY; // y coordinate for token to be moved
    static int moveTokenToX; // x coordinate for tile to move token to
    static int moveTokenToY; // y coordinate for tile to move token to
    
    @Override
    public void mouseClicked(MouseEvent me)
    {
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
        }
    }

    @Override
    public void mousePressed(MouseEvent me)
    {
    }

    @Override
    public void mouseReleased(MouseEvent me)
    {
    }

    @Override
    public void mouseEntered(MouseEvent me)
    {
    }

    @Override
    public void mouseExited(MouseEvent me)
    {
    }
    
    // constructor for class - requires marginSize
    MouseInput(int tempMarginSize, Tile[][] tempMapArray, int tempPPI, int tempHeight, int tempWidth)
    {
        this.marginSize = tempMarginSize;
        this.mapArray = tempMapArray;
        this.ppi = tempPPI;
        this.screenHeight = tempHeight;
        this.screenWidth = tempWidth;
    }
    
    // getters and setters
    public void setMarginSize(int temp)
    { this.marginSize = temp; }
    public int getMarginSize()
    { return this.marginSize; }
    public void setMapArray(Tile[][] tempArray)
    { this.mapArray = tempArray; }
    public Tile[][] getMapArray()
    { return this.mapArray; }
}
