/* Project-Daedalus
    This class contains the image for the map tile and a token on it.
    It does not combine the two into an image within it, as tokens may
    move.
*/

/**
 * @author Christopher Nguyen
 *          Eric Busch
 *          Tyler Eley
 *          William Hopkins
 */
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.*;
import javax.swing.*;

public class Tile extends JPanel
{
    // PARAMETERS
    Image map; // background image of the tile
    Token token; // player's token in the tile
    int ppi; // pixels-per-inch
    int topX; // x coordinate of top left of tile border
    int topY; // y coordinate of top left of tile border
    
    @Override
 public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g2);
        this.setBackground(Color.WHITE); // background of the tile
        TileFill(g2,topX,topY);
        g2.setColor(Color.BLACK); // set the color of the tile border
        g2.drawRect(this.topX, this.topY, this.ppi, this.ppi); // draw a 1 inch square for the tile
        g2.drawRect(this.topX + 1, this.topY + 1, this.ppi - 2, this.ppi - 2); // draw another square one pixel smaller to make the border 2 pixels thick
        
    }
    public void TileFill(Graphics2D g2,int x,int y)
    {
        ImageIcon icon = new ImageIcon("C:\\Users\\Tyler Eley\\Documents\\NetBeansProjects\\Project-Daedalus\\img\\Grass.png");//Image to be loaded. Feel free to change this
        Image tileImage = icon.getImage(); 
        g2.drawImage(tileImage, x, y,this.ppi,this.ppi, this);
       
    }
    // constructor for this class - requires ppi and top left corner's coordinates
    public Tile(int tempPPI, int tempX, int tempY)
    {
        this.map = null;
        this.token = null;
        this.ppi = tempPPI;
        this.topX = tempX;
        this.topY = tempY;
    }
    
    // ACCESSORS
    public Image getMap()
    {   return map; }
    public void setMap( Image m )
    {   map = m;    }
    public void setMap( String s )
    {
        try {  map = ImageIO.read( new File( s ) ); }
        catch ( IOException ex ) {   }
    }
    public Token getToken()
    {   return token;   }
    public void setToken( Token t )
    {   token = t;  }
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
