
package tictactoeserver;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;


public class ServerController implements Initializable {
    
    private Label label;
    @FXML
    private Button buttonTurnOnServer;
    @FXML
    private Button buttonTurnOffServer;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void turnOnServer(MouseEvent event) {
        new Server();
        buttonTurnOnServer.setDisable(true);
        buttonTurnOffServer.setDisable(false);
    }

    @FXML
    private void turnOffServer(MouseEvent event) {
        buttonTurnOnServer.setDisable(false);
        buttonTurnOffServer.setDisable(true);
    }
    
}
