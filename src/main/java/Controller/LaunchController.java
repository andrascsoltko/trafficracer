package Controller;

import Model.dbFunctions;
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
import org.tinylog.Logger;

import javax.persistence.NoResultException;

/**
 * The login scene.
 * */
public class LaunchController {

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Label lblStatus;

    /**
     * Initialises the login sequence.
     * @param actionEvent triggers the method.
     * */
    public void startLogin(ActionEvent actionEvent) {


        try {


            if(txtUsername.getText().isEmpty() || txtPassword.getText().isEmpty()){
                lblStatus.setText("Username/password is empty!");
                lblStatus.setTextFill(Color.RED);
                Logger.info("Login Failed");
            }
            else{
                dbFunctions.loginUser(txtUsername.getText(), txtPassword.getText());

                lblStatus.setText("Login Successful");
                lblStatus.setTextFill(Color.GREEN);
                Logger.info("Login successful.");

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/menu.fxml"));
                Parent root = fxmlLoader.load();
                fxmlLoader.<MenuController>getController().initdata(txtUsername.getText());

                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setTitle("TrafficRacer - Menu");
                stage.show();

            }

        }
        catch (NoResultException ex) {
            lblStatus.setText("Login Failed");
            lblStatus.setTextFill(Color.RED);
            Logger.info("Login failed:");
            Logger.info(ex);


        }catch (Exception ex){

           Logger.info(ex);

        }
    }

    /**
     * Initialises the registration.
     * */
    public void startRegister(){

        Logger.info("startRegister pressed");
        try {
            if(txtUsername.getText().isEmpty() || txtPassword.getText().isEmpty()){
                lblStatus.setText("Username/password is empty!");
                lblStatus.setTextFill(Color.RED);
                Logger.info("Registration failed");
            }else {
                dbFunctions.registerUser(txtUsername.getText(), txtPassword.getText());
                lblStatus.setText("Registration Successful");
                lblStatus.setTextFill(Color.GREEN);
                Logger.info("Registration Successful");
            }
        } catch (Exception ex){
            lblStatus.setText("Username Already Taken");
            lblStatus.setTextFill(Color.RED);
            Logger.info("Registration failed");
            Logger.info(ex);
        }
    }
}
