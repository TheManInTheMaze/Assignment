package model.interfaces;

/**
 * Created by pcorentin on 07/07/2015.
 */
public interface IGrid {
    GameStatus clic(int xCoor, int yCoor);

    enum GameStatus {
        STARTED, ONGOING, WIN, FAIL
    }


}
