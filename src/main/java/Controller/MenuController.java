package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;



/**
 * Menu scene.
 * */
public class MenuController {

    @FXML
    private Label lblUsername;

    private String playerName;

    /**
     * Receives the username of the player from previous scene.
     * @param playerName is the name of the current user.
     * */
    public void initdata(String playerName) {
        this.playerName = playerName;
        lblUsername.setText(playerName);


    }


    /**
     * Returns to the opening scene of the application.
     * @param actionEvent triggers the method.
     * @throws Exception in case something fails.
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
     * @param actionEvent triggers the method.
     * @throws Exception in case something fails.
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
     * @param actionEvent triggers the method.
     * @throws Exception in case something fails.
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
