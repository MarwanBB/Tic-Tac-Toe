/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import database.LocalDataBase;
import database.SingleDataBase;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import models.GameDataPVP;
import models.GameDataPlayerVsPC;
import models.SceneNavigator;

/**
 *
 * @author Yomna
 */
public class HistoryPlayerVsPCController implements Initializable {
    
    private Label label;
    @FXML
    private ImageView imageStar;
    @FXML
    private ImageView backImg;
    @FXML
    private StackPane stackpane;
    @FXML
    private TableView<GameDataPlayerVsPC> table;
    @FXML
    private TableColumn<GameDataPlayerVsPC, String> gameDate;
    @FXML
    private TableColumn<GameDataPlayerVsPC, String> player;
    @FXML
    private TableColumn<GameDataPlayerVsPC, String> score;
    @FXML
    private TableColumn<GameDataPlayerVsPC, String> pcScore;
    @FXML
    private TableColumn<GameDataPlayerVsPC, String> winner;
    
    String xName ,oName;
    SingleDataBase singleDB;
    String data;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            singleDB = new SingleDataBase();
            data = singleDB.singleData;
            String line []= data.split("//t");
            GameDataPlayerVsPC game ;
            ObservableList<GameDataPlayerVsPC> elements = FXCollections.observableArrayList();
            for(int i = 0;i< line.length;i++){
                String[] items = line[i].split(",");
                game = new GameDataPlayerVsPC(items[0], items[1], items[2], items[3], items[4], items[5]);
                elements.add(game);
            }
            gameDate.setCellValueFactory(new PropertyValueFactory<GameDataPlayerVsPC, String>("gameDate"));
            player.setCellValueFactory(new PropertyValueFactory<GameDataPlayerVsPC, String>("player1"));
            score.setCellValueFactory(new PropertyValueFactory<GameDataPlayerVsPC, String>("player1Score"));
            pcScore.setCellValueFactory(new PropertyValueFactory<GameDataPlayerVsPC, String>("player2Score"));
            winner.setCellValueFactory(new PropertyValueFactory<GameDataPlayerVsPC, String>("winner"));
            table.setItems(elements);
            
        } catch (IOException ex) {
            Logger.getLogger(HistoryPlayerVsPCController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void goBackToGamePlayerVsPc(MouseEvent event) throws IOException {
    SceneNavigator sceneNavigator = new SceneNavigator();
    sceneNavigator.navigateImg(event, "/views/GamePlayerVsPC.fxml");
    }
    
}
