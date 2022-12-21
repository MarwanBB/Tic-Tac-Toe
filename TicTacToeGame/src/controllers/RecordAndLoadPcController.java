
package controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.SceneNavigator;


public class RecordAndLoadPcController implements Initializable {

    @FXML
    private ListView<String> ListId;
    String name;
    private Parent root;
    private Scene scene;
    private Stage stage;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        File file = new File("src/Records/PVE/");
        String[] fileList = file.list();
        for (String name : fileList) {
            System.out.println(name);
        }
        ObservableList<String> games = FXCollections.observableArrayList(fileList);
        ListId.getItems().addAll(fileList);
        ListId.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    File file = new File("src/Records/PVE/" + ListId.getSelectionModel().getSelectedItem());
                    System.out.println("src/Records/" + games.get(0));
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/GamePlayerVsPC.fxml"));
                    root = loader.load();
                    GamePlayerVsPCController pve = loader.getController();
                    pve.load(file);
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(RecordAndLoadPcController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        });
    }

    @FXML
    private void goBack(MouseEvent event) {
        SceneNavigator.navigate("/views/GamePlayerVsPC.fxml");
    }

}
