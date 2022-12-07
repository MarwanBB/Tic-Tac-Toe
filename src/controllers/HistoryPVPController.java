/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import database.LocalDataBase;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import models.GameDataPVP;
import models.SceneNavigator;

/**
 *
 * @author Yomna
 */
public class HistoryPVPController implements Initializable {
    
    private Label label;
    @FXML
    private ImageView imageStar;
    @FXML
    private ImageView backImg;
    //private TableView<GameDataPVP> table;
    @FXML
    private TableColumn<GameDataPVP, String> gameDate;
    @FXML
    private TableColumn<GameDataPVP, String> player1;
    @FXML
    private TableColumn<GameDataPVP, String> score1;
    @FXML
    private TableColumn<GameDataPVP, String> player2;
    @FXML
    private TableColumn<GameDataPVP, String> score2;
    @FXML
    private TableColumn<GameDataPVP, String> winner;
    String xName ,oName;
    LocalDataBase localDB;
    String data;
    @FXML
    private TableView<GameDataPVP> tabelId;
    
    private void handleButtonAction(ActionEvent event) {
       
         
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            
            localDB = new LocalDataBase();
            data = localDB.localData;
            //System.out.print(data);
            String line []= data.split("//t");
            GameDataPVP game ;
            ObservableList<GameDataPVP> elements = FXCollections.observableArrayList();
            for(int i = 0;i< line.length;i++){
                String[] items = line[i].split(",");
                game = new GameDataPVP(items[0], items[1], items[2], items[3], items[4], items[5]);
                elements.add(game);
            }
            gameDate.setCellValueFactory(new PropertyValueFactory<GameDataPVP, String>("gameDate"));
            player1.setCellValueFactory(new PropertyValueFactory<GameDataPVP, String>("player1"));
            score1.setCellValueFactory(new PropertyValueFactory<GameDataPVP, String>("player1Score"));
            player2.setCellValueFactory(new PropertyValueFactory<GameDataPVP, String>("player2"));
            score2.setCellValueFactory(new PropertyValueFactory<GameDataPVP, String>("player2Score"));
            winner.setCellValueFactory(new PropertyValueFactory<GameDataPVP, String>("winner"));
            tabelId.setItems(elements);
            
        } catch (IOException ex) {
            Logger.getLogger(HistoryPVPController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }    

    @FXML
    private void goBackToGamePVP(MouseEvent event) throws IOException {
        SceneNavigator sceneNavigator = new SceneNavigator();
        sceneNavigator.navigateImg(event, "/views/GamePVP.fxml");
    }
}
