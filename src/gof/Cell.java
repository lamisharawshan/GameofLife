/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gof;

/**
 *
 * @author 20204930
 */
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;

/**
 * Creates a cell to possess the information about the position of the cell in a grid (both x coordinate and y coordinate),
 * the state of the cell (alive or dead). It also has two setter methods to change the state of the cell.
 * Cell class inherits from JPanel class.
 * @author Yoon Jin Park
 * @version 2.3
 * @since 1.0
 */
public class Cell extends JPanel {
    private int xPos;
    private int yPos;
    private int cellStatus;

    /**
     * Initialises and builds Cell class. This is a constructor method for Cell class.
     * @param x the position of height coordinate
     * @param y the position of width coordinate
     * @param cellSize the size of sides (both height and width) for a cell object.
     */
    Cell(int x, int y, int cellSize)
    {
        xPos = x;
        yPos = y;
        cellStatus = 0;
        setBackground(new Color(0, 102, 204));
        setPreferredSize(new Dimension(cellSize, cellSize));
    }

    /**
     * Returns the position of x coordinate of a Cell object.
     * Getter method of Cell class
     * @return the position of x coordinate
     */
    public int getX()
    {
        return xPos;
    }
    /**
     * Returns the position of y coordinate of a Cell object.
     * Getter method of Cell class
     * @return the position of y coordinate
     */
    public int getY()
    {
        return yPos;
    }
    /**
     * Returns the state of a Cell object to display whether the cell is alive or not.
     * Getter method of Cell class
     * @return the state of a specific position of a cell (alive or dead)
     */
    int getStatus()
    {
        return cellStatus;
    }
    /**
     * Changes the state of a Cell object to indicate whether the cell is alive or not.
     * If the cell was alive, then it dies and vice versa.
     * Setter method of Cell class
     */
    void toggleStatus()
    {
        if (cellStatus==0)
        {
            cellStatus=1;
        }
        else
        {
            cellStatus=0;
        }
    }
    /**
     * Changes the state of a Cell object to indicate whether the cell is alive or not.
     * The state of a Cell object is passed through a parameter.
     * Setter method of Cell class
     * @param aliveOrNot    integer to display whether the cell is alive or not. If alive,
     *                      then the state is 1. If dead, the state is represented by 0.
     */
    void setStatus(int aliveOrNot)
    {
        cellStatus = aliveOrNot;
    }
}

