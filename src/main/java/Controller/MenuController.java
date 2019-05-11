package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;



public class MenuController {

    @FXML
    private Label lblUsername;

    private String playerName;


    public void initdata(String playerName) {
        this.playerName = playerName;
        lblUsername.setText(playerName);


    }


    //----------------------------LOGOUT SEQUENCE--------------------------------------------------------------------------------
    public void logOut(ActionEvent actionEvent) throws Exception {

        //----------------------------BACK TO LOGIN SCENE--------------------------------------------------------------------------------
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/launch.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("TrafficRacer - Login");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();


    }
    //----------------------------GAME START--------------------------------------------------------------------------------
    public void playButton(ActionEvent actionEvent)throws Exception, IOException {

        //----------------------------GAME SCENE INIT--------------------------------------------------------------------------------

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/game.fxml"));
        Parent root = fxmlLoader.load();
        fxmlLoader.<GameController>getController().initdata(lblUsername.getText());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("TrafficRacer - Game");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();

    }

    public void getHighScores(ActionEvent actionEvent)throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/scores.fxml"));
        Parent root = fxmlLoader.load();

        fxmlLoader.<ScoreBoardController>getController().initdata(lblUsername.getText());

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("TrafficRacer - ScoreBoard");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();


    }


}
