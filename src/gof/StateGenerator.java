/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gof;


/**
 * Represents the rules for a survival for the state of the cells (both alive or dead).
 * @author Yoon Jin Park
 * @version 2.3
 * @since 1.0
 */
class StateGenerator
{
    /**
     * Generates a new generation of collective states with the rules for a survival
     * with the original generation.
     * @param generation the collection of the state of the cells (to be specific, alive or dead)
     * @param side the width and height of grid cells on a game board
     * @return  a new collection of the state of the cells after applying the rules for a survival
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
     * Returns the number of the states that are alive in adjacent cells.
     * Adjacent cells mean surrounding eight cells immediate to a specific cell.
     * @param grid the generation of collective cells, the state of which neighbours (adjacent cells)
     *             need to be counted
     * @param i the position of height coordinate
     * @param j the position of width coordinate
     * @return the number of the states that are alive in adjacent cells
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
     * <p>
     * Any live cell with fewer than two live neighbors dies, as if by under population.
     * </p>
     * <p>
     * Any live cell with more than three live neighbors dies, as if by overpopulation.
     * </p>
     * <p>
     * Any live cell with two or three live neighbors lives on to the next generation where it is already alive.
     * </p>
     * <p>
     * Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
     * </p>
     * @param aliveNeighbours the number of the states that are alive in adjacent cells
     * @param alive the state of a specific position of a cell (alive or dead)
     * @return a new state of a specific position of a cell (alive or dead)
     * after the rules for a survival is applied.
     */
    private static int gameOfLifeRules(int aliveNeighbours, int alive)
    {
        //Any live cell with two or three live neighbors lives on to the next generation where it is already alive.
        int aliveOrNot = alive;
        //Any live cell with fewer than two live neighbors dies, as if by under population.
        if (aliveNeighbours < 2 && alive == 1)
        {
            aliveOrNot = 0;
        }
        //Any live cell with more than three live neighbors dies, as if by overpopulation.
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
     * Organizes an evolution process by inputting the original generation of collective states
     * and returns a new generation of collective states.
     * @param originalGeneration the generation of collective cells,
     *                           the state of which neighbours (adjacent cells) will be counted
     *                           and evaluated against the rules
     * @param side  the width and height of grid cells on a game board
     * @return a new collection of the state of the cells after applying the rules for a survival
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
