package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.SceneNavigator;

public class GamePVPController implements Initializable {

    @FXML
    private Button button1;
    @FXML
    private Button button2;
    @FXML
    private Button button3;
    @FXML
    private Button button4;
    @FXML
    private Button button5;
    @FXML
    private Button button6;
    @FXML
    private Button button7;
    @FXML
    private Button button8;
    @FXML
    private Button button9;
    @FXML
    private Label Player1Score;
    @FXML
    private Label Player2Score;
    @FXML
    private Label TieScore;
    @FXML
    private Button playAgainButton;
    

    @FXML
    private ImageView backImg;
    @FXML
    private ImageView saveImg;
    @FXML
    private ImageView loadImg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
    }

    @FXML
    void playAgain(ActionEvent event) {
        
    }

    

    @FXML
    private void goBack(MouseEvent event) throws IOException {
        SceneNavigator sceneNavigator = new SceneNavigator();
        sceneNavigator.navigateImg(event, "/views/PVPNames.fxml");
    }

    @FXML
    private void goToHistoryPVP(ActionEvent event) throws IOException {

        SceneNavigator sceneNavigator = new SceneNavigator();
        sceneNavigator.navigateBtn(event, "/views/HistoryPVP.fxml");

    }

    @FXML
    private void saveGame(MouseEvent event) {

    }

    @FXML
    private void loadGame(MouseEvent event) {
    }

}
