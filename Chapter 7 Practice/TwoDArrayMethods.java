import java.util.Arrays;

/**
 * Write a description of class TwoDArrayMethods here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TwoDArrayMethods
{
    /** description of instance variable x (add comment for each instance variable) */

    /**
     * Default constructor for objects of class TwoDArrayMethods
     */
    public TwoDArrayMethods()
    {
        
    }
    
    public static void main(String[] args)
    {
        int[][] x= new int[3][4];
        //TwoDArrayMethods method=new TwoDArrayMethod();
        //System.out.println(Arrays.toString(x));
        
        System.out.println(Arrays.toString(getSecond(x,0)));
        
        System.out.println(Arrays.toString(getFirst(x,0)));
    }
    
    /**
     * An example of a method - replace this comment with your own
     *    that describes the operation of the method
     *
     * @pre        preconditions for the method
     *            (what the method assumes about the method's parameters and class's state)
     * @post    postconditions for the method
     *            (what the method guarantees upon completion)
     * @param    y    description of parameter y
     * @return    description of the return value
     */
    public static int[] getSecond(int[][] x, int row)
    {
        return x[row];        
    }

    public static int[] getFirst(int[][] x, int col)
    {
        int[] newx= new int[x.length];
        for (int i=0;i<x.length;i++)
        {
            newx[i]=x[i][col];
        }
        return newx;
    }
}
