package main;
import Model.dbFunctions;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.File;

public class MyApplication extends Application {

    Media sound;
    MediaPlayer mediaPlayer;


    @Override
    public void start(Stage primaryStage) throws Exception {
//----------------------------DB CONNECTION INIT------------------------------------------------------------------------
        dbFunctions.DBInit();

//----------------------------MUSIC PLAYER------------------------------------------------------------------------------
        String musicFile = "src\\main\\resources\\songs\\music.mp3";
        sound = new Media(new File(musicFile).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setVolume(0.05);
        mediaPlayer.play();
//----------------------------MAIN SCENE--------------------------------------------------------------------------------
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/launch.fxml"));
        primaryStage.setTitle("TrafficRacer - Login");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();


//----------------------------DB CONNECTION DISPOSE---------------------------------------------------------------------
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                dbFunctions.Dispose();
            }
        });
    }
}
