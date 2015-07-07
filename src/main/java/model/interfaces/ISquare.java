package model.interfaces;

/**
 * Created by pcorentin on 07/07/2015.
 */
public interface ISquare {
    SquareStatus clic();

    boolean hasMine();

    void setMine(boolean mine);

    SquareStatus getStatus();

    void setStatus(SquareStatus status);

    int getAdjacentMines();

    void setAdjacentMines(int adjacentMines);


    enum SquareStatus {
        REVEALED, COVERED, EXPLODED
    }
}
