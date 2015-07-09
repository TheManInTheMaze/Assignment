package model;

import model.interfaces.IGrid;
import model.interfaces.ISquare;
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
        try {
            new Grid(-1, 3, 2);
            fail();
        } catch (IndexOutOfBoundsException e) {

        }
        try {
            new Grid(4, -1, 2);
            fail();
        } catch (IndexOutOfBoundsException e) {

        }
        try {
            new Grid(2, 3, -1);
            fail();
        } catch (IndexOutOfBoundsException e) {

        }
        try {
            new Grid(0, 1, 0);
            fail();
        } catch (IndexOutOfBoundsException e) {

        }
        try {
            new Grid(1, 0, 0);
            fail();
        } catch (IndexOutOfBoundsException e) {

        }


        //TEST nbMines too big
        try {
            new Grid(1, 1, 1);
            fail();
        } catch (IndexOutOfBoundsException e) {

        }
        try {
            new Grid(1, 1, 2);
            fail();
        } catch (IndexOutOfBoundsException e) {

        }

    }

    private int getCntMines(Square[][] actualGrid) {
        int cntMines = 0;
        for (Square[] rows : actualGrid) {
            for (Square square : rows) {
                if (square.hasMine()) {
                    cntMines++;
                }
            }
        }
        return cntMines;
    }

    @Test
    public void clic() throws Exception {
        Grid grid = new Grid(3, 3, 2);

        try {
            grid.clic(-1, 1);
            fail();
        } catch (IndexOutOfBoundsException e) {

        }
        try {
            grid.clic(1, -1);
            fail();
        } catch (IndexOutOfBoundsException e) {

        }
        try {
            grid.clic(3, 1);
            fail();
        } catch (IndexOutOfBoundsException e) {

        }
        try {
            grid.clic(1, 3);
            fail();
        } catch (IndexOutOfBoundsException e) {

        }



    }

    @Test
    public void testFirstClic() throws Exception {
        Grid grid = new Grid(3, 3, 8);
        assertSame(IGrid.GameStatus.STARTED, grid.getStatus());

        Square[][] actualGrid = (Square[][]) grid.getGrid();
        int previousCntMines = getCntMines(actualGrid);

        // WE SHALL NEVER LOOSE ON FIRST CLIC
        assertSame(IGrid.GameStatus.WIN, grid.clic(1, 1));
        assertNotEquals(previousCntMines, actualGrid);



    }

    @Test
    public void testWinningGame() throws Exception {
        IGrid grid = new Grid("" +
                ". * .\n" +
                "* . .\n" +
                ". . .\n");

        IGrid secondState = new Grid("" +
                ". * .\n" +
                "* r .\n" +
                ". . .\n");

        assertSame(IGrid.GameStatus.ONGOING, grid.clic(1, 1));
        assertTrue(grid.compareTo(secondState));

        assertSame(IGrid.GameStatus.ONGOING, grid.clic(2, 2));
        IGrid thirdState = new Grid("" +
                ". * .\n" +
                "* r r\n" +
                ". r r\n");
        assertTrue(grid.compareTo(thirdState));
        assertSame(IGrid.GameStatus.ONGOING, grid.clic(0, 2));
        assertSame(IGrid.GameStatus.ONGOING, grid.clic(2, 0));
        assertSame(IGrid.GameStatus.WIN, grid.clic(0, 0));

    }


    @Test
    public void testLosingGame() throws Exception {
        IGrid grid = new Grid("" +
                ". * .\n" +
                ". * .\n" +
                ". * .\n");



        IGrid secondState = new Grid("" +
                ". * .\n" +
                ". * .\n" +
                "r * .\n");

        assertSame(IGrid.GameStatus.ONGOING, grid.clic(0, 2));

        assertTrue(grid.compareTo(secondState));
        assertSame(IGrid.GameStatus.FAIL, grid.clic(1, 1));

    }



    @Test
    public void testSquareNumbers() {
        IGrid grid = new Grid("" +
                ". * .\n" +
                "* . .\n" +
                ". . .\n");

        ISquare[][] initialState = grid.getGrid();
        assertEquals(2, initialState[0][0].getAdjacentMines());
        assertEquals(1, initialState[0][1].getAdjacentMines());
        assertEquals(1, initialState[0][2].getAdjacentMines());

        assertEquals(1, initialState[1][0].getAdjacentMines());
        assertEquals(2, initialState[1][1].getAdjacentMines());
        assertEquals(1, initialState[1][2].getAdjacentMines());

        assertEquals(1, initialState[2][0].getAdjacentMines());
        assertEquals(1, initialState[2][1].getAdjacentMines());
        assertEquals(0, initialState[2][2].getAdjacentMines());


    }




}