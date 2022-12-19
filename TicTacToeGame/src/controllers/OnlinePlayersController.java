/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import models.OnlinePlayers;
import models.SceneNavigator;

/**
 * FXML Controller class
 *
 * @author marwan
 */
public class OnlinePlayersController implements Initializable {

    @FXML
    private ImageView imageStar;
    @FXML
    private ImageView backImg;

    @FXML
    private TableView<OnlinePlayers> tableId;

    @FXML
    private TableColumn<OnlinePlayers, String> player1;
    @FXML
    private Button refreshButton;

    private static ArrayList<String> availablePlayers = new ArrayList<String>();

    Client client;

    OnlinePlayers onlinePlayers;

    public static ArrayList<String> getAvailablePlayers() {
        return availablePlayers;
    }

    public static void setAvailablePlayers(ArrayList<String> availablePlayers) {
        OnlinePlayersController.availablePlayers = availablePlayers;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Initialization
        client = Client.getInstance();

        tableId.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (tableId.getSelectionModel().getSelectedItem() != null) {

                }

            }
        });

    }

    @FXML
    private void refreshButtonClicked(ActionEvent event) {
        refresh();
    }

    public static void emptyAvailablePlayersListInView() {
        availablePlayers = new ArrayList<String>();
    }

    public static void addToAvailablePlayersList(String str) {
        availablePlayers.add(str);
    }

    public static void removeFromAvailablePlayersList(String str) {

    }

    void refreshOnlineAnyTime() {
    }

    @FXML
    private void goBack(MouseEvent event) {
            SceneNavigator.navigate("/views/SignIn.fxml");
        
    }

    private void refresh() {
    }

}
