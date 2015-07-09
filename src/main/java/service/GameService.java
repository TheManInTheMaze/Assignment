package service;

import model.Grid;
import model.interfaces.IGrid;

/**
 * Created by pcorentin on 08/07/2015.
 */
public class GameService {

    private IGrid grid;

    public ResponseWrapper newGame(int xSize, int ySize, int nbMines) {
        try {
            grid = new Grid(xSize, ySize, nbMines);
        } catch (IndexOutOfBoundsException ex) {
            return new ResponseWrapper(null, "500");
        }
        return new ResponseWrapper(grid, "200");
    }

    public ResponseWrapper clic(int xPos, int yPos) {
        try {
            grid.clic(xPos, yPos);
        } catch (IndexOutOfBoundsException ex) {
            return new ResponseWrapper(grid, "500");
        } catch (NullPointerException ex) {
            return new ResponseWrapper(grid, "503");
        }
        return new ResponseWrapper(grid, "200");
    }
}
