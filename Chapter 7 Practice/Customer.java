

/**
 * Write a description of class Customer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Customer
{
    /** description of instance variable x (add comment for each instance variable) */
    private double saleSize;
    private String name="";

    public Customer(String name1, double saleSize1)
    {
        name=name1;
        saleSize=saleSize1;        
    }

    public double getSaleSize()
    {
        return saleSize;
    }

    public String getName()
    {
        return name;
    }
}
