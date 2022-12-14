/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import models.SceneNavigator;

/**
 * FXML Controller class
 *
 * @author Copy Center
 */
public class SignInController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void goToRegistration(ActionEvent event) throws IOException {
        
        SceneNavigator sceneNavigator = new SceneNavigator();
        sceneNavigator.navigateBtn(event, "/views/Registration.fxml");
            
    }
    
    @FXML
    private void goToOnlineGame(ActionEvent event) throws IOException {
        
        SceneNavigator sceneNavigator = new SceneNavigator();
        sceneNavigator.navigateBtn(event, "/views/GameOnline.fxml");
            
    }
    
    @FXML
    private void goBackToMenu(MouseEvent event) throws IOException {
    SceneNavigator sceneNavigator = new SceneNavigator();
    sceneNavigator.navigateImg(event, "/views/Menu.fxml");
    }
    
}
