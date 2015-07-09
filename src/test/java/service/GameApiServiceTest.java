package service;

import config.ConfigTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

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
        assert (true);

    }

    @Test
    public void testClic() throws Exception {
        assert (true);

    }
}