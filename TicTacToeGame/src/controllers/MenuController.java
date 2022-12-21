
package controllers;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import models.SceneNavigator;



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
        TextInputDialog textInput = new TextInputDialog();
            textInput.setTitle("Ip of the server");
            textInput.getDialogPane().setContentText("Please enter the IP of the server");
            Optional<String> result = textInput.showAndWait();
            TextField input = textInput.getEditor();
            Util.ip=input.getText();
            SceneNavigator.navigate("/views/SignIn.fxml");
        
    }

    @FXML
    private void goToExtraGames(ActionEvent event) {
        SceneNavigator.navigate("/views/ExtraGames.fxml");
    }
    
}
