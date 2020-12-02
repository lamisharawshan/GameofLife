package gof;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;

/**
 * Creates a cell with the position of the cell in a grid (both x coordinate and y coordinate),
 * the state of the cell (alive or dead).
 * Cell class inherits JPanel class.
 */
public class Cell extends JPanel {
    private int xPos;
    private int yPos;
    private int cellStatus;

    /**
     * Initializes and builds Cell class with it's constructor method.
     */
    Cell(int x, int y, int cellSize)
    {
        xPos = x;
        yPos = y;
        cellStatus = 0;
        setBackground(new Color(128, 128, 128));
        setPreferredSize(new Dimension(cellSize, cellSize));
    }

    /**
     * Getter method of Cell class
     */
    public int getX()
    {
        return xPos;
    }
    /**
     * Getter method of Cell class
     */
    public int getY()
    {
        return yPos;
    }
    /**
     * Getter method of Cell class
     */
    int getStatus()
    {
        return cellStatus;
    }
    /**
     * If the cell was alive, then it dies and vice versa.
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
     * Setter method of Cell class
     */
    void setStatus(int aliveOrNot)
    {
        cellStatus = aliveOrNot;
    }
}

