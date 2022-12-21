package controllers;

import Utility.Constants;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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

    //  private static int signInRequestRunningFlag;
    Client client;

    //private static Thread threadSignIn;
    String nameregex = "[a-zA-Z0-9\\._\\-]{3,}";
    String passregex = "[a-zA-Z0-9\\._\\-]{3,15}";

    /* public static Thread getThreadSignIn() {
        return threadSignIn;
    }*/

 /*public static void setThreadSignIn(Thread threadSignIn) {
        SignInController.threadSignIn = threadSignIn;
    }*/
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

//                    if (client.getStr().equals("userFoundAfterSignInRequest")) {
//                            OnlinePlayersController.playerUsername = user.getUsername();
//                            client.clientRefreshOnlineOnSignIn(user);
//                            client.setUser(user);
//
//                            SceneNavigator.navigate("/views/OnlinePlayers.fxml");
//
//                        }
                    // threadSignIn = new Thread() {
                    //   @Override
                    //  public void run() {
                    //checking if this user exists
                    // client.clientSignInRequest(user);
                    /* new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            // here goes your code to delay
                            if (client.getStr().equals("userFoundAfterSignInRequest")) {

                                client.clientRefreshOnlineOnSignIn(user);
                                client.setUser(user);

                                SceneNavigator.navigate("/views/OnlinePlayers.fxml");

                            }
                        }
                    }, 300L);*/
 /*  Platform.runLater(new Runnable() {
                        @Override
                        public void run() {

                            // waiting(suspend thread) till the result of searching for the user is returned
                            // used thread.resume() at the Client after str = dataInputStream.readLine();, so that the next lines
                            // are executed ONLY after the readLine() happens.
                            //signInRequestRunningFlag = 1;
                            //threadSignIn.suspend();
                            //signInRequestRunningFlag = 0;
                            client.clientSignInRequest(user);
                            if (client.getStr().equals("userFoundAfterSignInRequest")) {

                               // client.clientRefreshOnlineOnSignIn(user);
                                client.setUser(user);

                                SceneNavigator.navigate("/views/OnlinePlayers.fxml");

                            }
                        }
                    });*/
                    //  };
                    //    threadSignIn.start();
                } catch (NullPointerException ex) {

                }

            } else {
                AlertBoxOneButton.createAlert("Invalid Input", "Please enter a valid user name or password", "Ok");

            }

        }

    }

    /*  public static int getSignInRequestRunningFlag() {
        return signInRequestRunningFlag;
    }*/

 /*public static void setSignInRequestRunningFlag(int signInRequestRunningFlag) {
        SignInController.signInRequestRunningFlag = signInRequestRunningFlag;
    }*/
}
