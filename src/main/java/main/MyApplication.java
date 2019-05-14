package main;
import Model.dbFunctions;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;
import javafx.stage.WindowEvent;



public class MyApplication extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception {

        dbFunctions.DBInit();


        Parent root = FXMLLoader.load(getClass().getResource("/fxml/launch.fxml"));
        primaryStage.setTitle("TrafficRacer - Login");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();



        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                dbFunctions.Dispose();
            }
        });
    }
}
