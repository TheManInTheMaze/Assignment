package service;

import config.ConfigTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.Assert.assertSame;


/**
 * Created by pcorentin on 09/07/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = ConfigTest.class)
public class GameApiServiceTest {

    @Autowired
    private GameApiService service;


    @Test
    public void testStartGame() throws Exception {

        assertSame(HttpStatus.BAD_REQUEST, service.startGame(-1, 1, 1));
        assertSame(HttpStatus.BAD_REQUEST, service.startGame(1, -1, 1));
        assertSame(HttpStatus.BAD_REQUEST, service.startGame(3, 3, -1));
        assertSame(HttpStatus.BAD_REQUEST, service.startGame(3, 3, 10));

    }

    @Test
    public void testClic() throws Exception {
        assert (false);

    }
}