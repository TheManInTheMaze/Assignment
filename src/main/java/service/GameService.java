package service;

import model.Grid;
import model.exceptions.WrongParameterException;
import model.interfaces.IGrid;
import org.springframework.stereotype.Service;

/**
 * Created by pcorentin on 08/07/2015.
 */
@Service
public class GameService {

    private IGrid grid;

    public IGrid newGame(int xSize, int ySize, int nbMines) throws WrongParameterException {
        grid = new Grid(xSize, ySize, nbMines);
        return grid;
    }

    public IGrid clic(int xPos, int yPos) throws WrongParameterException {
        grid.clic(xPos, yPos);
        return grid;
    }
}
