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
import java.util.HashMap;
import java.util.Map;

public class MouseInput implements MouseListener
{
    int marginSize; // size (in pixels) of the margins around the grid
    Tile[][] mapArray; // array with the tiles of the grid
    
    @Override
    public void mouseClicked(MouseEvent me)
    {
        int mx = me.getX(); // get the X coordinate of the mouse click
        int my = me.getY(); // get the Y coordinate of the mouse click
                
        if (mx < marginSize && my < marginSize)
            System.exit(0); // quit the program if the user clicks the top left of the screen
        
        if (mx < marginSize || my < marginSize || mx > marginSize || my > marginSize)
            System.out.println("User clicked at position: (" + mx + ", " + my + ")"); // print coordinates of mouse click
        else
        {
            
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
    MouseInput(int tempMarginSize, Tile[][] tempMapArray)
    {
        this.marginSize = tempMarginSize;
        this.mapArray = tempMapArray;
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
