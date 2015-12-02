// Implements a 2-D array of characters
import java.util.Arrays;

public class CharMatrix
{
  // Instance variables:
  private char[][] grid=new char[33][33];
  
 
  // Constructor: creates a grid with dimensions rows, cols,
  // and fills it with spaces
  public CharMatrix(int rows, int cols)
  {
     grid= new char[rows][cols];
  }

  // Constructor: creates a grid with dimensions rows , cols ,
  // and fills it with the fill  character
  public CharMatrix(int rows, int cols, char fill)
  {
     grid= new char[rows][cols];
    for (int i=0; i<rows;i++)
    {
        for (int i2=0; i2<cols;i2++)
        {
            grid[i][i2]=fill;
        }
    }
    printGrid(grid);
  }

  // Returns the number of rows in grid
  public int numRows()
  {
    return grid.length;
  }

  // Returns the number of columns in grid
  public int numCols()
  {
    return grid[0].length;
  }

  // Returns the character at row, col location
  public char charAt(int row, int col)
  {
    return grid[row][col];
  }

  // Sets the character at row, col location to ch
  public void setCharAt(int row, int col, char ch)
  {
    grid[row][col]=ch;
  }

  // Returns true if the character at row, col is a space,
  // false otherwise
  public boolean isEmpty(int row, int col)
  {
    return grid[row][col]==' ';
  }

  // Fills the given rectangle with fill  characters.
  // row0, col0 is the upper left corner and row1, col1 is the
  // lower right corner of the rectangle.
  public void fillRect(int row0, int col0, int row1, int col1, char fill)
  {
   //   printGrid(grid);
    //  System.out.println("Running fillRect");
    for (int i=row0; i<=row1;i++)
    {
        for (int i2=col0; i2<=col1;i2++)
        {
            grid[i][i2]=fill;            
        }
    }
  }

  // Fills the given rectangle with SPACE characters.
  // row0, col0 is the upper left corner and row1, col1 is the
  // lower right corner of the rectangle.
  public void clearRect(int row0, int col0, int row1, int col1)
  {
      //System.out.println("Running clearRect");
      //System.out.println(row0+" "+col0+ " second "+row1+" "+col1);     
    for (int i=row0; i<=row1;i++)
    {
        for (int i2=col0; i2<=col1;i2++)
        {
            grid[i][i2]=' ';            
        }
    }
    //printGrid(grid);
  }

  // Returns the count of all non-space characters in row 
  public int countInRow(int row)
  {
    int num=0;
    for (int i=0; i<grid.length;i++)
    {
        if (grid[row][i]==' ')
        {
            num++;
        }
    }
    return num;
  }
  public static void printGrid(char[][] grid)
  {
      for (int i=0;i<grid.length;i++)
      {
          System.out.println(Arrays.toString(grid[i]));
        }
    }
  
  // Returns the count of all non-space characters in col 
  public int countInCol(int col)
  {
    int num=0;
    int[] newx= new int[grid.length];
    for (int i=0;i<grid.length;i++)
    {
        newx[i]=grid[i][col];
    }
    for (int i=0; i<grid.length;i++)
    {
        if (newx[i]!=' ')
        {
            num++;
        }
    }
    return num;
  }
}
