/* Project-Daedalus
 *
 * Digital Tabletop map utility with token integration
 */

/**
 * @author Christopher Nguyen
 *          Eric Busch
 *          Tyler Eley
 *          William Hopkins
 */

public class Tile
{
    // PARAMETERS
    // Image map;
    Token token;
    
    public Tile()
    {
        //map = null;
        token = null;
    }
    
    // ACCESSORS
    /* public Image getMap()
    {   return map; }
    public void setMap( Image m )
    {   map = m;    }*/
    public Token getToken()
    {   return token;   }
    public void setToken( Token t )
    {   token = t;  }
}
