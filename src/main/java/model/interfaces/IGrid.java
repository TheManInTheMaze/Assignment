package model.interfaces;

/**
 * Created by pcorentin on 07/07/2015.
 */
public interface IGrid {
    GameStatus clic(int xPos, int yPos);

    boolean compareTo(IGrid grid);

    ISquare[][] getGrid();
    enum GameStatus {
        STARTED, ONGOING, WIN, FAIL
    }


}
