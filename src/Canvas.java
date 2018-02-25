/**
 * @author Christopher Nguyen
 *          Eric Busch
 *          Tyler Eley
 *          William Hopkins
 */
import javax.swing.*;
import java.awt.*;

class Canvas extends JPanel
{
    // PARAMETERS
    Tile[][] mapArray;
    int screenHeight, screenWidth, ppi, marginSize;

    // CONSTRUCTOR
    public Canvas( Tile[][] m ) // default constructor that determines all the necessary details after receiving the Tile array
    {
        mapArray = m;
        screenHeight = (int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        screenWidth = (int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        ppi = java.awt.Toolkit.getDefaultToolkit().getScreenResolution();
        marginSize = ppi / 4;
    }
    
    // METHODS
    @Override
    public void paintComponent( Graphics g )
    {
        g.setColor( Color.BLACK ); // set background color to black
        g.fillRect( 0, 0, screenWidth, screenHeight );
        
        // Draw the grid based on the margins and the size
        for( int r = 0; r < mapArray.length; r++ )
        {
            for( int c = 0; c < mapArray[0].length; c++ )
            {
                if ( mapArray[r][c].getMap() != null ) // draw the map tile if there is one to display
                {
                    g.drawImage( mapArray[r][c].getMap(),
                                 mapArray[r][c].getTopLeftX(),
                                 mapArray[r][c].getTopLeftY(),
                                 ppi, ppi,
                                 null);
                }
                if ( mapArray[r][c].getToken() != null ) // draw the token if there is one to display
                {
                    g.drawImage( mapArray[r][c].getToken().getMap(),
                                 mapArray[r][c].getTopLeftX(),
                                 mapArray[r][c].getTopLeftY(),
                                 ppi, ppi,
                                 null);
                }
                g.drawLine( marginSize+c*ppi, marginSize, marginSize+c*ppi, marginSize+mapArray.length*ppi); // horizontal lines
            }
            g.drawLine( marginSize, marginSize+r*ppi, marginSize+mapArray[0].length*ppi, marginSize+r*ppi ); // vertical lines
        }
        g.drawLine( marginSize,
                    marginSize+mapArray.length*ppi,
                    marginSize+mapArray[0].length*ppi,
                    marginSize+mapArray.length*ppi); // bottom line
        g.drawLine( marginSize+mapArray[0].length*ppi,
                    marginSize,
                    marginSize+mapArray[0].length*ppi,
                    marginSize+mapArray.length*ppi); // right line
        g.setColor( Color.RED ); // add the close button
        g.fillRect(0, 0, marginSize, marginSize);
        g.setColor( Color.BLUE ); // add the add button
        g.fillRect(0, marginSize, marginSize, marginSize);
    }
}
