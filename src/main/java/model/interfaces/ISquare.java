package model.interfaces;

/**
 * Created by pcorentin on 07/07/2015.
 */
public interface ISquare {
    SquareStatus clic();


    enum SquareStatus {
        REVEALED, COVERED, EXPLODED
    }
}
