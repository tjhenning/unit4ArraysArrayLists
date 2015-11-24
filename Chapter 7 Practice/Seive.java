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
        for (int i=2;i<8;i++)
        {
            for (int i2=0;i2<numbers.length;i2++)
            {
                if (numbers[i2]%i==0)
                {
                    numbers=ArrayMethods.remove(numbers , i2);                    
                }
            }
        }
        System.out.println("Primes under 122 and more than 10: "+Arrays.toString(numbers));
    }
}