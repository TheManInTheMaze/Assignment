package model.interfaces;

/**
 * Created by pcorentin on 07/07/2015.
 */
public interface ISquare {
    SquareStatus clic();

    boolean hasMine();

    void setMine(boolean mine);


    enum SquareStatus {
        REVEALED, COVERED, EXPLODED
    }
}
