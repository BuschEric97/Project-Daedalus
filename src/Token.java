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
import java.awt.*;
import javax.swing.*;

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
        ImageIcon icon = null;
        java.net.URL imgURL = getClass().getClassLoader().getResource(s);
        if ( imgURL != null )
            icon = new ImageIcon(imgURL);
        token = icon.getImage();
    }
    
    // METHODS
    abstract public String DisplayStats();
}
