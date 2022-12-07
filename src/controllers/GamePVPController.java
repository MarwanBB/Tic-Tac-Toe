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
    private int playerTurn = 0;
    private int p1score = 0;
    private int p2score = 0;
    private int tiescore = 0;
    private String line = "";
    private int squareCount = 0;
    private int flagWin = 0;
    private ArrayList<Button> buttons;

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
        buttons = new ArrayList<>(Arrays.asList(button1, button2, button3, button4, button5, button6, button7, button8, button9));
        buttons.forEach(button -> {
            setupButton(button);

        });
    }

    @FXML
    void playAgain(ActionEvent event) {
        playerTurn = 0;
        squareCount = 0;
        flagWin = 0;
        buttons.forEach(this::resetButton);
    }

    public void resetButton(Button button) {
        button.setDisable(false);
        button.setText("");
    }

    private void setupButton(Button button) {
        button.setOnMouseClicked(mouseEvent -> {
            setPlayerSymbol(button);
            button.setDisable(true);
            checkIfGameIsOver();
        });
    }

    public void setPlayerSymbol(Button button) {
        if (playerTurn == 0) {    // i changed this ----------------
            button.setText("X");
            //add element 
            playerTurn = 1;
        } else {
            button.setText("O");
            playerTurn = 0;
        }


    }

    public void checkIfGameIsOver() {

        squareCount++;

        line = button1.getText() + button2.getText() + button3.getText();
        winLoseChecker(line);
        line = button4.getText() + button5.getText() + button6.getText();
        winLoseChecker(line);
        line = button7.getText() + button8.getText() + button9.getText();
        winLoseChecker(line);
        line = button1.getText() + button5.getText() + button9.getText();
        winLoseChecker(line);
        line = button3.getText() + button5.getText() + button7.getText();
        winLoseChecker(line);
        line = button1.getText() + button4.getText() + button7.getText();
        winLoseChecker(line);
        line = button2.getText() + button5.getText() + button8.getText();
        winLoseChecker(line);
        line = button3.getText() + button6.getText() + button9.getText();
        winLoseChecker(line);

        if (squareCount == 9 && flagWin == 0) {
            tiescore++;
            TieScore.setText(Integer.toString(tiescore));
        }

    }

    boolean winLoseChecker(String line) {
        switch (line) {
            case "XXX":
                flagWin = 1;
                p1score++;
                Player1Score.setText(Integer.toString(p1score));

                buttons.forEach(button -> {
                    button.setDisable(true);
                });
                return true;
            case "OOO":
                flagWin = 1;
                p2score++;
                Player2Score.setText(Integer.toString(p2score));

                buttons.forEach(button -> {
                    button.setDisable(true);
                });
                return true;
            default:
                return false;
        }
    }


    @FXML
    private void saveGame(MouseEvent event) {
  

    }

    @FXML
    private void loadGame(MouseEvent event) {
       
    }

}