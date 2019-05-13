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

    /**
     * Receives the username of the player from previous scene.
     * */
    public void initdata(String playerName) {
        this.playerName = playerName;
        lblUsername.setText(playerName);


    }


    /**
     * Returns to the opening scene of the application.
     * */
    public void logOut(ActionEvent actionEvent) throws Exception {

        //----------------------------BACK TO LOGIN SCENE--------------------------------------------------------------------------------
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/launch.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("TrafficRacer - Login");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();


    }
    /**
     * Start the game sequence.
     * */
    public void playButton(ActionEvent actionEvent)throws Exception {


        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/game.fxml"));
        Parent root = fxmlLoader.load();
        fxmlLoader.<GameController>getController().initdata(lblUsername.getText());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("TrafficRacer - Game");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();

    }

    /**
     * Opens the high scores view.
     * */
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
