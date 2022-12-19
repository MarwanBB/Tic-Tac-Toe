/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import models.SceneNavigator;

/**
 * FXML Controller class
 *
 * @author marwan
 */
public class ExtraGamesController implements Initializable {

    @FXML
    private Button snakesAndLadders;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void gotoSnakesAndLaddersGame(ActionEvent event) {
        SceneNavigator.navigate("/views/Snakes.fxml");
    }
    
}