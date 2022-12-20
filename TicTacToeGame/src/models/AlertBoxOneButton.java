/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Optional;
import javafx.application.Platform;
import javafx.scene.control.ButtonType;
import javafx.stage.Window;

/**
 *
 * @author marwan
 */
public class AlertBoxOneButton {
    
    
    public static void createAlert(String strTitle, String contextMessage, String strButton){
        Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    ButtonType Ok = new ButtonType(strButton);
                                    javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.NONE, "", Ok);
                                    alert.setTitle(strTitle);
                                    alert.setContentText(contextMessage);
                                    Window window = alert.getDialogPane().getScene().getWindow();
                                    window.setOnCloseRequest(e -> alert.hide());
                                    Optional<ButtonType> result = alert.showAndWait();
                                }
                            });
    }
    
}
