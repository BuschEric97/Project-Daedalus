/*
 * This class is to draw the tile border for each game tile
 */

import java.awt.*;
import javax.swing.*;

public class TileBorder extends JPanel
{
    int ppi; // pixels-per-inch
    int topX; // x coordinate of top left of tile border
    int topY; // y coordinate of top left of tile border
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        this.setBackground(Color.WHITE); // background of the tile
        
        g.setColor(Color.BLACK); // set the color of the tile border
        g.drawRect(this.topX, this.topY, this.ppi, this.ppi); // draw a 1 inch square for the tile
        g.drawRect(this.topX + 1, this.topY + 1, this.ppi - 2, this.ppi - 2); // draw another square one pixel smaller to make the border 2 pixels thick
    }
    
    // constructor for this class - requires ppi and top left corner's coordinates
    TileBorder(int tempPPI, int tempX, int tempY)
    {
        this.ppi = tempPPI;
        this.topX = tempX;
        this.topY = tempY;
    }
    
    // getters and setters
    public void setPPI(int temp)
    { this.ppi = temp; }
    public int getPPI()
    { return this.ppi; }
    public void setTopLeftX(int temp)
    { this.topX = temp; }
    public int getTopLeftX()
    { return this.topX; }
    public void setTopLeftY(int temp)
    { this.topY = temp; }
    public int getTopLeftY()
    { return this.topY; }
}
