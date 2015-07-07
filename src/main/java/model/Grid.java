package model;

import model.interfaces.IGrid;
import model.interfaces.ISquare;

/**
 * Created by pcorentin on 07/07/2015.
 */
public class Grid implements IGrid {

    private ISquare[][] grid;
    private int nbMines;
    private IGrid.GameStatus status = IGrid.GameStatus.STARTED;

    public Grid(int xSize, int ySize, int nbMines) {
        this.nbMines = nbMines;
        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {
                grid[j][i] = new Square(i, j);
            }
        }

    }

    //todo
    private void firstClic(int xPos, int yPos) {

        placeMines();


    }

    //todo
    private void placeMines() {


    }

    //todo
    public GameStatus clic(int xCoor, int yCoor) {
        return status;
    }
}
