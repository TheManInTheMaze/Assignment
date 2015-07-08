package service;

import model.exceptions.WrongParameterException;
import model.interfaces.IGrid;

import javax.inject.Inject;
import javax.ws.rs.*;

/**
 * Created by pcorentin on 08/07/2015.
 */

@Path("/minesweep")
public class GameApiService {

    @Inject
    private GameService gameService;

    @Produces({"application/json"})
    @GET
    public IGrid startGame(@QueryParam("xSize") @DefaultValue("3") final int xSize,
                           @QueryParam("ySize") @DefaultValue("3") final int ySize,
                           @QueryParam("nbMines") @DefaultValue("3") final int nbMines) throws WrongParameterException {
        return gameService.newGame(xSize, ySize, nbMines);
    }

    @Produces({"application/json"})
    @GET
    public IGrid clic(@QueryParam("xPos") @DefaultValue("-1") final int xPos,
                      @QueryParam("yPos") @DefaultValue("-1") final int yPos) throws WrongParameterException {

        return gameService.clic(xPos, yPos);
    }


}
