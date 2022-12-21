package controllers;

import Utility.Constants;
import database.LocalDataBase;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import models.GameDataPVP;
import models.SceneNavigator;
import java.util.Calendar;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javafx.scene.Parent;
import javafx.scene.Scene;

import models.Room;

public class GameOnlineController implements Initializable {

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
    private Label Player1Score;
    private Label Player2Score;
    private Label TieScore;

    private int playerTurn = 0;
    private int p1score = 0;
    private int p2score = 0;
    private int tiescore = 0;
    private String line = "";
    private int squareCount = 0;
    private int flagWin = 0;
    private ArrayList<Button> buttons;
    private byte[] b;
    private String bString = "";   //added this at first cause printing reading the file didnt show first letter..
    @FXML
    private ImageView backImg;
    private Label Player1Name;
    @FXML
    private Label Player2Name;
    LocalDataBase ldb;

    private Client client;

    private Parent root;
    private Scene scene;
    private Stage stage;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("bstring at initialization ============================= " + bString);

        client = Client.getInstance(Util.ip, Util.port);

        buttons = new ArrayList<>(Arrays.asList(button1, button2, button3, button4, button5, button6, button7, button8, button9));

        buttons.forEach(button -> {
            setupButton(button);
            button.setFocusTraversable(false);
        });

        switch (Room.getTurn()) {
            case "1":
                buttons.forEach(button -> {
                    button.setDisable(false);

                });
                playerTurn = 0;
                Room.setTurn("0");
                break;

            case "0":
                buttons.forEach(button -> {
                    button.setDisable(true);
                });
                playerTurn = 1;
                Room.setTurn("1");
                break;
        }

        System.out.println("room board data %%%%%% " + Room.getBoardDataAsString());
        if (Room.getBoardDataAsString() != null) {
            bString = Room.getBoardDataAsString();
        }

        if (!"".equals(bString)) {
            try {
                String[] arrStringShapes = bString.split("-");
                squareCount = arrStringShapes.length;

                for (int i = 0; i < arrStringShapes.length; i++) {

                    if ("X".equals(Character.toString(arrStringShapes[i].charAt(1))) | "O".equals(Character.toString(arrStringShapes[i].charAt(1)))) {
                        buttons.get(Integer.parseInt(Character.toString(arrStringShapes[i].charAt(0))) - 1).setText(Character.toString(arrStringShapes[i].charAt(1)));
                        buttons.get(Integer.parseInt(Character.toString(arrStringShapes[i].charAt(0))) - 1).setDisable(true);
                    }

                }

                String check = checkIfGameIsOver();
                if (winLoseChecker(check) == true) {

                }

                if (arrStringShapes.length % 2 == 0) {
                    playerTurn = 0;
                } else {
                    playerTurn = 1;
                }
            } catch (IOException ex) {
                Logger.getLogger(GameOnlineController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public void clearButton(Button button) {
        button.setText("");
    }

    public void resetButton(Button button) {
        button.setDisable(false);
        button.setText("");
    }

    private void setupButton(Button button) {
        button.setOnMouseClicked(mouseEvent -> {

            setPlayerSymbol(button);


            client.clientRefreshGameBoardRequest(Constants.refreshGameBoardAfterEveryTurn + "/" + Room.getPlayerOneUserName() + "/" + Room.getPlayerTwoUserName() + "/" + bString);

        });
    }

    public void setPlayerSymbol(Button button) {
        if (playerTurn % 2 == 0) {
            button.setText("X");
            playerTurn = 1;
        } else {
            button.setText("O");
            playerTurn = 0;
        }

        bString += button.getId() + button.getText() + "-";
    }

    public String checkIfGameIsOver() throws IOException {
        System.out.println("in check if game is over 00000000000");

        //squareCount++;
        String d = "";
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
            flagWin = 1;
            tiescore++;
            SceneNavigator.navigate("/views/TiedOnline.fxml");
        }
        d = line;
        return d;
    }

    boolean winLoseChecker(String line) throws IOException {
        switch (line) {
            case "XXX":
                flagWin = 1;
                p1score++;
                if (Room.getPlayerOneWonFlag() == 0) {
                    client.playerOneWon();
                    Room.setPlayerOneWonFlag(1);
                } else {
                    Room.setPlayerOneWonFlag(0);
                }

                 {

                }
                buttons.forEach(button -> {
                    button.setDisable(true);
                });
                return true;
            case "OOO":
                flagWin = 1;

                if (Room.getPlayerTwoWonFlag() == 0) {
                    client.playerTwoWon();
                    Room.setPlayerTwoWonFlag(1);
                } else {
                    Room.setPlayerTwoWonFlag(0);
                }

                buttons.forEach(button -> {
                    button.setDisable(true);
                });
                return true;
            default:

                return false;
        }
    }

    private void goToHistoryPVP(ActionEvent event) {
        SceneNavigator.navigate("/views/HistoryPVP.fxml");

    }

    public void DisplayNames(String p1Name, String p2Name) {
        Player1Name.setText(p1Name);
        Player2Name.setText(p2Name);
    }

    public void getData(String winner) throws IOException {
        String date = new SimpleDateFormat("yyyy/MM/dd/HH/mm/ss").format(Calendar.getInstance().getTime());
        ldb = new LocalDataBase();

        GameDataPVP gm = new GameDataPVP(
                date, Player1Name.getText(), Player1Score.getText(),
                Player2Name.getText(), Player2Score.getText(), winner);
        ldb.writeData(gm);
    }

    private void saveGame(MouseEvent event) {

        // should create an alert here.
        Stage stage = new Stage();
        FileChooser fc = new FileChooser();

        File file = fc.showSaveDialog(stage);
        FileOutputStream fos;
        try {
            if (file != null) {
                fos = new FileOutputStream(file);
                b = bString.getBytes();
                fos.write(b);
                // 0X-1O-3X
                fos.close();
            }
        } catch (FileNotFoundException ex) {

        } catch (IOException ex) {
        }

    }

    private void loadGame(MouseEvent event) {
        Stage stage = new Stage();
        FileChooser fc = new FileChooser();
        File file = fc.showOpenDialog(stage);
        FileInputStream fis;

        try {
            if (file != null) {
                fis = new FileInputStream(file);
                while (fis.read() != -1) {
                    int size = fis.available();
                    b = new byte[size];
                    fis.read(b);
                    bString = new String(b);
                    String[] arrString = bString.split("-");

                    for (int i = 0; i < arrString.length; i++) {
                        if ("X".equals(Character.toString(arrString[i].charAt(1))) | "O".equals(Character.toString(arrString[i].charAt(1)))) {
                            buttons.get(Integer.parseInt(Character.toString(arrString[i].charAt(0))) - 1).setText(Character.toString(arrString[i].charAt(1)));
                            buttons.get(Integer.parseInt(Character.toString(arrString[i].charAt(0))) - 1).setDisable(true);
                        }

                        if (arrString.length % 2 == 0) {
                            playerTurn = 0;
                        } else {
                            playerTurn = 1;
                        }

                    }

                    fis.close();
                }
            }
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }
    }

    public void Data(String p1Name, String p2Name, int p1Score, int p2Score, int tScore) {
        Player1Name.setText(p1Name);
        Player2Name.setText(p2Name);
        Player1Score.setText(Integer.toString(p1Score));
        Player2Score.setText(Integer.toString(p2Score));
        TieScore.setText(Integer.toString(tScore));
    }

    @FXML
    private void goBack(MouseEvent event) {

        Room.setBoardDataAsString("");
        SceneNavigator.navigate("/views/OnlinePlayers.fxml");
        client.resetIsPlaying();
    }

}
