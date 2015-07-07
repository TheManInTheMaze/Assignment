package model;

import junit.framework.TestCase;
import model.interfaces.ISquare;
import org.junit.Test;

/**
 * Created by pcorentin on 07/07/2015.
 */
public class SquareTest extends TestCase {

    private Square square = new Square(0, 0);

    @Test
    public void testClic() throws Exception {

        assertSame(square.clic(), ISquare.SquareStatus.REVEALED);
        //status should stay the same
        assertSame(square.clic(), ISquare.SquareStatus.REVEALED);

        square.setStatus(ISquare.SquareStatus.COVERED);
        square.setHasMine(true);
        assertSame(square.clic(), ISquare.SquareStatus.EXPLODED);
        //status should stay the same
        assertSame(square.clic(), ISquare.SquareStatus.EXPLODED);


    }

}