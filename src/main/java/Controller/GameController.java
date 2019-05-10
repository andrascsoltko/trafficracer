package Controller;

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


import java.io.IOException;
import java.net.URL;

import java.util.ArrayList;

import java.util.ResourceBundle;

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



    static ArrayList<String> input;

    static Image carImg;
    static Image obstacleImg;

    static ImagePattern carPattern;
    static ImagePattern obstaclePattern;



    static double velocity = 10;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        carRectangle.setFocusTraversable(true);

        carImg = new Image(getClass().getResource("/Images/car.png").toExternalForm());
        obstacleImg = new Image(getClass().getResource("/Images/obstacle.png").toExternalForm());

        carPattern = new ImagePattern(carImg);
        obstaclePattern = new ImagePattern(obstacleImg);

        obstacle.setFill(obstaclePattern);
        carRectangle.setFill(carPattern);



        input = new ArrayList<String>();


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



                    scoreLBL.setText("Score:" + ((int) t) * 10);


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
                        this.stop();
                        btnBack.setVisible(true);
                    }


                    if(collosionDetection(carRectangle, obstacle)){
                        gameOverLable.setText("GAME OVER");
                        this.stop();
                        btnBack.setVisible(true);

                    }



                }
            }.start();




    }

    public boolean collosionDetection(Rectangle rect1, Rectangle rect2){

        if(
                rect1.getX()+ rect1.getWidth() >= rect2.getX() &&
                        rect1.getX() <= (rect2.getX()+rect2.getWidth()) &&
                        rect1.getY() + rect1.getHeight() >= rect2.getY() &&
                        rect1.getY() <= rect2.getY() +rect2.getHeight()
        ){
            System.out.println("balek");
            return true;
        }
        else {
            //System.out.println("faszacsavo");
            return false;
        }



    }

    public void backToMenu(ActionEvent actionEvent)throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/menu.fxml"));
        Parent root = fxmlLoader.load();

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("TrafficRacer - Menu");
        stage.show();
    }


}
