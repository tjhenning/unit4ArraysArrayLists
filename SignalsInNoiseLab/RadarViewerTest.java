
import javax.swing.JFrame;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Write a description of test class RadarViewerTest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RadarViewerTest
{
    /** description of instance variable x (add comment for each instance variable) */
    private int x;

    /**
     * Default constructor for objects of class RadarViewerTest
     */
    public RadarViewerTest()
    {
        // initialise instance variables
        x = 0;
    }
    
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
    
    @Test
    public void test()// throws InterruptedException
    {
        final int ROWS = 100;
        final int COLS = 100;
        System.out.println("Enter whatever numbers you want as long as you only make one monster.\nYou can SPAM 1 if you want.");
        Radar radar = new Radar(ROWS, COLS);
        radar.setNoiseFraction(0.10);
        radar.setMonsterLocation(4,4,0);
        radar.scan();
        
        JFrame frame = new JFrame();
        
        frame.setTitle("Signals in Noise Lab");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // a frame contains a single component; create the radar component and add it to the frame
        RadarComponent component = new RadarComponent(radar);
        frame.add(component);
        
        // set the size of the frame to encompass the contained component
        frame.pack();
        
        // make the frame visible which will result in the paintComponent method being invoked on the
        //  component.
        frame.setVisible(true);
        
        // perform 100 scans of the radar wiht a slight pause between each
        // after each scan, instruct the Java Run-Time to redraw the window
        for(int i = 0; i < 100; i++)
        {
            //Thread.sleep(100); // sleep 100 milliseconds (1/10 second)
            
            radar.scan();
            
            frame.repaint();
        }
        int[][] detect=radar.getDetected();
        assertEquals(detect[0][0],4);
        assertEquals(detect[0][1],4);
    }

}
