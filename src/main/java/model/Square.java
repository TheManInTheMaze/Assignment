package model;

import model.interfaces.ISquare;

import static model.interfaces.ISquare.SquareStatus.COVERED;
import static model.interfaces.ISquare.SquareStatus.REVEALED;

/**
 * Created by pcorentin on 07/07/2015.
 */
public class Square implements ISquare {
    private int xPos, yPos;
    private boolean hasMine = false;
    private ISquare.SquareStatus status = COVERED;
    private int adjacentMines = 0;

    public Square(int yPos, int xPos) {
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
        if (this.hasMine) {
            status = SquareStatus.EXPLODED;
            return status;
        }
        switch (status) {
            case COVERED:
                status = REVEALED;
                break;
            default:
                break;
        }
        return status;

    }

}
