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
    private TableView<?> table;
    @FXML
    private TableColumn<?, ?> gameDate;
    @FXML
    private TableColumn<?, ?> player;
    @FXML
    private TableColumn<?, ?> score;
    @FXML
    private TableColumn<?, ?> pcScore;
    @FXML
    private TableColumn<?, ?> winner;
    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
    
}
