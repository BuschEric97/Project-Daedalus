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
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.*;

public class Tile
{
    // PARAMETERS
    Image map;
    Token token;
    
    public Tile()
    {
        map = null;
        token = null;
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
}
