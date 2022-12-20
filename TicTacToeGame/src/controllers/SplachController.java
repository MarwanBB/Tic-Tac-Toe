/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.MenuScreen;

/**
 * FXML Controller class
 *
 * @author Copy Center
 */
public class SplachController implements Initializable {

    @FXML
    private AnchorPane parent;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        new SplashScreen().start();
    }

    class SplashScreen extends Thread {

        @Override
        public void run() {
            try {
                Thread.sleep(2000);

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Stage stage = MenuScreen.getStage();
                            stage.setResizable(false);
                            Parent root = FXMLLoader.load(getClass().getResource("/views/Menu.fxml"));
                            Scene scene = new Scene(root);
                            stage.setScene(scene);
                            stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(SplachController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });

            } catch (InterruptedException ex) {
                Logger.getLogger(SplachController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
