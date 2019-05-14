package Controller;

import Model.dbFunctions;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.tinylog.Logger;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


/**
 * Hearth of the Game.
 * */
public class GameController implements Initializable {


    @FXML
    private Canvas playGroundCanvas;


    @FXML
    private Label gameOverLable;

    @FXML
    private Rectangle carRectangle;

    @FXML
    private Rectangle obstacle;

    @FXML
    private Label scoreLBL;

    @FXML
    private Button btnBack;



    private static ArrayList<String> input;

    private static Image carImg;
    private static Image obstacleImg;

    private static ImagePattern carPattern;
    private static ImagePattern obstaclePattern;

    private static int score = 0;



    private static String playerName;

    private static double velocity = 10;

    /**
     * Receives the username of the player from previous scene.
     * */
    void initdata(String playerName) {
        GameController.playerName = playerName;
        Logger.info("Recieved: "+ playerName);
    }

    /**
     * Hearth of the game.
     * Frame based background animation to get the illusion of an endless road, uses {@link AnimatedImages}.
     * Handles player movement with keyListeners.
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        carRectangle.setFocusTraversable(true);

        carImg = new Image(getClass().getResource("/Images/car.png").toExternalForm());
        obstacleImg = new Image(getClass().getResource("/Images/obstacle.png").toExternalForm());

        carPattern = new ImagePattern(carImg);
        obstaclePattern = new ImagePattern(obstacleImg);

        obstacle.setFill(obstaclePattern);
        carRectangle.setFill(carPattern);



        input = new ArrayList<>();


        carRectangle.setOnKeyPressed(
                event -> {
                    String code = event.getCode().toString();
                    if(!input.contains(code))

                        input.add(code);
                }
        );

        carRectangle.setOnKeyReleased(
                event -> {
                    String code = event.getCode().toString();
                    input.remove(code);
                }
        );



        GraphicsContext gc = playGroundCanvas.getGraphicsContext2D();


        AnimatedImages road = new AnimatedImages();

        Image[] imageArray = new Image[4];
        for (int i = 0; i < 4; i++)
            imageArray[i] = new Image(getClass().getResource("/Images/road_"+i+".png").toExternalForm());

        road.frames = imageArray;
        road.duration = 0.100;

        final long startNanoTime = System.nanoTime();
        obstacle.setX(Math.random() * 400 + 200);
        carRectangle.setY(450);
        carRectangle.setX(370);


            new AnimationTimer() {
                public void handle(long currentNanoTime) {


                    double t = (currentNanoTime - startNanoTime) / 1000000000.0;

                    score = (int) (t *10);



                    scoreLBL.setText("Score:" + score);



                    gc.drawImage(road.getFrame(t), 0, 0);

                    obstacle.setY(obstacle.getY()+velocity);

                    if(obstacle.getY() > 600) {

                        obstacle.setX(Math.random() * 600 + 100);
                        obstacle.setY(0);
                    }


                    if (input.contains("A")) {

                        if(carRectangle.getX() > 100)
                        carRectangle.setX(carRectangle.getX()-10);

                    } else if (input.contains("D")) {

                        if(carRectangle.getX()+carRectangle.getWidth() < 700)
                        carRectangle.setX(carRectangle.getX()+10);

                    }else if (input.contains("W")) {

                        if(carRectangle.getY() > 0)
                            carRectangle.setY(carRectangle.getY()-10);

                    }else if (input.contains("S")) {

                        if(carRectangle.getY()+carRectangle.getHeight() < 600)
                            carRectangle.setY(carRectangle.getY()+10);

                    }


                    if(input.contains("ESCAPE")){
                        gameOverLable.setText("GAME OVER");
                        Logger.info("Player gave up.");
                        this.stop();
                        btnBack.setVisible(true);
                    }


                    if(collosionDetection(carRectangle, obstacle)){
                        this.stop();
                        Logger.info("Player crashed.");
                        gameOverLable.setText("GAME OVER");
                        dbFunctions.saveScore(playerName,score);
                        btnBack.setVisible(true);

                    }



                }
            }.start();




    }

   /**
    * Checks whether the two rectangles are touching, returning a boolean value.
    * @return true if they collide, false if they don't.
    * @param rect1 is the car rectangle.
    * @param rect2 is the obstacle rectangle.
    * */
    public boolean collosionDetection(Rectangle rect1, Rectangle rect2){



        return rect1.getX() + rect1.getWidth() >= rect2.getX() &&
                rect1.getX() <= (rect2.getX() + rect2.getWidth()) &&
                rect1.getY() + rect1.getHeight() >= rect2.getY() &&
                rect1.getY() <= rect2.getY() + rect2.getHeight();



    }

    /**
     * Back to menu button, swaps the scene to the menu scene.
     * @param actionEvent triggering the method.
     * @throws IOException if and Input or Output method failed or interpreted.
     * */
    public void backToMenu(ActionEvent actionEvent)throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/menu.fxml"));
        Parent root = fxmlLoader.load();
        fxmlLoader.<MenuController>getController().initdata(playerName);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("TrafficRacer - Menu");
        stage.show();
    }


}
