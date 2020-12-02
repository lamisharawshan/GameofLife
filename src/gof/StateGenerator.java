package gof;


/**
 * Represents the survival rules for a cell state (both alive or dead).
 */
class StateGenerator
{
    /**
     * Generates a new generation of collective states with the rules for a survival
     * with the original generation.
     
     */
    static int[][] stateGenerator(int[][] generation, int side)
      {
          int paddedSide = side+2;
          int [][] prevGeneration = new int[paddedSide][paddedSide];
          for (int i=0; i < paddedSide; ++i)
          {
            for (int j=0; j < paddedSide; ++j)
            {
                if (i==0 || j==0 || i==paddedSide-1 || j==paddedSide-1)
                {
                    prevGeneration[i][j] = 0;
                }
                else
                {
                    prevGeneration[i][j] = generation[i-1][j-1];
                }
            }
          }
          generation = evolutionProcess(prevGeneration, paddedSide);
          return generation;
      }

    /**
     * Returns the number alive neighbors of a cell
     * @param grid -- the generation of collective cells, the state of which neighbors (adjacent cells)
     *             need to be counted
     * @param i -- the position of height coordinate
     * @param j -- the position of width coordinate
     */
    private static int countAliveNeighbours(int grid[][], int i, int j)
    {
        int aliveNeighbours=0;

        for (int width = i-1; width <= i+1; ++width)
        {
            for (int height = j-1; height <= j+1; ++height)
            {
                if (grid[width][height]==1)
                {
                    ++aliveNeighbours;
                }
            }
        }
        return aliveNeighbours;
    }

    /**
     * Evaluates and returns whether a specific cell can survive or not with the rules.
     * The rules of a survival consist of the four conditions below:
     *Any live cell with fewer than two live neighbors dies from isolation.
     *Any live cell with two or three live neighbors lives on to the next generation.
     *Any live cell with more than three live neighbors dies from overcrowding.
     *Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
     */
    private static int gameOfLifeRules(int aliveNeighbours, int alive)
    {
        
        int aliveOrNot = alive;
        //Any live cell with fewer than two live neighbors dies from isolation.
        if (aliveNeighbours < 2 && alive == 1)
        {
            aliveOrNot = 0;
        }
        //Any live cell with more than three live neighbors dies from overcrowding.
        else if (aliveNeighbours > 3 && alive == 1)
        {
            aliveOrNot = 0;
        }
        //Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
        else if (aliveNeighbours == 3 && alive == 0)
        {
            aliveOrNot = 1;
        }
        return aliveOrNot;
    }

    /**
     * Generates the new collective states based on rules.
     */
    private static int[][] evolutionProcess(int[][] originalGeneration, int side)
    {
        int originalSide = side-2;
        int [][] nextGeneration = new int [originalSide][originalSide];

        for (int i=1; i<side-1; ++i)
        {
            for (int j=1; j<side-1; ++j)
            {
                //get the number of neighbours alive for a cell
                int aliveNeighbours = countAliveNeighbours(originalGeneration, i, j);
                aliveNeighbours -= originalGeneration[i][j];
                //go through the rules to find out whether a cell survives or not
                int aliveOrNot;
                aliveOrNot = gameOfLifeRules(aliveNeighbours, originalGeneration[i][j]);
                //assign the result to a cell
                nextGeneration[i-1][j-1] = aliveOrNot;
            }
        }
        return nextGeneration;
    }
}
