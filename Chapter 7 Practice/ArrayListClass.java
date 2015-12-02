import java.util.ArrayList;

public class ArrayListClass
{
    public static void main(String[] args)
    {
        ArrayList<String> list=new ArrayList<String>();
        list.add("233");
        list.add("5555");
        list.add("6");
        list.add("11");
        list.add("11");
        list.add("88888870");
        list.add("11");
        System.out.println(list.toString());
        for (int i=list.size()-1;i>=0;i--)
        {
            if (list.get(i).length()<3)
            {
                list.remove(i);                
            }
        }
        System.out.println(list.toString());
    }
}