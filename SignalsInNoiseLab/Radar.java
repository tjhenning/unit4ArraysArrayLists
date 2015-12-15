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
    private int[] monsterLocationRow;
    private int[] monsterLocationCol;
    private int numberOfMonsters;

    // probability that a cell will trigger a false detection (must be >= 0 and < 1)
    private double noiseFraction;
    
    // number of scans of the radar since construction
    private int numScans;

    /**
     * Constructor for objects of class Radar
     * 
     * @param   rows    the number of rows in the radar grid
     * @param   cols    the number of columns in the radar grid
     */
    public Radar(int rows, int cols, boolean test)
    {
        // initialize the currentScan 2D array and the accumulator 2D array
        currentScan=new boolean[rows][cols];
        accumulator=new int[rows][cols];
        
        for (int i=0; i<rows;i++)
        {
            for (int i2=0; i2<cols;i2++)
            {
                currentScan[i][i2]=false;
            }
        }
        
        // randomly set the location of the monster (can be explicity set through the
        //  setMonsterLocation method for the unit test        
        if (!test)
        {
            Scanner s=new Scanner(System.in);
            System.out.println("How many monsters do you want there to be?: ");
            numberOfMonsters = s.nextInt();
            monsterLocationRow=new int[numberOfMonsters];
            monsterLocationCol=new int[numberOfMonsters];        
            for (int i=0;i<numberOfMonsters;i++)
            {            
                System.out.println("Set the row # for monster #"+(i+1)+": ");            
                monsterLocationRow[i] = s.nextInt();//(int)(Math.random() * rows);
                System.out.println("Set column # for monster #"+(i+1)+": ");
                monsterLocationCol[i] = s.nextInt();//(int)(Math.random() * cols);
            }
            
            System.out.println("Are the monster(s) moving?(y?): ");
            if (s.next().equals("y"))
            {
                isMoving=true;
            }
            System.out.println("What should the noise fraction be, as a decimal?: ");
            setNoiseFraction(s.nextDouble());
        }
        else
        {
            numberOfMonsters=1;
            monsterLocationRow=new int[1];
            monsterLocationCol=new int[1]; 
            monsterLocationRow[0]=4;
            monsterLocationCol[0]=4;
            isMoving=false;
        }
        //noiseFraction = 0.05;
        numScans= 0;
    }
    
    /** returns number of monsters.
     * 
     * @return number of monsters
     */
    public int getNumberOfMonsters()
    {
        return numberOfMonsters;
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
        double mod=0;
        for (int i=0; i<currentScan.length;i++)
        {
            for (int i2=0; i2<currentScan[0].length;i2++)
            {
                currentScan[i][i2]=false;
            }
        }
        if (isMoving)
        {
            for (int i=0;i<numberOfMonsters;i++)
            { 
                mod=Math.random();
                if (mod>.77)
                {
                    if (monsterLocationRow[i]==currentScan.length-1)
                    {
                        monsterLocationRow[i]=0;
                    }
                    else
                    {
                        monsterLocationRow[i]++;
                    }
                }
                else if (mod>.6)
                {
                    if (monsterLocationRow[i]==0)
                    {
                        monsterLocationRow[i]=currentScan.length-1;
                    }
                    else
                    {
                        monsterLocationRow[i]--;
                    }
                }
                else if (mod>.5)
                {
                    //
                }
                else if (mod>.2)
                {
                     if (monsterLocationCol[i]==currentScan.length-1)
                    {
                        monsterLocationCol[i]=0;
                    }
                    else
                    {
                        monsterLocationCol[i]++;
                    }
                }
                else
                {
                     if (monsterLocationCol[i]==0)
                    {
                        monsterLocationCol[i]=currentScan.length-1;
                    }
                    else
                    {
                        monsterLocationCol[i]--;
                    }
                }
            }
        }       
        
        for (int i=0;i<numberOfMonsters;i++)
        { 
                currentScan[monsterLocationRow[i]][monsterLocationCol[i]]=true;
           }    
        injectNoise();        
         if(isMoving){
                for (int i=0; i<currentScan.length&&numScans!=0;i++)
                {
                    for (int i2=0; i2<currentScan[0].length;i2++)
                    {
                        if (accumulator[i][i2]==1)
                        {
                            
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
    public void setMonsterLocation(int row, int col, int num)
    {
        // remember the row and col of the monster's location
        monsterLocationRow[num] = row;
        monsterLocationCol[num] = col;
        
        // update the radar grid to show that something was detected at the specified location
        currentScan[row][col] = true;
    }
    
    /**
     * Sets the accumulator to reflect the results of the first scan.
     * 
     */
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
     * Returns whether the monster is moving
     * 
     * @return whether the monster is moving
     */
    public boolean getIsMoving()
    {
        return isMoving;
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
     * Returns the location that has been positive the most times/where the monster is detected
     * 
     * @return where the monsters are detected in int[][] form with the first being which monster and the second being the x y postition.
     */
    public int[][] getDetected()
    {
        
        
        int howMany=0;
        int[][] retrn=new int[numberOfMonsters][2];
        if (isMoving)       
        {            
            for (int i=0; i<currentScan.length;i++)
            {
                for (int i2=0; i2<currentScan[0].length;i2++)
                {
                    if (accumulator[i][i2]==1)
                    {
                        if (howMany==numberOfMonsters)
                        {
                            retrn[0][0]=-1;
                            return retrn;
                        }
                        retrn[howMany][0]=i;
                        retrn[howMany][1]=i2;
                        howMany++;
                    }
                }
            }            
        }
        else 
        {
                
            for (int i=0; i<currentScan.length;i++)
            {
                for (int i2=0; i2<currentScan[0].length;i2++)
                {
                    if (accumulator[i][i2]==numScans)
                    {
                        if (howMany==numberOfMonsters)
                        {
                            retrn[0][0]=-1;
                            return retrn;
                        }
                        retrn[howMany][0]=i;
                        retrn[howMany][1]=i2;
                        howMany++;
                    }                    
                }
            }                     
        }
        return retrn;
    }

    /**
     * Returns location of a monster.
     * 
     * @param ID number of which monster you want to get the location for.
     * @return the location of the monster as a 2-digit array.
     */
    public int[] getMonsterLocation(int num)
    {
        return new int[] {monsterLocationRow[num],monsterLocationCol[num]};
        //return r;
    }
    
    /**
     * Returns string containing ID for each cardinal direction contatining a positive on the scan.
     * 
     * @param x x value to check
     * y y value to check
     * @return a string that represents which came up as true. For instance, if the string is "ur" then Up and Right have monsters.
     */
    public String checkAround(int x, int y)
    {        
        String retrn=new String("");
        int xchangen=x-1;
        int xchangep=x+1;
        int ychangen=y-1;
        int ychangep=y+1;
        int row=100;
        if (x==0)
        {
            xchangen=row-1;
        }
        else if (x==row-1)
        {
            xchangep=0;
        }     
        if (y==0)
        {
            ychangen=row-1;
        }
        else if (y==row-1)
        {
            ychangep=0;
        }      
        if (currentScan[xchangen][y])
            {
                retrn+="l";
            }
        if (currentScan[x][ychangen])
        {
            retrn+="u";
        }
        if (currentScan[x][y])
        {
            retrn+="n";
        }
        if (currentScan[x][ychangep])
        {
            retrn +="d";
        }
        if (currentScan[xchangep][y])
        {
            retrn+="r";
        }
        return retrn;
    }
    /**
     * Sets cells as falsely triggering detection based on the specified probability
     * 
     */
    private void injectNoise()
    {
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
