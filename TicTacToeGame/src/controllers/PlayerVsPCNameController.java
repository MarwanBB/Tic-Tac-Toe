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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.SceneNavigator;

/**
 * FXML Controller class
 *
 * @author Copy Center
 */
public class PlayerVsPCNameController implements Initializable {

    private TextField PlayerOTxt;
    @FXML
    private Button nextBtn;
    @FXML
    private ImageView backImg;
    @FXML
    private TextField PlayerTxt;
    
    private Parent root;
    private Scene scene;
    private Stage stage;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void goBackToMenu(MouseEvent event) {
        SceneNavigator.navigate("/views/Menu.fxml");
    }

    @FXML
    private void goToGameSinglePlayer(ActionEvent event) throws IOException {
       if(!PlayerTxt.getText().isEmpty()){
            
           String p1Name = PlayerTxt.getText();
           FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/GamePlayerVsPC.fxml"));
           root = loader.load();
           GamePlayerVsPCController pvpc = loader.getController();
           pvpc.DisplayNames(p1Name);
           stage =(Stage)((Node)event.getSource()).getScene().getWindow();
           scene = new Scene(root);
           stage.setScene(scene);
           stage.show();
        }
    }
    
}
