import java.util.Arrays;

public class ArrayMethods
{
    public static void main(String[] args)
    {
        int[] x= {1,2,3,4,5,6,7,8,9};
        //int hold=x[0];
        //x[0]=x[x.length-1];
        
        //int[] holdlist=new int[x.length];
        System.out.println(Arrays.toString(x));
        System.out.println(Arrays.toString(pushRight(x)));
        System.out.println(Arrays.toString(allEvensTo0(x)));
        System.out.println(Arrays.toString(setToLargestAdjacent(x)));
        System.out.println(Arrays.toString(removeMiddle(x)));
        System.out.println(Arrays.toString(putEvensFirst(x)));
        System.out.println(secondLargest(x));
        System.out.println(isInOrder(x));
        System.out.println(isAdjacentDuplicates(x));
        System.out.println(isDuplicates(x));
    }
    public static int[] pushRight(int[] x)
    {        
        int hold=x[x.length-1];
        for (int i=x.length-1;i>=1;i--)
        {
            x[i]=x[i-1];
        }
        x[0]=hold;
        return x;
    
    }
    public static int[] allEvensTo0(int[] x)
    {
        for (int i=0;i<x.length;i++)
        {
            if (x[i]%2==0)
            {
                x[i]=0;
            }
        }  
        return x;
    }
    public static int[] setToLargestAdjacent(int[] x)
    {
        int[] holdlist=new int[x.length];
        for (int i=1;i<x.length-1;i++)
        {
            if (x[i-1]>x[i+1])
            {
                holdlist[i]=x[i-1];
            }
            else
            {
                holdlist[i]=x[i+1];
            }
        }        
        holdlist[0]=x[0];
        holdlist[holdlist.length-1]=x[x.length-1];
        return holdlist;
    }
    public static int[] removeMiddle(int[] x)
    {    
        if (x.length%2==0)
        {
            x[x.length/2]=99;
            x[x.length/2 -1]=99;
        }
        else
        {
            x[x.length/2]=99;
        }
        return x;
    }
    public static int[] putEvensFirst(int[] x)
    {
        int[] holdlist=new int[x.length];
        int c=0;
        for (int i=0;i<x.length;i++)
        {
            if (x[i]%2==0)
            {
                
                holdlist[c]=x[i];
                c++;
            }
        }
        for (int i=0;i<x.length;i++)
        {
            if (x[i]%2==1)
            {
                
                holdlist[c]=x[i];
                c++;
            }
        }        
        return holdlist;
    }
    public static int secondLargest(int[] x)
    {
        int hold=0;
        int hold2=0;
        for (int i=0;i<x.length;i++)
        {
            if (hold<x[i])
            {
                hold2=hold;
                hold=x[i];
            }
        }  
        return hold2;
    }
    public static boolean isInOrder(int[] x)
    {
        int hold=-99;        
        for (int value:x)
        {
            if (!(hold<=value))
            {
                return false;                
            }
            hold=value;
        }
        return true;
    }
    public static boolean isAdjacentDuplicates(int[] x)
    {
        for (int i=1;i<x.length;i++)
        {
            if (x[i]==x[i-1])
            {
                return true;//"There is adjacent duplicates.");                
            }
        }
        return false;
    }
    public static boolean isDuplicates(int[] x)
    {        
        for (int i=0;i<x.length;i++)
        {
            for (int i2=0;i2<x.length;i2++)
            {
                if (x[i]==x[i2] && i!=i2)
                {
                    return true; //System.out.println("There is duplicates.");
                    
                }
            }
        }
        return false;
    }
}