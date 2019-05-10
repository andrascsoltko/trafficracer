package Controller;

import Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.persistence.NoResultException;
import java.io.IOException;

public class LaunchController {

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Label lblStatus;
//----------------------------LOGIN FUNCTION--------------------------------------------------------------------------------
    public void startLogin(ActionEvent actionEvent) throws IOException {


        try {
//----------------------------LOGIN SEQUENCE INIT-----------------------------------------------------------------------
            dbFunctions.loginUser(txtUsername.getText(), txtPassword.getText());
            lblStatus.setText("Login Successful");
            lblStatus.setTextFill(Color.GREEN);
//----------------------------LOGIN SUCCESFULL CHANGING SCENE TO MENU---------------------------------------------------
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/menu.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("TrafficRacer - Menu");
            stage.show();


        }
        catch (NoResultException ex) {
//----------------------------LOGIN FAILED------------------------------------------------------------------------------
            lblStatus.setText("Login Failed");
            lblStatus.setTextFill(Color.RED);


        }catch (Exception ex){
            System.out.println(ex);

            System.out.println("buzi");

        }

    }
//----------------------------REGISTER FUNCTION--------------------------------------------------------------------------------
    public void startRegister(){

        try {
//----------------------------REGISTER INIT--------------------------------------------------------------------------------
            dbFunctions.registerUser(txtUsername.getText(), txtPassword.getText());
            lblStatus.setText("Registration Successful");
            lblStatus.setTextFill(Color.GREEN);

        } catch (Exception ex){
//----------------------------REGISTER FAILED--------------------------------------------------------------------------------
            lblStatus.setText("Username Already Taken");
            lblStatus.setTextFill(Color.RED);


        }
    }


}
