/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gof;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Dimension;
import java.awt.CardLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.TimerTask;
import java.util.Timer;
/**
 *
 * @author 20204930
 */
public class GameOfLife extends JPanel
{
    private static final int gap = 1;
    private static final Color bg = new Color(102, 0, 153);
    private static int [][] passedGeneration;
    private static Timer timer;

    /**
     * Runs the main method for the Game Of Life package.
     * Displays a game board that has the graphical grid cells and user interfaces.
     */
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(GameOfLife::playGame);
    }

    /**
     * Calls and initialize user interface class of GameOfLife.
     * Each cell in the grid has MouseLister so that when the cell is clicked,
     * the state of the cell changes and updates the graphics. This action also
     * creates a collection of the state of the cells in a current grid.
     * This is a constructor method for ConwayGameOfLife class.
     * @param side  user input of both the width and height of grid cells on a initial game board
     */
    private GameOfLife(int side)
    {
        //create original generation with the size of sides
        JPanel [][] placeHolder = new JPanel[side][side];
        setPreferredSize(new Dimension(22*side, 22*side));
        passedGeneration = new int[side][side];
        setBackground(bg);
        setLayout(new GridLayout(side, side, gap, gap));

        for (int i = 0; i < side; i++) {
            for (int j = 0; j < side; j++)
            {
                placeHolder[i][j] = new JPanel();
                //placeHolder[i][j].setOpaque(true);
                placeHolder[i][j].setBackground(Color.black);
                placeHolder[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
                add(placeHolder[i][j]);

                int cellSize = 20;
                final Cell cell = new Cell(i, j, cellSize);
                placeHolder[i][j].addMouseListener(new MouseAdapter()
                {
                    public void mouseClicked(MouseEvent e)
                    {
                        if (cell.getStatus()==0)
                        {
                            int xPos = cell.getX();
                            int yPos = cell.getY();
                            placeHolder[xPos][yPos].setBackground(new Color(204, 204, 0));
                        }
                        else
                        {
                            int xPos = cell.getX();
                            int yPos = cell.getY();
                            placeHolder[xPos][yPos].setBackground(Color.black);
                        }
                        cell.toggleStatus();
                        createOriginalGeneration(passedGeneration, cell);
                    }
                });
            }
        }
    }

    /**
     * Registers and generates a collection of the state of cells in the current grid with the user input
     * @param generation the collection of the state of the cells (to be specific, alive or dead)
     * @param cell Cell object having the information of x and y coordinates and the state of the cell
     */
    private void createOriginalGeneration(int[][] generation, Cell cell)
    {
        int xPosition = cell.getX();
        int yPosition = cell.getY();
        generation[xPosition][yPosition] = cell.getStatus();
    }

    /**
     * Cancel the auto scheduled timer when pause button is pressed.
     */
    private static void timerPause()
    {
        timer.cancel();
    }

    /**
     * 
     */
    private static void timerRestart()
    {
        timer = new Timer();
    }

    
    private static void playGame()
    {
        JFrame frame = new JFrame("Game of Life");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel mainPanel;
        mainPanel = new JPanel(new CardLayout());
        mainPanel.setPreferredSize(new Dimension(500, 500));
        CardLayout cardLayout = (CardLayout) mainPanel.getLayout();

        // Creating start button and setting an initial grid to obtain user input with mouse click
        //JButton startButton = new JButton("Click to start on Conway's game of life!");
        JPanel startPanel = new JPanel();
        String startMenu = "Start Menu";
        startPanel.setLayout(new GridBagLayout());
        GridBagConstraints s = new GridBagConstraints();
        mainPanel.add(startPanel, startMenu);
        cardLayout.show(mainPanel, startMenu);
            int side=10;
            if (side >0)
            {
                
                    JPanel gamePanel = new JPanel();
                    String firstMenu = "First Menu";
                    JPanel boardPanel = new JPanel();
                    String nextMenu = "Game Board";
                    gamePanel.setLayout(new GridBagLayout());
                    boardPanel.setLayout(new GridBagLayout());
                    GameOfLife grids = new GameOfLife(side);
                    gamePanel.add(grids);

                    //add option buttons to option panel, which is added to game panel
                    JPanel optionPanel = new JPanel();
                    optionPanel.setLayout(new GridBagLayout());
                    JButton playButton = new JButton("Play");
                    JButton quitButton = new JButton("Quit");

                    GridBagConstraints c = new GridBagConstraints();
                    c.fill = GridBagConstraints.HORIZONTAL;
                    c.gridx = 0;
                    c.gridy = 1;
                    optionPanel.add(playButton, c);
                    c.gridx = 1;
                    c.gridx = 2;
                    optionPanel.add(quitButton, c);
                    c.gridx = 0;
                    c.gridy = 2;
                    c.gridx = 1;
                    c.gridx = 2;
                    c.gridx = 0;
                    c.gridy = 2;
                    gamePanel.add(optionPanel, c);
                    mainPanel.add(gamePanel, firstMenu);
                    cardLayout.show(mainPanel, firstMenu);

                    //when a play button clicked, the user input is passed on to apply the rules of a survival,
                    //and the auto scheduled timer is instantiated to display next generation every one second
                    playButton.addActionListener(e1 -> {
                            passedGeneration = StateGenerator.stateGenerator(passedGeneration, side);
                            Board updatedGrids = new Board(passedGeneration, side);
                            boardPanel.add(updatedGrids);
                            playButton.setEnabled(false);
                            boardPanel.add(optionPanel, c);
                            mainPanel.add(boardPanel, nextMenu);
                            cardLayout.show(mainPanel, nextMenu);

                            timerRestart();
                            TimerTask myTask = new TimerTask()
                            {
                                @Override
                                public void run()
                                {
                                    boardPanel.removeAll();
                                    passedGeneration = StateGenerator.stateGenerator(passedGeneration, side);
                                    Board updatedGrids = new Board(passedGeneration, side);
                                    boardPanel.add(updatedGrids);
                                    boardPanel.add(optionPanel, c);
                                    boardPanel.revalidate();
                                    mainPanel.add(boardPanel, nextMenu);
                                    cardLayout.show(mainPanel, nextMenu);
                                }
                            };
                            timer.scheduleAtFixedRate(myTask ,1000 , 1000);
                    });
                    quitButton.addActionListener(e13 -> System.exit(0));

                }
           
        //add card layout of mainPanel to the frame and display the window
        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

