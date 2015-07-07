package model;

import model.interfaces.IGrid;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by pcorentin on 07/07/2015.
 */
public class GridTest {
    private final int MAXROWSIZE = 10;
    private final int MAXCOLUMNSIZE = 10;



    @Test
    public void testGetGrid() throws Exception {


        // EXPECTED TEST CASE
        for (int rowSize = 1; rowSize < MAXROWSIZE; rowSize++) {
            for (int columnSize = 1; columnSize < MAXCOLUMNSIZE; columnSize++) {
                for (int nbMines = 0; nbMines < rowSize * columnSize; nbMines++) {
                    Grid grid = new Grid(rowSize, columnSize, nbMines);
                    // to trigger mine positioning
                    grid.clic(0, 0);
                    Square[][] actualGrid = (Square[][]) grid.getGrid();
                    assertSame(nbMines, getCntMines(actualGrid));
                }
            }
        }

        // TEST WRONG INPUTS
        assertNull(new Grid(-1, 3, 2));
        assertNull(new Grid(4, -1, 2));
        assertNull(new Grid(2, 3, -1));
        assertNull(new Grid(0, 1, 0));
        assertNull(new Grid(1, 0, 0));


        //TEST nbMines too big
        assertNull(new Grid(1, 1, 1));
        assertNull(new Grid(1, 1, 2));

    }

    private int getCntMines(Square[][] actualGrid) {
        int cntMines = 0;
        for (Square[] rows : actualGrid) {
            for (Square square : rows) {
                if (square.isHasMine()) {
                    cntMines++;
                }
            }
        }
        return cntMines;
    }


    @Test
    public void testFirstClic() throws Exception {
        Grid grid = new Grid(3, 3, 2);
        assertSame(IGrid.GameStatus.STARTED, grid.getStatus());

        Square[][] actualGrid = (Square[][]) grid.getGrid();
        int previousCntMines = getCntMines(actualGrid);
        grid.clic(1, 1);
        assertNotEquals(previousCntMines, actualGrid);
        // WE SHALL NEVER LOOSE ON FIRST CLIC
        assertSame(IGrid.GameStatus.ONGOING, grid.getStatus());


    }

    @Test
    public void testWinningGame() throws Exception {

    }


    @Test
    public void testLosingGame() throws Exception {

    }

    @Test
    public void testSquareNumbers() {

    }


}