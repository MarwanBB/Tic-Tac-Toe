package controllers;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
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

public class SignInController implements Initializable {

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        client = Client.getInstance();
    }

    @FXML
    private void goToRegistration(ActionEvent event) throws IOException {

        SceneNavigator sceneNavigator = new SceneNavigator();
        sceneNavigator.navigateBtn(event, "/views/Registration.fxml");

    }

    @FXML
    private void goBackToMenu(MouseEvent event) throws IOException {
        SceneNavigator sceneNavigator = new SceneNavigator();
        sceneNavigator.navigateImg(event, "/views/Menu.fxml");
    }

    @FXML
    private void signInClicked(ActionEvent event) throws IOException {

        User user = new User(userTxt.getText(), passwordTxt.getText());

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    client.clientSignInRequest(user);
                    if (client.readResponse().equals("userFound")) {
                        System.out.println("found the user in platform run later");
                        SceneNavigator sceneNavigator = new SceneNavigator();
                        sceneNavigator.navigateBtn(event, "/views/GamePVP.fxml");
                    }
                } catch (IOException ex) {
                    Logger.getLogger(SignInController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }

    void goToOnlineGame() {

    }

}
