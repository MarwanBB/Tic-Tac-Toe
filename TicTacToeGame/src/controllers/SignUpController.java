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
    String nameregex = "[a-zA-Z0-9\\._\\-]{3,}";
    String passregex = "[a-zA-Z0-9\\._\\-]{3,15}";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        client = Client.getInstance(Util.ip, Util.port);
    }

    @FXML
    private void goBackToSignIn(MouseEvent event) {
        SceneNavigator.navigate("/views/SignIn.fxml");
    }

    @FXML
    private void signUpClicked(ActionEvent event) {
        if (userTxt.getText().matches(nameregex) && passwordTxt.getText().matches(passregex)) {

            User user = new User(userTxt.getText(), passwordTxt.getText());

            System.out.println("username and password are going to be sent to the client");

            client.clientSignUpRequest(user);

        } else {
            AlertBoxOneButton.createAlert("invalid Input", "Please enter a valid Username or Password", "ok");

        }

    }

}
