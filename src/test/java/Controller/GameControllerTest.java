package Controller;

import javafx.scene.shape.Rectangle;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameControllerTest {

    private Rectangle rect1;


    private Rectangle rect2;

    private GameController gameController;

    @BeforeEach
    void setUp(){
        gameController = new GameController();
        rect1 = new Rectangle(30,30,60,60);
        rect2 = new Rectangle(20,20,50,50);
    }

    @AfterEach
    void tearDown(){
        gameController = null;
        rect1 = null;
        rect2 = null;
    }

    @Test
    void collosionDetection() {
        //assertEquals(true, gameController.collosionDetection(rect1,rect2));
        assertTrue(gameController.collosionDetection(rect1,rect2));
    }
}