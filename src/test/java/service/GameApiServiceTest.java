package service;

import config.ConfigTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
    private GameApiService  service;


    @Test
    public void testStartGame() throws Exception {

        assertSame("500", service.startGame(-1, 1, 0).getResponceCode());
        assertSame("500", service.startGame(1, -1, 0).getResponceCode());
        assertSame("500", service.startGame(1, 1, -1).getResponceCode());
        assertSame("500", service.startGame(2, 2, 10).getResponceCode());


    }

    @Test
    public void testClic() throws Exception {
        // UNINITIALIZED GAME TEST
        assertSame("503", service.clic(2, 1).getResponceCode());
        service.startGame(2, 2, 0);
        //OUT OF GRID TEST
        assertSame("500", service.clic(-2, 1).getResponceCode());
        assertSame("500", service.clic(1, -1).getResponceCode());
        assertSame("500", service.clic(-1, -1).getResponceCode());
        assertSame("200", service.clic(1, 1).getResponceCode());



    }
}