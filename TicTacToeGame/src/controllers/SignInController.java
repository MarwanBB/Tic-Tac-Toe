package controllers;

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
import models.AlertBoxOneButton;
import models.SceneNavigator;
import models.User;

public class SignInController extends Thread implements Initializable {

    @FXML
    private ImageView backBtn;
    @FXML
    private TextField userTxt;
    @FXML
    private PasswordField passwordTxt;
    @FXML
    private Button signInButton;
    @FXML
    private Button signInBtn1;

    
    Client client;

    
    String nameregex = "[a-zA-Z0-9\\._\\-]{3,}";
    String passregex = "[a-zA-Z0-9\\._\\-]{3,15}";

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void goToSignUp(ActionEvent event) {
        SceneNavigator.navigate("/views/SignUp.fxml");

    }

    @FXML
    private void goBackToMenu(MouseEvent event) {
        SceneNavigator.navigate("/views/Menu.fxml");
        //   threadSignIn.stop();

    }

    @FXML
    private void signInClicked(ActionEvent event) {

        System.out.println("sign in clicked");

        if (userTxt.getText().length() == 0 && passwordTxt.getText().length() == 0) {
            AlertBoxOneButton.createAlert("Sign In Failed", "Please enter a username and a password", "Ok");
        }

        if (userTxt.getText().length() > 0 && passwordTxt.getText().length() == 0) {
            AlertBoxOneButton.createAlert("Sign In Failed", "Please enter a password", "Ok");
        }
        if (userTxt.getText().length() == 0 && passwordTxt.getText().length() > 0) {
            AlertBoxOneButton.createAlert("Sign In Failed", "Please enter a username", "Ok");
        }

        if (userTxt.getText().length() > 0 && passwordTxt.getText().length() > 0) {
            if (userTxt.getText().matches(nameregex) && passwordTxt.getText().matches(passregex)) {
                try {
                    User user = new User(userTxt.getText(), passwordTxt.getText());
                    System.out.println(user.getUsername());

                    client = Client.getInstance(Util.ip, Util.port);
                    client.setUser(user);
                    client.clientSignInRequest(user);
                 
                } catch (NullPointerException ex) {

                }

            } else {
                AlertBoxOneButton.createAlert("Invalid Input", "Please enter a valid user name or password", "Ok");

            }

        }

    }

    
}
