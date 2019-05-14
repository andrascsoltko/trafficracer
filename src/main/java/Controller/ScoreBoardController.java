package Controller;

import Model.dbFunctions;
import Model.tbl_score;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * ScoreBoard view.
 * */
public class ScoreBoardController {

    @FXML
    private TableView<tbl_score> scoreTable;

    @FXML
    private TableColumn<tbl_score, String > userNameC;

    @FXML
    private TableColumn<tbl_score, Integer> scoreC;

    private String playerName;


    private static ArrayList<tbl_score> scores = new ArrayList<>();

    /**
     * Receives the username of the player from previous scene.
     * @param userName is the current user.
     * */
    void initdata(String userName) {
        this.playerName = userName;
    }

    /**
     * Leads back to the menu scene.
     * @param actionEvent triggers the method.
     * @throws Exception in case something fails.
     * */
    public void backToMenu(ActionEvent actionEvent)throws Exception{

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/menu.fxml"));
        Parent root = fxmlLoader.load();
        fxmlLoader.<MenuController>getController().initdata(playerName);

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("TrafficRacer - Menu");
        stage.show();

    }

    /**
     * Loads the data to the fxml table view.
     * */
    public void initialize(){

        scores = dbFunctions.getScores();

        userNameC.setCellValueFactory(new PropertyValueFactory<>("username"));
        scoreC.setCellValueFactory(new PropertyValueFactory<>("score"));

        ObservableList<tbl_score> observableResult = FXCollections.observableArrayList();

        observableResult.addAll(scores);
        scoreTable.setItems(observableResult);


    }




}
