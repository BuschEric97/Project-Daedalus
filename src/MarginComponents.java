/*
 * This class is to draw the tile border for each game tile
 */

import java.awt.*;
import javax.swing.*;

public class MarginComponents extends JPanel
{
    int marginSize; // size (in pixels) of the margins around the grid
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        this.setBackground(Color.WHITE);
        
        // draw a red box in the top left of the screen to act as a "close program" button
        g.setColor(Color.RED);
        g.fillRect(0, 0, marginSize, marginSize);
    }
    
    // constructor for this class - requires ppi and top left corner's coordinates
    MarginComponents(int tempMarginSize)
    {
        this.marginSize = tempMarginSize;
    }
    
    // getters and setters
    public void setMarginSize(int temp)
    { this.marginSize = temp; }
    public int getMarginSize()
    { return this.marginSize; }
}
