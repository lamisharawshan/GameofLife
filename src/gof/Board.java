/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gof;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

/**
 * Represents a game board that has grid cells.
 * This class is different from ConwayGameOfLife class
 * where the action lister does not need to be embedded in each cell in a grid.
 * @author Yoon Jin Park
 * @version 2.3
 * @since 2.0
 */
class Board extends JPanel {

    private static final int gap = 1;
    private static final Color bg = new Color(0, 102, 204);
    private static final int cellSize = 20;

    /**
     * Takes in grid cells and the size of the grid and
     * visualize and display grid cells.
     * This is a constructor method for GameBoard class.
     * @param generation the collection of the state of the cells (to be specific, alive or dead)
     * @param side the width and height of grid cells on a game board
     */
    Board(int[][] generation, int side)
    {
        JPanel [][] placeHolder = new JPanel[side][side];
        setPreferredSize(new Dimension(22*side, 22*side));
        setBackground(bg);
        setLayout(new GridLayout(side,side,gap,gap));
        for (int i=0; i < side; ++i)
        {
            for (int j=0; j < side; ++j)
            {
                placeHolder[i][j] = new JPanel();
                placeHolder[i][j].setBackground(Color.black);
                add(placeHolder[i][j]);
                final Cell cell = new Cell(i, j, cellSize);
                cell.setStatus(generation[i][j]);
                if (cell.getStatus()==1)
                {
                    placeHolder[i][j].setBackground(new Color(204, 204, 0));
                }
            }
        }
    }
}
