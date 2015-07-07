package model;

import org.junit.Test;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

/**
 * Created by pcorentin on 07/07/2015.
 */
public class GridTest {
    private final int MAXROWSIZE = 10;
    private final int MAXCOLUMNSIZE = 10;


    @Test
    public void testGetStatus() throws Exception {

    }

    @Test
    public void testSetStatus() throws Exception {

    }

    @Test
    public void testGetGrid() throws Exception {


        // EXPECTED TEST CASE
        for (int rowSize = 1; rowSize < MAXROWSIZE; rowSize++) {
            for (int columnSize = 1; columnSize < MAXCOLUMNSIZE; columnSize++) {
                for (int nbMines = 0; nbMines < rowSize * columnSize; nbMines++) {
                    Grid grid = new Grid(rowSize, columnSize, nbMines);
                    // to trigger mine positions
                    grid.clic(0, 0);
                    Square[][] actualGrid = (Square[][]) grid.getGrid();
                    int cntMines = 0;
                    for (Square[] rows : actualGrid) {
                        for (Square square : rows) {
                            if (square.isHasMine()) {
                                cntMines++;
                            }
                        }
                    }
                    assertSame(nbMines, cntMines);
                }
            }
        }

        // TEST WRONG INPUTS
        assertNull(new Grid(-1, 3, 2));
        assertNull(new Grid(4, 1, 2));
        assertNull(new Grid(2, 3, -1));
        assertNull(new Grid(0, 1, 0));
        assertNull(new Grid(1, 0, 0));


        //TEST nbMines too big
        assertNull(new Grid(1, 1, 1));
        assertNull(new Grid(1, 1, 2));


    }

    @Test
    public void testSetGrid() throws Exception {

    }

    @Test
    public void testGetNbMines() throws Exception {

    }

    @Test
    public void testSetNbMines() throws Exception {

    }

    @Test
    public void testClic() throws Exception {

    }
}