package service;

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
    @POST
    public ResponseWrapper startGame(@FormParam("xSize") @DefaultValue("3") final int xSize,
                           @FormParam("ySize") @DefaultValue("3") final int ySize,
                           @FormParam("nbMines") @DefaultValue("3") final int nbMines) {
        return gameService.newGame(xSize, ySize, nbMines);
    }

    @Produces({"application/json"})
    @GET
    public ResponseWrapper clic(@QueryParam("xPos") @DefaultValue("-1") final int xPos,
                      @QueryParam("yPos") @DefaultValue("-1") final int yPos) {

        return gameService.clic(xPos, yPos);
    }

}
