
public class ArrayMethods
{
    public static void main(String[] args)
    {
        int[] x= {1,2,3,4,5,6,7,8,9};
        int hold=x[0];
        x[0]=x[x.length-1];
        x[x.length-1]=hold;
        for (int i=0;i<x.length;i++)
        {
            System.out.print(x[i]+" ");
        }
        System.out.println();
        for (int i=x.length-1;i>=1;i--)
        {
            x[i]=x[i-1];
        }
        x[0]=hold;
        for (int i=0;i<x.length;i++)
        {
            System.out.print(x[i]+" ");
        }
        System.out.println();
        for (int i=0;i<x.length;i++)
        {
            if (x[i]%2==0)
            {
                x[i]=0;
            }
        }
        for (int i=0;i<x.length;i++)
        {
            System.out.print(x[i]+" ");
        }
    }
}