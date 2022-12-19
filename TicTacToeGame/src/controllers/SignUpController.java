
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



public class SignUpController implements Initializable {

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
        client = Client.getInstance();
    }    
    
    @FXML
    private void goBackToSignIn(MouseEvent event){
    SceneNavigator.navigate("/views/SignIn.fxml");
    }

    @FXML
    private void signUpClicked(ActionEvent event) {

        User user = new User(userTxt.getText(), passwordTxt.getText());
        
        System.out.println("username and password are going to be sent to the client");
        
        client.clientSignUpRequest(user);
        
        
    }
    
}
