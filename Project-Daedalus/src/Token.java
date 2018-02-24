/* Project-Daedalus
    This is the token interface for the player tokens.
    It is a generalized player token so that we can
    implement player tokens from different tabletop games
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

public abstract class Token
{
    // PARAMETERS
    Image token;
    
    // ACCESSORS
    public Image getMap()
    {   return token; }
    public void setMap( Image t )
    {   token = t;  }
    public void setMap( String s )
    {
        try {  token = ImageIO.read( new File( s ) ); }
        catch ( IOException ex ) {   }
    }
    
    // METHODS
    abstract public String DisplayStats();
}
