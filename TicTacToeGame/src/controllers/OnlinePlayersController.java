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

    public static String playerUsername;

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
                System.out.println("finally?");
                if (tableId.getSelectionModel().getSelectedItem() != null & !tableId.getSelectionModel().getSelectedItem().getPlayerUsername().equals(client.getUser().getUsername())) {
                    System.out.println(tableId.getSelectionModel().getSelectedItem().getPlayerUsername());
                    // to invite the player clicked on the tableview.
                    client.clientInvitePlayer(tableId.getSelectionModel().getSelectedItem().getPlayerUsername());

                    //to remove the selection from the row instantly after clicking any row.
                    tableId.getSelectionModel().clearSelection();

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
        //new OnlinePlayersController().refresh();
    }

    public static void removeFromAvailablePlayersList(String str) {

    }

    private void refresh() {
        try {
            client.clientRefreshOnlineAnyTimeClient();

            TimeUnit.MILLISECONDS.sleep(300);

            ObservableList<OnlinePlayers> elements = FXCollections.observableArrayList();

            for (int i = 0; i < availablePlayers.size(); i++) {
                System.out.println("string to be added to elements :::: " + availablePlayers.get(i));
                elements.add(new OnlinePlayers(availablePlayers.get(i)));
                System.out.println("element ======" + elements.get(i));

            }

            player1.setCellValueFactory(new PropertyValueFactory<OnlinePlayers, String>("playerUsername"));
            tableId.setItems(elements);

        } catch (InterruptedException ex) {
            Logger.getLogger(OnlinePlayersController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void goBack(MouseEvent event) {
        System.out.println("player username that hsould apepar offline" + playerUsername);
        client.appearOffline(playerUsername);
        SceneNavigator.navigate("/views/SignIn.fxml");
        client.CloseRequest();
        client.closeClient();

    }

}
