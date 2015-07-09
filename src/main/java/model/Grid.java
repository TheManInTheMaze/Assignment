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
        if (xSize <= 0 || ySize <= 0 || nbMines < 0 || nbMines >= xSize * ySize)
            throw new IndexOutOfBoundsException("Wrong grid parameters");
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

    public Grid(String input) {
        String[] rows = input.split("\n");
        int icolumn = 0;
        int irow = 0;
        int nbMines = 0;
        for (String row : rows) {
            String[] squares = row.split(" ");
            if (grid == null) grid = new Square[rows.length][squares.length];
            for (String square : squares) {
                grid[irow][icolumn] = new Square(irow, icolumn);
                if (square.equals("*")) {
                    grid[irow][icolumn].setMine(true);
                    nbMines++;
                } else if (square.equals("r")) {
                    grid[irow][icolumn].setStatus(ISquare.SquareStatus.REVEALED);
                }
                icolumn++;
            }
            this.xSize = icolumn;
            icolumn = 0;
            irow++;
        }
        this.ySize = irow;
        assignNumbersToSquares();
        this.nbMines = nbMines;
        this.status = GameStatus.ONGOING;

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

    private boolean isInField(int xPos, int yPos) {
        return xPos >= 0 && yPos < ySize && yPos >= 0 && xPos < xSize;

    }

    private GameStatus firstClic(int xPos, int yPos) {
        placeMines(xPos, yPos);
        status = GameStatus.ONGOING;
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

    private void revealGrid(int xPos, int yPos) {
        for (int i = xPos - 1; i <= xPos + 1; i++)
            for (int j = yPos - 1; j <= yPos + 1; j++) {
                if (isInField(i, j) && !(i == xPos && j == yPos)) {
                    ISquare square = grid[j][i];
                    if (square.hasMine()) return;
                    if (square.getStatus() == ISquare.SquareStatus.COVERED) {
                        square.setStatus(ISquare.SquareStatus.REVEALED);
                        if (square.getAdjacentMines() == 0)
                            revealGrid(i, j);
                    }
                }
            }
    }

    private void checkGameWon() {
        int countCovered = 0;
        for (ISquare[] rows : grid) {
            for (ISquare square : rows) {
                if (square.getStatus() == ISquare.SquareStatus.COVERED) {
                    countCovered++;
                }
            }
        }
        if (countCovered == nbMines) {
            status = GameStatus.WIN;
        }
    }

    private void assignNumbersToSquares() {
        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {
                assignNumbersToSquares(i, j);
            }
        }
    }

    private void assignNumbersToSquares(int xPos, int yPos) {
        int nbAdjMines = 0;
        for (int i = xPos - 1; i <= xPos + 1; i++)
            for (int j = yPos - 1; j <= yPos + 1; j++) {
                if (isInField(i, j) && !(i == xPos && j == yPos)) {
                    if (grid[i][j].hasMine())
                        nbAdjMines++;
                }
            }
        grid[xPos][yPos].setAdjacentMines(nbAdjMines);
    }

    public GameStatus clic(int xPos, int yPos) {
        if (!isInField(xPos, yPos)) return null;
        if (status.equals(GameStatus.STARTED)) {
            return firstClic(xPos, yPos);
        }
        ISquare.SquareStatus squareStatus = grid[yPos][xPos].clic();
        if (squareStatus == ISquare.SquareStatus.EXPLODED) {
            status = GameStatus.FAIL;
            return status;
        }
        if (grid[yPos][xPos].getAdjacentMines() == 0)
            revealGrid(xPos, yPos);
        checkGameWon();
        return status;
    }

    public String toString() {
        StringBuilder ret = new StringBuilder();
        for (ISquare[] rows : grid) {
            for (ISquare square : rows) {
                switch (square.getStatus()) {
                    case COVERED:
                        if (square.hasMine()) ret.append("*");
                        else ret.append(".");
                        break;
                    case REVEALED:
                        ret.append("r");
                        break;

                }
                ret.append(" ");
            }
            ret.append("\n");
        }
        return ret.toString();

    }

    public boolean compareTo(IGrid field) {
        ISquare[][] secondState = field.getGrid();
        if (grid.length != secondState.length) return false;
        if (grid.length != 0 && grid[0].length != secondState[0].length)
            return false;
        for (int j = 0; j < grid.length; j++) {
            for (int i = 0; i < grid[0].length; i++) {
                if (grid[j][i].getStatus() != secondState[j][i].getStatus())
                    return false;
            }
        }
        return true;
    }

}
