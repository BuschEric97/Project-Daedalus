class Canvas extends JPanel
    {
        Tile[][] mapArray;
        int screenHeight;
        int screenWidth;
        int ppi;
        int marginSize;
        
        public Canvas( Tile[][] m )
        {
            mapArray = m;
            screenHeight = (int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight();
            screenWidth = (int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth();
            ppi = java.awt.Toolkit.getDefaultToolkit().getScreenResolution();
            marginSize = ppi / 4;
        }
        @Override
        public void paintComponent( Graphics g )
        {
            g.setColor( Color.BLACK );
            g.fillRect( 0, 0, screenWidth, screenHeight );
            for( int r = 0; r < mapArray.length; r++)
                for( int c = 0; c < mapArray[0].length; c++ )
                    if ( mapArray[r][c].getMap() != null )
                    {
                        g.drawImage( mapArray[r][c].getMap(),
                                     mapArray[r][c].getTopLeftX(),
                                     mapArray[r][c].getTopLeftY(),
                                     null);
                    }
            g.setColor( Color.RED );
            g.fillRect(0, 0, marginSize, marginSize);
        }
    }
