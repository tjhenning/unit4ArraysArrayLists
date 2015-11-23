import java.util.Arrays;

public class Seive
{
    public static void main(String[] args)
    {
        int[] numbers=new int[121];
        for (int i=0;i<121;i++)
        {
            numbers[i]=i+1;
        }
        System.out.println(Arrays.toString(numbers));
    }
}