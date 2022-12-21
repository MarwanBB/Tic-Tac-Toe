/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import models.SceneNavigator;

/**
 * FXML Controller class
 *
 * @author Copy Center
 */
public class TiedOnlineController implements Initializable {

    @FXML
    private MediaView mediaView;
    @FXML
    private ImageView imageBack;
     private File file;
    private Media media;
    private MediaPlayer mediaPlayer;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        media = new Media(getClass().getResource("Tie.mp4").toExternalForm());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        mediaView.setMediaPlayer(mediaPlayer);
    }    

    @FXML
    private void goBack(MouseEvent event) {
        mediaPlayer.pause();
        SceneNavigator.navigate("/views/OnlinePlayers.fxml");
        
    }
    
}
