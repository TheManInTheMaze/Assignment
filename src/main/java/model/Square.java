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

    //todo
    public SquareStatus clic() {


        return status;
    }

}
