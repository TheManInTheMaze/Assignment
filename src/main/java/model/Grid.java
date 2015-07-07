package model;

import model.interfaces.IGrid;
import model.interfaces.ISquare;

import java.util.Random;

/**
 * Created by pcorentin on 07/07/2015.
 */
public class Grid implements IGrid {

    private ISquare[][] grid;
    private int nbMines;
    private IGrid.GameStatus status = IGrid.GameStatus.STARTED;
    private int xSize, ySize;

    public Grid(int xSize, int ySize, int nbMines) {
        this.ySize = ySize;
        this.xSize = xSize;
        grid = new Square[ySize][xSize];
        this.nbMines = nbMines;
        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {
                grid[j][i] = new Square(j, i);
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
        assignNumbersToSquares();

    }

    public int getNbMines() {
        return nbMines;
    }

    public void setNbMines(int nbMines) {
        this.nbMines = nbMines;
    }

    //todo
    private GameStatus firstClic(int xPos, int yPos) {
        placeMines(xPos, yPos);
        return clic(xPos, yPos);
    }

    // CAN BE SLOW IF MANY MINES TO PLACE
    private void placeMines(int xPos, int yPos) {
        int nbPlacedMines = 0;
        Random random = new Random();
        while (nbPlacedMines < nbMines) {
            int numCase = random.nextInt(xSize * ySize);
            int x = numCase % xSize;
            int y = numCase / xSize;
            if (x == xPos && y == yPos) continue;
            ISquare square = grid[y][x];
            if (!square.hasMine()) {
                square.setMine(true);
                nbPlacedMines++;
            }
        }
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

    public GameStatus clic(int xPos, int yPos) {
        if (status.equals(GameStatus.STARTED)) {
            return firstClic(xPos, yPos);
        }
        ISquare.SquareStatus squareStatus = grid[yPos][xPos].clic();
        if (squareStatus == ISquare.SquareStatus.EXPLODED) {
            status = GameStatus.FAIL;
            return status;
        }
        revealGrid(xPos, yPos);
        checkGameWon();
        return status;
    }
}
