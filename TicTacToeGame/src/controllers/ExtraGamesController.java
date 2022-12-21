
package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javax.swing.JFrame;
import models.SceneNavigator;


public class ExtraGamesController implements Initializable {

    @FXML
    private Button snakesAndLadders;
    @FXML
    private Button bounceBall;
    @FXML
    private ImageView backImg;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    @FXML
    private void gotoSnakesAndLaddersGame(ActionEvent event) {
        SceneNavigator.navigate("/views/Snakes.fxml");
    }

    @FXML
    private void gotoBounceBallGame(ActionEvent event) {
        JFrame obj = new JFrame();

        GamePlay gamePlay = new GamePlay();
        obj.setBounds(10, 10, 700, 600);
        obj.setTitle("Brick Breaker");
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        obj.add(gamePlay);
    }

    @FXML
    private void goBackToMenu(MouseEvent event) {
        SceneNavigator.navigate("/views/Menu.fxml");
    }

}
