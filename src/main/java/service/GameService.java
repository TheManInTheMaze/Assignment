package service;

import model.Grid;
import model.interfaces.IGrid;

/**
 * Created by pcorentin on 08/07/2015.
 */
public class GameService {

    private IGrid grid;

    public IGrid newGame(int xSize, int ySize, int nbMines) {
        grid = new Grid(xSize, ySize, nbMines);
        return grid;
    }

    public IGrid clic(int xPos, int yPos) {
        grid.clic(xPos, yPos);
        return grid;
    }
}
