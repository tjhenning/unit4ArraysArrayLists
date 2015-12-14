import javax.swing.JFrame;

/**
 * Class that contains the main method for the program and creates the frame containing the component.
 * 
 * @author @gcschmit
 * @version 19 July 2014
 */
public class RadarViewer
{
    /**
     * main method for the program which creates and configures the frame for the program
     *
     */
    public static void main(String[] args) throws InterruptedException
    {
        // create the radar, set the monster location, and perform the initial scan
        final int ROWS = 100;
        final int COLS = 100;
                
        Radar radar = new Radar(ROWS, COLS);
        //radar.setNoiseFraction(noiseFraction);
        radar.scan();
        radar.setOriginal();
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
        int[][] detect=new int[10][2];
        int[] detect2=new int[2];
        // perform 100 scans of the radar wiht a slight pause between each
        // after each scan, instruct the Java Run-Time to redraw the window
        //while (detect[0]==0)
        //{
            for(int i = 0; i < 20; i++)
            {
                Thread.sleep(100); // sleep 100 milliseconds (1/10 second)
                
                radar.scan();
                
                frame.repaint();                 
            }
            detect=radar.getDetected();
            while (detect[0][0]==-1)
            {            
                Thread.sleep(100/(radar.getNumScans()/20));             
                radar.scan();            
                frame.repaint();                                           
                detect=radar.getDetected();
            }
        //}
        System.out.println("Monster detected at: ");
        for (int i=0;i<radar.getNumberOfMonsters();i++)
        {   
            System.out.println(detect[i][0]+" "+detect[i][1]);            
        }
        System.out.println("Monsters actually at: ");
        for (int i=0;i<radar.getNumberOfMonsters();i++)
        {   
            detect2=radar.getMonsterLocation(i);
            System.out.println(detect2[0]+" "+detect2[1]);
        }
    }

}
