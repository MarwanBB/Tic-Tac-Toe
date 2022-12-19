package models;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import main.MenuScreen;

public class SceneNavigator {
    
    public static void navigate(String destination) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Stage stage = MenuScreen.getStage();

                    stage.setResizable(false);
                    Parent root = FXMLLoader.load(getClass().getResource(destination));

                    Scene scene = new Scene(root);

                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(SceneNavigator.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    public static void navigateBtn(ActionEvent event, String str) {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource(str));

                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(SceneNavigator.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

    }

    public static void navigateImg(MouseEvent event, String str) {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource(str));

                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(SceneNavigator.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }

    

}
