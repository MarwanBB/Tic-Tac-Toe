/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import models.SceneNavigator;

/**
 *
 * @author Yomna
 */
public class HistoryOnlineController implements Initializable {
    
    private Label label;
    @FXML
    private ImageView imageStar;
    @FXML
    private ImageView backImg;
    @FXML
    private StackPane stackpane;
    @FXML
    private TableView<?> table;
    @FXML
    private TableColumn<?, ?> gameDate;
    @FXML
    private TableColumn<?, ?> player1;
    @FXML
    private TableColumn<?, ?> score1;
    @FXML
    private TableColumn<?, ?> player2;
    @FXML
    private TableColumn<?, ?> score2;
    @FXML
    private TableColumn<?, ?> winner;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void goBackToGameOnline(MouseEvent event) {
    SceneNavigator.navigate("/views/GameOnline.fxml");
    }
    
}
