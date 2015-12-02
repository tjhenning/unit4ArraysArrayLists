import java.util.ArrayList;
import java.util.Scanner;

/**
 * Write a description of class BestCustomer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Store
{
    /** description of instance variable x (add comment for each instance variable) */
    private ArrayList<Customer> customers=new ArrayList<Customer>();

    public static void main(String[] args)
   {
       Scanner s=new Scanner(System.in);
       Store store=new Store();
       System.out.println("Input the name of the first customer. ");
       String n1=s.next();
       System.out.println("Input the sales price of the first customer. ");
       store.addSale(n1,s.nextDouble());       
       System.out.println("Input the name of the second customer. ");
       n1=s.next();
       System.out.println("Input the sales price of the second customer. ");
       store.addSale(n1,s.nextDouble());
       System.out.println("Input the name of the third customer. ");
       n1=s.next();
       System.out.println("Input the sales price of the third customer. ");
       store.addSale(n1,s.nextDouble());
       System.out.println("Thanks to "+store.nameOfBest()+" for being our best customer today!");
       
       
    }
    public void addSale(String customerName, double amount)
    {
       Customer c1=new Customer(customerName,amount);
       customers.add(c1);
    }
    public String nameOfBest()
    {
        double sentinal=0;
        int reminder=-1;
        for (int i=0;i<customers.size();i++)
        {
            customers.get(i).getSaleSize();
            if (sentinal<customers.get(i).getSaleSize())
            {
                sentinal=customers.get(i).getSaleSize();
                reminder=i;                
            }       
        }
        return customers.get(reminder).getName();
    }
}
