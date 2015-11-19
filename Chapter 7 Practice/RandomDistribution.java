import java.util.Scanner;

public class RandomDistribution
{
    public static void main(String[] args)
    {
        Scanner s=new Scanner(System.in);        
        System.out.print("How many numbers do you want to generate?: ");
        int number=s.nextInt();
        System.out.print("How many 'sides' does the 'die' have?: ");
        double sides=s.nextDouble();
        int[] arr= new int[number];
        for (int i=0;i<arr.length;i++)
        {
            arr[i]=(int)(Math.random()*sides);
        }
        for (int i=0;i<arr.length;i++)
        {
            System.out.println("Roll #"+i+": "+arr[i]);
        }
    }
}