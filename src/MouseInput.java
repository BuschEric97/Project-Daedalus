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
    
    @Override
    public void mouseClicked(MouseEvent me)
    {
        int mx = me.getX(); // get the X coordinate of the mouse click
        int my = me.getY(); // get the Y coordinate of the mouse click
        
        System.out.println("User clicked at position: (" + mx + ", " + my + ")"); // print coordinates of mouse click
        
        if (mx < marginSize && my < marginSize)
            System.exit(0); // quit the program if the user clicks the red box at the top left of the screen
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
    
    MouseInput(int tempMarginSize)
    {
        this.marginSize = tempMarginSize;
    }
    
    // getters and setters
    public void setMarginSize(int temp)
    { this.marginSize = temp; }
    public int getMarginSize()
    { return this.marginSize; }
}
