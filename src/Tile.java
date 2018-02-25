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
import javax.swing.*;

public class Tile extends JPanel
{
    // PARAMETERS
    Image map; // background image of the tile
    Token token; // player's token in the tile
    int ppi; // pixels-per-inch
    int topX; // x coordinate of top left of tile border
    int topY; // y coordinate of top left of tile border
    
    // default constructor
    public Tile()
    {
        this.map = null;
        this.token = null;
        this.ppi = 0;
        this.topX = 0;
        this.topY = 0;
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
        ImageIcon icon = null;
        java.net.URL imgURL = getClass().getResource(s);
        if ( imgURL == null )
            return;
        icon = new ImageIcon(imgURL);
        map = icon.getImage();
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
