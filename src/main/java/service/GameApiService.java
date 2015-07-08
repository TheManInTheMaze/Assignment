package service;

import model.interfaces.IGrid;

import javax.inject.Inject;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 * Created by pcorentin on 08/07/2015.
 */
public class GameApiService {

    @Inject
    private GameService gameService;

    @Produces({"application/json"})
    @GET
    public IGrid startGame(@QueryParam("xSize") @DefaultValue("3") final int xSize,
                           @QueryParam("ySize") @DefaultValue("3") final int ySize,
                           @QueryParam("nbMines") @DefaultValue("3") final int nbMines) {
        return gameService.newGame(xSize, ySize, nbMines);
    }

    @Produces({"application/json"})
    @GET
    public IGrid clic(@QueryParam("xPos") @DefaultValue("-1") final int xPos,
                      @QueryParam("yPos") @DefaultValue("-1") final int yPos) {

        return gameService.clic(xPos, yPos);
    }

}
