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

        assertNull(grid.clic(-1, 1));
        assertNull(grid.clic(1, -1));
        assertNull(grid.clic(3, 2));
        assertNull(grid.clic(2, 3));


    }

    @Test
    public void testFirstClic() throws Exception {
        Grid grid = new Grid(3, 3, 2);
        assertSame(IGrid.GameStatus.STARTED, grid.getStatus());

        Square[][] actualGrid = (Square[][]) grid.getGrid();
        int previousCntMines = getCntMines(actualGrid);

        // WE SHALL NEVER LOOSE ON FIRST CLIC
        assertSame(IGrid.GameStatus.ONGOING, grid.clic(1, 1));
        assertNotEquals(previousCntMines, actualGrid);



    }

    @Test
    public void testWinningGame() throws Exception {
        Square[][] initialState = createGridFromText("" +
                ". * .\n" +
                "* . .\n" +
                ". . .\n");
        Grid grid = new Grid(3, 3, 0);
        grid.setGrid(initialState);


        Square[][] secondState = createGridFromText("" +
                ". * r\n" +
                "* r r\n" +
                "r r r\n");

        assertSame(grid.clic(1, 1), IGrid.GameStatus.ONGOING);
        assertTrue(compareSquares(grid.getGrid(), secondState));

        assertSame(grid.clic(0, 0), IGrid.GameStatus.WIN);

    }


    @Test
    public void testLosingGame() throws Exception {
        Square[][] initialState = createGridFromText("" +
                ". * .\n" +
                ". * .\n" +
                ". * .\n");
        Grid grid = new Grid(3, 3, 0);
        grid.setGrid(initialState);


        Square[][] secondState = createGridFromText("" +
                ". * r\n" +
                ". * r\n" +
                ". * r\n");

        assertSame(grid.clic(0, 2), IGrid.GameStatus.ONGOING);
        assertTrue(compareSquares(grid.getGrid(), secondState));

        assertSame(grid.clic(1, 1), IGrid.GameStatus.FAIL);

    }

    private boolean compareSquares(ISquare[][] grid, Square[][] secondState) {
        if (grid.length != secondState.length) return false;
        if (grid.length != 0 && secondState.length != 0 && grid[0].length != secondState[0].length)
            return false;
        for (int j = 0; j < grid.length; j++) {
            for (int i = 0; i < grid[0].length; i++) {
                System.out.print(grid[j][i].getStatus());
                if (grid[j][i].getStatus() != secondState[j][i].getStatus())
                    return false;
            }
        }
        return true;
    }

    @Test
    public void testSquareNumbers() {
        Square[][] initialState = createGridFromText("" +
                ". * .\n" +
                "* . .\n" +
                ". . .\n");
        Grid grid = new Grid(3, 3, 0);
        grid.setGrid(initialState);

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

    private Square[][] createGridFromText(String input) {
        Square[][] ret = null;
        String[] rows = input.split("\n");
        int icolumn = 0;
        int irow = 0;
        for (String row : rows) {
            String[] squares = row.split(" ");
            if (ret == null) ret = new Square[rows.length][squares.length];
            for (String square : squares) {
                ret[irow][icolumn] = new Square(irow, icolumn);
                if (square.equals("*")) {
                    ret[irow][icolumn].setMine(true);
                } else if (square.equals("r")) {
                    ret[irow][icolumn].setStatus(ISquare.SquareStatus.REVEALED);
                }
                icolumn++;
            }
            icolumn = 0;
            irow++;
        }
        return ret;


    }


}