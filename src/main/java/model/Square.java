package model;

import model.interfaces.ISquare;

/**
 * Created by pcorentin on 07/07/2015.
 */
public class Square implements ISquare {
    private int xPos, yPos;
    private boolean hasMine = false;
    private ISquare.SquareStatus status = ISquare.SquareStatus.COVERED;
    private int adjacentMines = 0;

    public Square(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public boolean isHasMine() {
        return hasMine;
    }

    public void setHasMine(boolean hasMine) {
        this.hasMine = hasMine;
    }

    public SquareStatus getStatus() {
        return status;
    }

    public void setStatus(SquareStatus status) {
        this.status = status;
    }

    public int getAdjacentMines() {
        return adjacentMines;
    }

    public void setAdjacentMines(int adjacentMines) {
        this.adjacentMines = adjacentMines;
    }

    //todo
    public SquareStatus clic() {


        return status;
    }

}
