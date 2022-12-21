
package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.SceneNavigator;
import models.AlertBoxOneButton;


public class PVPNamesController implements Initializable {

    @FXML
    private TextField PlayerXTxt;
    @FXML
    private TextField PlayerOTxt;
    @FXML
    private Button nextBtn;
    @FXML
    private ImageView backImg;

    private Parent root;
    private Scene scene;
    private Stage stage;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    @FXML
    private void goBackToMenu(MouseEvent event) {
        SceneNavigator.navigate("/views/Menu.fxml");
    }

    @FXML
    private void goToGamePVP(ActionEvent event) throws IOException {
        if (!PlayerXTxt.getText().isEmpty() && !PlayerOTxt.getText().isEmpty() && !PlayerXTxt.getText().contains(",") && !PlayerOTxt.getText().contains(",")) {

            String p1Name = PlayerXTxt.getText();
            String p2Name = PlayerOTxt.getText();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/GamePVP.fxml"));
            root = loader.load();
            GamePVPController pvp = loader.getController();
            pvp.DisplayNames(p1Name, p2Name);
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } else {
            AlertBoxOneButton.createAlert("Player Name", "The Name Shouldn't Contain (,) or be null", "OK");
        }
    }

}
