// Implements a 2-D array of characters

public class CharMatrix
{
  // Instance variables:
  private char[][] grid=new char[1][1];
  private int rows;
  private int cols;
  // Constructor: creates a grid with dimensions rows, cols,
  // and fills it with spaces
  public CharMatrix(int rows1, int cols1)
  {
    rows=rows1;
    cols=cols1;
    char[][] grid= new char[rows][cols];
  }

  // Constructor: creates a grid with dimensions rows , cols ,
  // and fills it with the fill  character
  public CharMatrix(int rows1, int cols1, char fill)
  {
    rows=rows1;
    cols=cols1;
    char[][] grid= new char[rows][cols];
    for (int i=0; i<rows;i++)
    {
        for (int i2=0; i2<cols;i2++)
        {
            grid[i][i2]=fill;
        }
    }
  }

  // Returns the number of rows in grid
  public int numRows()
  {
    return rows;
  }

  // Returns the number of columns in grid
  public int numCols()
  {
    return cols;
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
    for (int i=0; i<rows;i++)
    {
        for (int i2=0; i2<cols;i2++)
        {
            if (i>row0&&i2<col0&&i<row1&&i2>col1)
            {
                grid[i][i2]=fill;
            }
        }
    }
  }

  // Fills the given rectangle with SPACE characters.
  // row0, col0 is the upper left corner and row1, col1 is the
  // lower right corner of the rectangle.
  public void clearRect(int row0, int col0, int row1, int col1)
  {
    for (int i=0; i<rows;i++)
    {
        for (int i2=0; i2<cols;i2++)
        {
            if (i>row0&&i2<col0&&i<row1&&i2>col1)
            {
                grid[i][i2]=' ';
            }
        }
    }
  }

  // Returns the count of all non-space characters in row 
  public int countInRow(int row)
  {
    int num=0;
    for (int i=0; i<rows;i++)
    {
        if (grid[row][i]==' ')
        {
            num++;
        }
    }
    return num;
  }

  // Returns the count of all non-space characters in col 
  public int countInCol(int col)
  {
    int num=0;
    for (int i=0; i<rows;i++)
    {
        if (grid[i][col]==' ')
        {
            num++;
        }
    }
    return num;
  }
}
