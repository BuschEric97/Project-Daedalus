/* Project-Daedalus
    Digital Tabletop map utility with token integration
*/

/**
 * @author Christopher Nguyen
 *          Eric Busch
 *          Tyler Eley
 *          William Hopkins
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.math.*;

public class Main extends JFrame
{
    int ppi;
    int marginSize;
    
    private DrawCanvas canvas;
    
    public Main()
    {
        ppi = java.awt.Toolkit.getDefaultToolkit().getScreenResolution();
        marginSize = ppi / 4;
        canvas = new DrawCanvas();
        canvas.setPreferredSize( java.awt.Toolkit.getDefaultToolkit().getScreenSize() );
        Container cp = getContentPane();
        cp.add(canvas);
        setTitle("Game Board");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true); // remove the title bar
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // set the JFrame close operation to exit with the X button
        pack();
        addMouseListener(new MouseInput(marginSize)); // add mouse listener
        setVisible(true); // make the board visible
    }
    
    private class DrawCanvas extends JPanel
    {
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);     // paint parent's background
            setBackground(Color.BLACK);  // set background color for this JPanel

            g.setColor(Color.RED);
            g.fillRect(0, 0, marginSize, marginSize);
        }
    }
    
    public static void main(String[] args) {
        // Run the GUI codes on the Event-Dispatching thread for thread safety
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
               new Main(); // Let the constructor do the job
            }
        });
   }
}
