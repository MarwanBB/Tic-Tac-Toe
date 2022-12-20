/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import models.SceneNavigator;


/**
 *
 * @author Copy Center
 */
public class MenuController implements Initializable {
    
    private Label label;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void goToGameSinglePlayer(ActionEvent event) {
        
        SceneNavigator.navigate("/views/PlayerVsPCName.fxml");

    }

    @FXML
    private void goToGamePVP(ActionEvent event) {
        SceneNavigator.navigate("/views/PVPNames.fxml");
            
    }

    @FXML
    private void goToGameOnline(ActionEvent event)  {
        SceneNavigator.navigate("/views/SignIn.fxml");
        
    }

    private void goToExtraGames(ActionEvent event) {
        SceneNavigator.navigate("/views/ExtraGames.fxml");
    }
    
}
