import java.util.Arrays;
import java.util.Scanner;
/**
 * The model for radar scan and accumulator
 * 
 * @author @gcschmit
 * @version 19 July 2014
 */
public class Radar
{
    
    // stores whether each cell triggered detection for the current scan of the radar
    // (true represents a detected monster, which may be a false positive)
    private boolean[][] currentScan;
    private boolean isMoving=false;
    private int[][] original;
    // value of each cell is incremented for each scan in which that cell triggers detection
    private int[][] accumulator;
    
    // location of the monster
    private int monsterLocationRow;
    private int monsterLocationCol;

    // probability that a cell will trigger a false detection (must be >= 0 and < 1)
    private double noiseFraction;
    
    // number of scans of the radar since construction
    private int numScans;
    public int debugi;
    public int debugi2;

    /**
     * Constructor for objects of class Radar
     * 
     * @param   rows    the number of rows in the radar grid
     * @param   cols    the number of columns in the radar grid
     */
    public Radar(int rows, int cols)
    {
        // initialize the currentScan 2D array and the accumulator 2D array
        currentScan=new boolean[rows][cols];
        accumulator=new int[rows][cols];
        //lastScan=new boolean[100][100];
        for (int i=0; i<rows;i++)
        {
            for (int i2=0; i2<cols;i2++)
            {
                currentScan[i][i2]=false;
            }
        }
        
        // randomly set the location of the monster (can be explicity set through the
        //  setMonsterLocation method for the unit test
        Scanner s=new Scanner(System.in);
        System.out.println("Set row #: ");
        monsterLocationRow = s.nextInt();//(int)(Math.random() * rows);
        System.out.println("Set column #: ");
        monsterLocationCol = s.nextInt();//(int)(Math.random() * cols);
        System.out.println("Is the monster moving?(y?): ");
        if (s.next().equals("y"))
        {
            isMoving=true;
        }
        noiseFraction = 0.05;
        numScans= 0;
    }
    
    /**
     * Performs a scan of the radar. Noise is injected into the grid and the accumulator is updated.
     * 
     */
    public void scan()
    {
        // algorithm for performing a scan:
        //    1. set all cells in the currentScan 2D array to false
        //    2. set the location of the monster in the currentScan 2D array
        //    3. inject noise into the grid
        //    4. update the accumulator 2D array based on the state of the currentScan 2D array
        //    5. increment the numScans instance variable
        //System.out.println("Running "+numScans);
        double mod=Math.random();
        for (int i=0; i<currentScan.length;i++)
        {
            for (int i2=0; i2<currentScan[0].length;i2++)
            {
                currentScan[i][i2]=false;
            }
        }
        if (isMoving)
        {
            
            if (mod>.8)
            {
                if (monsterLocationRow==currentScan.length-1)
                {
                    monsterLocationRow=0;
                }
                else
                {
                    monsterLocationRow++;
                }
            }
            else if (mod>.6)
            {
                if (monsterLocationRow==0)
                {
                    monsterLocationRow=currentScan.length-1;
                }
                else
                {
                    monsterLocationRow--;
                }
            }
            else if (mod>.5)
            {
                //
            }
            else if (mod>.2)
            {
                 if (monsterLocationCol==currentScan.length-1)
                {
                    monsterLocationCol=0;
                }
                else
                {
                    monsterLocationCol++;
                }
            }
            else
            {
                 if (monsterLocationCol==0)
                {
                    monsterLocationCol=currentScan.length-1;
                }
                else
                {
                    monsterLocationCol--;
                }
            }
        }       
        
        for (int i=0; i<currentScan.length;i++)
        {
            for (int i2=0; i2<currentScan[0].length;i2++)
            {
                currentScan[monsterLocationRow][monsterLocationCol]=true;
            }
        }
        injectNoise();        
        if(isMoving){
                for (int i=0; i<currentScan.length&&numScans!=0;i++)
                {
                    for (int i2=0; i2<currentScan[0].length;i2++)
                    {
                        if (accumulator[i][i2]==1)
                        {
                            int[] detect=getMonsterLocation();
                            //System.out.println("Scan: "+numScans+". Checking "+i+" "+i2+", monster actually at "+detect[0]+" "+detect[1]);
                            String around=checkAround(i,i2);
                            int xchangen=i-1;
                            int xchangep=i+1;
                            int ychangen=i2-1;
                            int ychangep=i2+1;
                            int row=100;
                            //System.out.println("  String: "+around);
                            if (i==0)
                            {
                                xchangen=row-1;
                            }
                            else if (i==row-1)
                            {
                                xchangep=0;
                            }     
                            if (i2==0)
                            {
                                ychangen=row-1;
                            }
                            else if (i2==row-1)
                            {
                                ychangep=0;
                            }      
                            if(around.contains("u"))
                            {accumulator[i][ychangen]=1;;}
                            if(around.contains("d"))
                            {accumulator[i][ychangep]=1;}
                            if(around.contains("l"))
                            {accumulator[xchangen][i2]=1;}
                            if(around.contains("r"))
                            {accumulator[xchangep][i2]=1;}
                            if(around.contains("n"))
                            {accumulator[i][i2]=1;}
                            else
                            {accumulator[i][i2]=0;}                            
                        }
                    }
                }
            }
        
        else{      
        for (int i=0; i<currentScan.length;i++)
        {
            for (int i2=0; i2<currentScan[0].length;i2++)
            {
                if (currentScan[i][i2]==true)
                {
                    accumulator[i][i2]++;
                }
            }
        }       
        }
        numScans++;
        
    }

    /**
     * Sets the location of the monster
     * 
     * @param   row     the row in which the monster is located
     * @param   col     the column in which the monster is located
     * @pre row and col must be within the bounds of the radar grid
     */
    public void setMonsterLocation(int row, int col)
    {
        // remember the row and col of the monster's location
        monsterLocationRow = row;
        monsterLocationCol = col;
        
        // update the radar grid to show that something was detected at the specified location
        currentScan[row][col] = true;
    }
    
    public void setOriginal()
    {
        for (int i=0; i<currentScan.length;i++)
        {
            for (int i2=0; i2<currentScan[0].length;i2++)
            {
                if (currentScan[i][i2])
                {
                    accumulator[i][i2]=1;
                }
                else
                {
                    accumulator[i][i2]=0;
                }
            }
        }    
        //original=currentScan.clone();
    }
    
     /**
     * Sets the probability that a given cell will generate a false detection
     * 
     * @param   fraction    the probability that a given cell will generate a flase detection expressed
     *                      as a fraction (must be >= 0 and < 1)
     */
    public void setNoiseFraction(double fraction)
    {
        noiseFraction = fraction;
    }
    
    /**
     * Returns true if the specified location in the radar grid triggered a detection.
     * 
     * @param   row     the row of the location to query for detection
     * @param   col     the column of the location to query for detection
     * @return true if the specified location in the radar grid triggered a detection
     */
    public boolean isDetected(int row, int col)
    {
        return currentScan[row][col];
    }
    
    /**
     * Returns the number of times that the specified location in the radar grid has triggered a
     *  detection since the constructor of the radar object.
     * 
     * @param   row     the row of the location to query for accumulated detections
     * @param   col     the column of the location to query for accumulated detections
     * @return the number of times that the specified location in the radar grid has
     *          triggered a detection since the constructor of the radar object
     */
    public int getAccumulatedDetection(int row, int col)
    {
        return accumulator[row][col];
    }
    
    /**
     * Returns the number of rows in the radar grid
     * 
     * @return the number of rows in the radar grid
     */
    public int getNumRows()
    {
        return currentScan.length;
    }
    
    /**
     * Returns the number of columns in the radar grid
     * 
     * @return the number of columns in the radar grid
     */
    public int getNumCols()
    {
        return currentScan[0].length;
    }
    
    /**
     * Returns the number of scans that have been performed since the radar object was constructed
     * 
     * @return the number of scans that have been performed since the radar object was constructed
     */
    public int getNumScans()
    {
        return numScans;
    }
    
    /**
     * Returns the location that has been positive the most times- where the monster is detected
     * 
     * @return where the monster is detected in int[] form
     */
    public int[] getDetected()
    {
        int sent=0;
        int biggestx=0;
        int biggesty=0;
        for (int i=0; i<currentScan.length;i++)
        {
            for (int i2=0; i2<currentScan[0].length;i2++)
            {
                if (accumulator[i][i2]>sent)
                {
                    sent=accumulator[i][i2];
                    biggestx=i;
                    biggesty=i2;
                }
                else if (accumulator[i][i2]==sent)
                {                    
                    return new int[] {-1,-1};
                }
            }
        }               
        return new int[] {biggestx,biggesty};
    }
    public int[] getMonsterLocation()
    {
        int[] r={monsterLocationRow,monsterLocationCol};
        return r;
    }
    
    public String checkAround(int x, int y)
    {        
        String retrn=new String("");
//         if (currentScan[x-1][y-1]&&x!=0&&y!=0)
//             {
//                 return true;
//             }
        if (x!=0&&currentScan[x-1][y])
            {
                retrn+="l";
            }
//         if (currentScan[x-1][y+1]&&x!=0&&y+1!=currentScan.length)
//         {
//             return true;
//         }
        if (y!=0&&currentScan[x][y-1])
        {
            retrn+="u";
        }
         if (currentScan[x][y])
         {
             retrn+="n";
         }
        if (y+1!=currentScan.length&&currentScan[x][y+1])
        {
            retrn +="d";
        }
//         if (currentScan[x+1][y-1]&&x+1!=currentScan.length&&y!=0)
//         {
//             return true;
//         }
        if (x+1!=currentScan.length&&currentScan[x+1][y])
        {
            retrn+="r";
        }
//         if (currentScan[x+1][y+1]&&y+1!=currentScan.length&&x+1!=currentScan.length)
//         {
//             return true;
//         }
        return retrn;
    }
    /**
     * Sets cells as falsely triggering detection based on the specified probability
     * 
     */
    private void injectNoise()
    {
        // Iterate through all cells in the currentScan 2D array to inject noise by setting false positives.
        // The noiseFraction instance variable is the probability that a given cell will be
        // detected as a false positive. Use the Math.random method to determine if each cell should be set
        // as a false positive.        
        
        for (int i=0; i<currentScan.length;i++)
        {
            for (int i2=0; i2<currentScan[0].length;i2++)
            {
                if (Math.random()<noiseFraction)
                {
                    currentScan[i][i2]=true;
                }
            }
        }
    }
    
}
