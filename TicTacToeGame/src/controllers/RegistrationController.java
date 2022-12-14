
package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import models.SceneNavigator;
import models.User;



public class RegistrationController implements Initializable {

    @FXML
    private TextField userTxt;
    @FXML
    private PasswordField passwordTxt;
    @FXML
    private ImageView backImg;
    @FXML
    private Button signupBtn;
    
    Client client;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        client = new Client();
    }    
    
    @FXML
    private void goBackToSignIn(MouseEvent event) throws IOException {
    SceneNavigator sceneNavigator = new SceneNavigator();
    sceneNavigator.navigateImg(event, "/views/SignIn.fxml");
    }

    @FXML
    private void signUpClicked(ActionEvent event) {
        //        SceneNavigator sceneNavigator = new SceneNavigator();
        //        sceneNavigator.navigateBtn(event, "/views/GameOnline.fxml");

        User user = new User(userTxt.getText(), passwordTxt.getText());
        
        System.out.println("username and password are going to be sent to the client");
        
        client.sendMessage(user);
        
        
        
        //DataBaseAccessLayer.SignUp();
        
    }
    
}
