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

    public GameStatus getStatus() {
        return status;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }

    public ISquare[][] getGrid() {
        return grid;
    }

    public void setGrid(ISquare[][] grid) {
        this.grid = grid;
    }

    public int getNbMines() {
        return nbMines;
    }

    public void setNbMines(int nbMines) {
        this.nbMines = nbMines;
    }

    //todo
    private GameStatus firstClic(int xPos, int yPos) {

        placeMines();
        return clic(xPos, yPos);
    }

    //todo
    private void placeMines() {

    }

    //todo
    private void revealGrid(int xPos, int yPos) {

    }

    //todo
    private void checkGameWon() {
    }

    //todo
    private void assignNumbersToSquares() {
    }

    //todo
    public GameStatus clic(int xPos, int yPos) {
        return status;
    }
}
