
package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Vector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.SceneNavigator;

/**
 * FXML Controller class
 *
 * @author boory
 */
public class GamePlayerVsPCController implements Initializable {

    @FXML
    private Button Btn11;
    @FXML
    private Button btn12;
    @FXML
    private Button btn13;
    @FXML
    private Button Btn21;
    @FXML
    private Button Btn22;
    @FXML
    private Button Btn23;
    @FXML
    private Button Btn31;
    @FXML
    private Button Btn32;
    @FXML
    private Button Btn33;
    @FXML
    private Button EasyBtn;
    @FXML
    private Button MediumBtn;
    @FXML
    private Button HardBtn;
    @FXML
    private Label PlayerScore;
    @FXML
    private Label PCScore;
    @FXML
    private Label TiedScore;
    @FXML
    private ImageView backImg;
    Button[] btnsArr;
    String mode = "";
    private Random random = new Random();
    private boolean printO = true;
    private byte[] b;
    private String bString = "-";   //added this at first cause printing reading the file didnt show first letter..
    
    Stage stage;
     private int xScore = 0;
    private int oScore = 0;
    private int draws = 0;
    private int xResult = 0;
    private int oResult = 0;
    ArrayList<Integer> playerXMoves;
    ArrayList<Integer> playerOMoves;
    ArrayList<Integer> moves;
    AIMediumLevel AML;
   
    String movesAsAString;
    String getSubStringForNames = "";
    String nameOfPlayerOneRecorder = "";
    String nameOfPlayerTwoRecorder = "";
    private AiHardLevel AHL;
    @FXML
    private Button playAgainButton;
    @FXML
    private ImageView loadImg;
    @FXML
    private ImageView saveImg;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        AHL = new AiHardLevel();
        AHL.NewGame();
        playerXMoves = new ArrayList<Integer>();
        playerOMoves = new ArrayList<Integer>();
        moves = new ArrayList<Integer>();
        AML = new AIMediumLevel();
        btnsArr = new Button[9];
        addButtonsToArray();

    }    

    @FXML
    private void btn1Clicked(ActionEvent event) {
        if (mode.equalsIgnoreCase("easy")) {
                printXOForEasy(0);
            } else if (mode.equalsIgnoreCase("medium")) {
                printXOForMedium(0);
            } else if (mode.equalsIgnoreCase("hard")) {
                int GO = AHL.isGameOver();
                if (AHL.Move(1, 1) && GO == 0) {
                    System.out.println("YES");
                    Btn11.setDisable(true);
                    Btn11.setTextFill(javafx.scene.paint.Color.valueOf("#CA2727"));
                    Btn11.setText("X");
                    
                    bString += Btn11.getId() + Btn11.getText() + "-";
                    
                    ComputerMove(-1, 0);
                    if ((GO = AHL.isGameOver()) != 0) {
                        SetResulForHard(GO);
                    }
                }

            }
    }

    @FXML
    private void btn2Clicked(ActionEvent event) {
        if (mode.equalsIgnoreCase("easy")) {
                printXOForEasy(1);
            } else if (mode.equalsIgnoreCase("medium")) {
                printXOForMedium(1);
            } else if (mode.equalsIgnoreCase("hard")) {
                int GO = AHL.isGameOver();
                if (AHL.Move(2, 1) && GO == 0) {
                    System.out.println("YES");
                    btn12.setDisable(true);

                    btn12.setTextFill(javafx.scene.paint.Color.valueOf("#CA2727"));
                    btn12.setText("X");
                    
                    bString += btn12.getId() + btn12.getText() + "-";
                    
                    ComputerMove(-1, 0);
                    if ((GO = AHL.isGameOver()) != 0) {
                        SetResulForHard(GO);
                    }
                }

            }
    }

    @FXML
    private void btn3Clicked(ActionEvent event) {
        if (mode.equalsIgnoreCase("easy")) {
                printXOForEasy(2);
            } else if (mode.equalsIgnoreCase("medium")) {
                printXOForMedium(2);
            } else if (mode.equalsIgnoreCase("hard")) {
                int GO = AHL.isGameOver();
                if (AHL.Move(3, 1) && GO == 0) {
                    System.out.println("YES");
                    btn13.setDisable(true);

                    btn13.setTextFill(javafx.scene.paint.Color.valueOf("#CA2727"));
                    btn13.setText("X");
                    
                    bString += btn13.getId() + btn13.getText() + "-";
                    
                    ComputerMove(-1, 0);
                    if ((GO = AHL.isGameOver()) != 0) {
                        SetResulForHard(GO);
                    }
                }

            }
    }

    @FXML
    private void btn4Clicked(ActionEvent event) {
        if (mode.equalsIgnoreCase("easy")) {
                printXOForEasy(3);
            } else if (mode.equalsIgnoreCase("medium")) {
                printXOForMedium(3);
            } else if (mode.equalsIgnoreCase("hard")) {
                int GO = AHL.isGameOver();
                if (AHL.Move(4, 1) && GO == 0) {
                    System.out.println("YES");
                    Btn21.setDisable(true);
                    Btn21.setTextFill(javafx.scene.paint.Color.valueOf("#CA2727"));
                    Btn21.setText("X");
                    
                    bString += Btn21.getId() + Btn21.getText() + "-";
                    
                    ComputerMove(-1, 0);
                    if ((GO = AHL.isGameOver()) != 0) {
                        SetResulForHard(GO);
                    }
                }

            }
    }

    @FXML
    private void btn5Clicked(ActionEvent event) {
        if (mode.equalsIgnoreCase("easy")) {
                printXOForEasy(4);
            } else if (mode.equalsIgnoreCase("medium")) {
                printXOForMedium(4);
            } else if (mode.equalsIgnoreCase("hard")) {
                int GO = AHL.isGameOver();
                if (AHL.Move(5, 1) && GO == 0) {
                    System.out.println("YES");
                    Btn22.setDisable(true);

                    Btn22.setTextFill(javafx.scene.paint.Color.valueOf("#CA2727"));
                    Btn22.setText("X");
                    
                    bString += Btn22.getId() + Btn22.getText() + "-";
                    
                    ComputerMove(-1, 0);
                    if ((GO = AHL.isGameOver()) != 0) {
                        SetResulForHard(GO);
                    }
                }

            }
    }

    @FXML
    private void btn6Clicked(ActionEvent event) {
        if (mode.equalsIgnoreCase("easy")) {
                printXOForEasy(5);
            } else if (mode.equalsIgnoreCase("medium")) {
                printXOForMedium(5);
            } else if (mode.equalsIgnoreCase("hard")) {
                int GO = AHL.isGameOver();
                if (AHL.Move(6, 1) && GO == 0) {
                    System.out.println("YES");
                    Btn23.setDisable(true);

                    Btn23.setTextFill(javafx.scene.paint.Color.valueOf("#CA2727"));
                    Btn23.setText("X");
                    
                    bString += Btn23.getId() + Btn23.getText() + "-";
                    
                    ComputerMove(-1, 0);
                    if ((GO = AHL.isGameOver()) != 0) {
                        SetResulForHard(GO);
                    }
                }

            }
    }

    @FXML
    private void btn7Clicked(ActionEvent event) {
         if (mode.equalsIgnoreCase("easy")) {
                printXOForEasy(6);
            } else if (mode.equalsIgnoreCase("medium")) {
                printXOForMedium(6);
            } else if (mode.equalsIgnoreCase("hard")) {
                int GO = AHL.isGameOver();
                if (AHL.Move(7, 1) && GO == 0) {
                    System.out.println("YES");
                    Btn31.setDisable(true);

                    Btn31.setTextFill(javafx.scene.paint.Color.valueOf("#CA2727"));
                    Btn31.setText("X");
                    
                    bString += Btn31.getId() + Btn31.getText() + "-";
                    
                    ComputerMove(-1, 0);
                    if ((GO = AHL.isGameOver()) != 0) {
                        SetResulForHard(GO);
                    }
                }

            }
    }

    @FXML
    private void btn8Clicked(ActionEvent event) {
         if (mode.equalsIgnoreCase("easy")) {
                printXOForEasy(7);
            } else if (mode.equalsIgnoreCase("medium")) {
                printXOForMedium(7);
            } else if (mode.equalsIgnoreCase("hard")) {
                int GO = AHL.isGameOver();
                if (AHL.Move(8, 1) && GO == 0) {
                    System.out.println("YES");
                    Btn32.setDisable(true);

                    Btn32.setTextFill(javafx.scene.paint.Color.valueOf("#CA2727"));
                    Btn32.setText("X");
                    
                    bString += Btn32.getId() + Btn32.getText() + "-";
                    
                    ComputerMove(-1, 0);
                    if ((GO = AHL.isGameOver()) != 0) {
                        SetResulForHard(GO);
                    }
                }

            }
    }

    @FXML
    private void btn9Clicked(ActionEvent event) {
        if (mode.equalsIgnoreCase("easy")) {
                printXOForEasy(8);
            } else if (mode.equalsIgnoreCase("medium")) {
                printXOForMedium(8);
            } else if (mode.equalsIgnoreCase("hard")) {
                int GO = AHL.isGameOver();
                if (AHL.Move(9, 1) && GO == 0) {
                    System.out.println("YES");
                    Btn33.setDisable(true);

                    Btn33.setTextFill(javafx.scene.paint.Color.valueOf("#CA2727"));
                    Btn33.setText("X");
                    
                    bString += Btn33.getId() + Btn33.getText() + "-";
                    
                    ComputerMove(-1, 0);
                    if ((GO = AHL.isGameOver()) != 0) {
                        SetResulForHard(GO);
                    }
                }

            }
    }

    @FXML
    private void onEasyClicked(ActionEvent event) {
        this.mode = "easy";
            disableDifficultyButtons(true);
            for (int i = 0 ; i < 9 ; i++){
            btnsArr[i].setDisable(false);
        }
    }

    @FXML
    private void onMediumClicked(ActionEvent event) {
        this.mode = "medium";
            disableDifficultyButtons(true);
            for (int i = 0 ; i < 9 ; i++){
            btnsArr[i].setDisable(false);
        }
    }

    @FXML
    private void onHardClicked(ActionEvent event) {
         this.mode = "hard";
            disableDifficultyButtons(true);
            for (int i = 0 ; i < 9 ; i++){
            btnsArr[i].setDisable(false);
        }
    }

    
    private void addButtonsToArray() {
        btnsArr[0] = Btn11;
        btnsArr[1] = btn12;
        btnsArr[2] = btn13;
        btnsArr[3] = Btn21;
        btnsArr[4] = Btn22;
        btnsArr[5] = Btn23;
        btnsArr[6] = Btn31;
        btnsArr[7] = Btn32;
        btnsArr[8] = Btn33;
    }

    private void computerTurn() {
        if (printO) {
            int nextMove = AML
                    .setTheNextPlay(playerXMoves, moves);
            if (nextMove == -1) {

                while (true) { 
                    int index = random.nextInt(9); 
                    if (btnsArr[index].getText().equals("")) {
                        btnsArr[index].setText("O");
                        
                        bString += btnsArr[index].getId() + btnsArr[index].getText() + "-";
                        
                        btnsArr[index].setDisable(true);
                        btnsArr[index].setTextFill(javafx.scene.paint.Color.valueOf("#FFFFFF"));
                        playerOMoves.add(index + 1);
                        int check = AML.isGameOver(playerXMoves, playerOMoves); // Check If I Wined !
                        if (check == -1) {
                            printO = true;

                        } else {
                            printO = false;
                            SetResultForMedium(check);
                        }
                        break;
                    }
                }
            } else {
                moves.add(nextMove);
                System.out.println("" + nextMove);
                nextMove--;
                if (btnsArr[nextMove].getText().equals("")) {
                    btnsArr[nextMove].setText("O");
                    
                    bString += btnsArr[nextMove].getId() + btnsArr[nextMove].getText() + "-";
                    
                    btnsArr[nextMove].setDisable(true);
                    btnsArr[nextMove].setTextFill(javafx.scene.paint.Color.valueOf("#FFFFFF"));
                    playerOMoves.add(nextMove + 1);
                    int check = AML.isGameOver(playerXMoves, playerOMoves); // Check If I Wined !
                    if (check == -1) {
                        printO = true;

                    } else {
                        printO = false;
                        SetResultForMedium(check);
                    }
                } else {
                    computerTurn();
                }
            }
        }
    }

    public void SetResultForMedium(int Num) {
        switch (Num) {
            case 1:
                xScore++;
                xResult++;
                break;
            case 2:
                oScore++;
                oResult++;
                break;
            case 0:
                draws++;
                break;
        }
        disableAllXOButton();
        PlayerScore.setText("" + xScore);
        PCScore.setText("" + oScore);
        TiedScore.setText("" + draws);
        if (xResult > oResult) {

            xResult = 0;
            oResult = 0;

        } else if (xResult < oResult) {
            xResult = 0;
            oResult = 0;

        } else {

            xResult = 0;
            oResult = 0;

        }

    }

    void disableAllXOButton() {
        
        for (int i = 0 ; i < 9 ; i++){
            btnsArr[i].setDisable(true);
        }
        
    }

    void disableDifficultyButtons(boolean x) {
        EasyBtn.setDisable(x);
        MediumBtn.setDisable(x);
        HardBtn.setDisable(x);

    }

    private void ComputerMove(int player, int move) {
        if (move == 0) {
            move = AHL.isGameOver() == 0 ? AHL.GenerateMove(player) : 0;
        }
        String PText = (player == -1) ? "O" : "X";

        switch (move) {
            case 1:
                Btn11.setDisable(true);
                Btn11.setTextFill(javafx.scene.paint.Color.valueOf("#edf1f2"));
                Btn11.setText(PText);
                
                bString += Btn11.getId() + Btn11.getText() + "-";
                
                AHL.Move(move, player);
                break;
            case 2:
                btn12.setDisable(true);
                btn12.setTextFill(javafx.scene.paint.Color.valueOf("#edf1f2"));
                btn12.setText(PText);
                
                bString += btn12.getId() + btn12.getText() + "-";
                
                AHL.Move(move, player);
                break;
            case 3:
                btn13.setDisable(true);
                btn13.setTextFill(javafx.scene.paint.Color.valueOf("#edf1f2"));
                btn13.setText(PText);
                
                bString += btn13.getId() + btn13.getText() + "-";
                
                AHL.Move(move, player);
                break;
            case 4:
                Btn21.setDisable(true);
                Btn21.setTextFill(javafx.scene.paint.Color.valueOf("#edf1f2"));
                Btn21.setText(PText);
                
                bString += Btn21.getId() + Btn21.getText() + "-";
                
                AHL.Move(move, player);
                break;
            case 5:
                Btn22.setDisable(true);
                Btn22.setTextFill(javafx.scene.paint.Color.valueOf("#edf1f2"));
                Btn22.setText(PText);
                
                bString += Btn22.getId() + Btn22.getText() + "-";
                
                AHL.Move(move, player);
                break;
            case 6:
                Btn23.setDisable(true);
                Btn23.setTextFill(javafx.scene.paint.Color.valueOf("#edf1f2"));
                Btn23.setText(PText);
                
                bString += Btn23.getId() + Btn23.getText() + "-";
                
                AHL.Move(move, player);
                break;
            case 7:
                Btn31.setDisable(true);
                Btn31.setTextFill(javafx.scene.paint.Color.valueOf("#edf1f2"));
                Btn31.setText(PText);
                
                bString += Btn31.getId() + Btn31.getText() + "-";
                
                AHL.Move(move, player);
                break;
            case 8:
                Btn32.setDisable(true);
                Btn32.setTextFill(javafx.scene.paint.Color.valueOf("#edf1f2"));
                Btn32.setText(PText);
                
                bString += Btn32.getId() + Btn32.getText() + "-";
                
                AHL.Move(move, player);
                break;
            case 9:
                Btn33.setDisable(true);
                Btn33.setTextFill(javafx.scene.paint.Color.valueOf("#edf1f2"));
                Btn33.setText(PText);
                
                bString += Btn33.getId() + Btn33.getText() + "-";
                
                AHL.Move(move, player);
                break;
        }

    }

    public void SetResulForHard(int Num) {
        switch (Num) {
            case 1:
                xScore++;
                xResult++;
            case -1:
                oScore++;
                oResult++;
                break;
            case 2:
                draws++;
                TiedScore.setText("" + draws);
                System.out.println("" + draws);

                break;
            case 0:
                xScore = 0;
                oScore = 0;
                draws = 0;
                break;
        }

        disableAllXOButton();
        PlayerScore.setText("" + xScore);
        PCScore.setText("" + oScore);
        if (xResult > oResult) {

            xResult = 0;
            oResult = 0;

        } else if (xResult < oResult) {

            xResult = 0;
            oResult = 0;

        } else {
            xResult = 0;
            oResult = 0;
        }

    }

    private Vector<Integer> getAvailableIndeces() {
        Vector<Integer> available = new Vector();
        for (int i = 0; i < 9; i++) {
            if (btnsArr[i].getText().equals("")) {
                available.add(i);
            }
        }
        return available;
    }

    private int getRandomAvailableButton() {
        Vector<Integer> availableindeces = getAvailableIndeces();
        int availableButtonsLength = availableindeces.size();
        int rand = 0;

        while (true) {
            rand = random.nextInt(availableButtonsLength);
            if (btnsArr[availableindeces.get(rand)].getText().equals("")) {
                break;
            }
        }
        return availableindeces.get(rand);
    }

    private void printXOForEasy(int i) {
        if (btnsArr[i].getText().equals("")) {
            btnsArr[i].setText("X");
            
            bString += btnsArr[i].getId() + btnsArr[i].getText() + "-";
            
            btnsArr[i].setDisable(true);
            btnsArr[i].setTextFill(javafx.scene.paint.Color.valueOf("#CA2727"));
            playerXMoves.add(i + 1);
            int checkResult = getResult(playerXMoves, playerOMoves);
            if (checkResult == -1) {
                int o = getRandomAvailableButton();
                if (btnsArr[o].getText().equals("")) {
                    btnsArr[o].setText("O");
                    
                    bString += btnsArr[o].getId() + btnsArr[o].getText() + "-";
                    
                    btnsArr[o].setDisable(true);
                    btnsArr[o].setTextFill(javafx.scene.paint.Color.valueOf("#FFFFFF"));
                    playerOMoves.add(o + 1);
                    int check = getResult(playerXMoves, playerOMoves);
                    if (check == -1) {
                        printO = true;

                    } else {
                        printO = false;
                        SetResultForMedium(check);
                    }

                }

            } else {

                SetResultForMedium(checkResult);
            }

        }
    }

    private void printXOForMedium(int i) {

        btnsArr[i].setText("X");
        
        bString += btnsArr[i].getId() + btnsArr[i].getText() + "-";
        
        btnsArr[i].setDisable(true);
        btnsArr[i].setTextFill(javafx.scene.paint.Color.BLACK);
        playerXMoves.add(i + 1);
        int checkGameEnded = AML.isGameOver(playerXMoves, playerOMoves);
        if (checkGameEnded == -1) {
            printO = true;
            computerTurn();
        } else {
            printO = false;
            SetResultForMedium(checkGameEnded);
        }

    }

    public int getResult(ArrayList<Integer> playerXSteps, ArrayList<Integer> playerOSteps) {
        List<List> winningLists = new ArrayList<>();
        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List bottomRow = Arrays.asList(7, 8, 9);
        List leftcol = Arrays.asList(1, 4, 7);
        List midcol = Arrays.asList(2, 5, 8);
        List rightcol = Arrays.asList(3, 6, 9);
        List diagonalOne = Arrays.asList(1, 5, 9);
        List diagonalTwo = Arrays.asList(3, 5, 7);
        winningLists.add(topRow);
        winningLists.add(midRow);
        winningLists.add(bottomRow);
        winningLists.add(leftcol);
        winningLists.add(midcol);
        winningLists.add(rightcol);
        winningLists.add(diagonalOne);
        winningLists.add(diagonalTwo);
        if (playerXSteps.size() + playerOSteps.size() >= 5) {

            for (List l : winningLists) {
                if (playerXSteps.containsAll(l)) {
                    return 1;
                } else if (playerOSteps.containsAll(l)) {
                    return 2;
                }
            }
            if (playerXSteps.size() + playerOSteps.size() == 9) {
                return 0;
            }
        }

        return -1;
    }
    
    @FXML
    private void goToHistoryPlayerVsPc(ActionEvent event) throws IOException {
        
        SceneNavigator sceneNavigator = new SceneNavigator();
        sceneNavigator.navigateBtn(event, "/views/HistoryPlayerVsPC.fxml");
            
    }

     @FXML
    private void goBack(MouseEvent event) throws IOException {
    SceneNavigator sceneNavigator = new SceneNavigator();
    sceneNavigator.navigateImg(event, "/views/PlayerVsPCName.fxml");
    }


    @FXML
    private void playAgain(ActionEvent event) {
//        for (int i = 0 ; i < 9 ; i++){
//            btnsArr[i].setText("");
//            btnsArr[i].setDisable(true);
//            btnsArr[i].setVisible(true);
//        }
//        disableDifficultyButtons(false);
    }

    @FXML
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

    @FXML
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
                            btnsArr[(Integer.parseInt(Character.toString(arrString[i].charAt(0))) - 1)].setText(Character.toString(arrString[i].charAt(1)));
                            btnsArr[(Integer.parseInt(Character.toString(arrString[i].charAt(0))) - 1)].setDisable(true);
                        }


                    }

                    fis.close();
                }
            }
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }
    }
    
}
