package gof;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

/**
 * Represents a game board that has grid cells.
 */
class Board extends JPanel {

    private static final int gap = 1;
    private static final Color bg = new Color(128, 128, 128);
    private static final int cellSize = 20;

    /**
     * Takes in grid cells and the size of the grid and display grid cells.
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
                    placeHolder[i][j].setBackground(new Color(0, 128, 0));
                }
            }
        }
    }
}
