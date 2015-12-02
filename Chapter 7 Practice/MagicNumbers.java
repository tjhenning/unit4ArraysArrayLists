import java.util.Scanner;
import java.util.Arrays;

/**
 * Write a description of class MagicNumbers here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MagicNumbers
{
     public static void main(String[] args)
    {
        Scanner s = new Scanner(System.in);
        int[][] square=new int[4][4];
        for (int i=0;i<4;i++)
        {
            for (int i2=0;i2<4;i2++)
            {
                System.out.println("Input the number for index "+i+" "+i2+": ");
                square[i][i2]=s.nextInt();
            }
        }
        System.out.println(Arrays.toString(square[0]));
        System.out.println(Arrays.toString(square[1]));
        System.out.println(Arrays.toString(square[2]));
        System.out.println(Arrays.toString(square[3]));
        boolean ismagic=true;
        int k=square[0][0]+square[1][1]+square[2][2]+square[3][3];
        if (square[3][0]+square[2][1]+square[1][2]+square[0][3]==k)
        {
            for (int i=0;i<4;i++)
            {
                if (square[i][0]+square[i][1]+square[i][2]+square[i][3]!=k)
                {
                    ismagic=false;
                    break;
                }
                if (square[0][i]+square[1][i]+square[2][i]+square[3][i]!=k)
                {
                    ismagic=false;
                    break;
                }
            }
        }
        else{
            ismagic=false;
        }
        if (ismagic)
        {
            System.out.println("It is a magic square. ");
        }
        else{
             System.out.println("It is not a magic square. ");
            }
    }
}
