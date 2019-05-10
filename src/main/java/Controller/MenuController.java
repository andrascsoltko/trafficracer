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

    private String userName;


    public void initdata(String userName) {
        this.userName = userName;
        lblUsername.setText("Welcome " + this.userName + "!");
    }


    //----------------------------LOGOUT SEQUENCE--------------------------------------------------------------------------------
    public void logOut(ActionEvent actionEvent) throws IOException {

        try {
            //----------------------------BACK TO LOGIN SCENE--------------------------------------------------------------------------------
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/launch.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setTitle("TrafficRacer - Login");
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.show();
        }catch (Exception ex){

        }


    }
    //----------------------------GAME START--------------------------------------------------------------------------------
    public void playButton(ActionEvent actionEvent)throws Exception{

        try {
//----------------------------GAME SCENE INIT--------------------------------------------------------------------------------
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/game.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setTitle("TrafficRacer - Game");
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.show();

        }catch (Exception ex){
            System.out.println(ex);
            System.out.println("szaaar");
            throw ex;

        }
    }


}
