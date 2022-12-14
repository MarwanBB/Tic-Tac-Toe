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
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import models.SceneNavigator;

/**
 *
 * @author Copy Center
 */
public class GameOnlineController implements Initializable {
    
    
    
    @FXML
    private Label label;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    @FXML
    private void goBack(MouseEvent event) throws IOException {
    SceneNavigator sceneNavigator = new SceneNavigator();
    sceneNavigator.navigateImg(event, "/views/SignIn.fxml");
    }
    
    @FXML
    private void goToHistoryOnline(ActionEvent event) throws IOException {
        
        SceneNavigator sceneNavigator = new SceneNavigator();
        sceneNavigator.navigateBtn(event, "/views/HistoryOnline.fxml");
            
    }
    
    
    
    
}
