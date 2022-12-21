/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.net.URL;
import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import models.SceneNavigator;


/**
 *
 * @author Yomna
 */
public class WinnerOnlineController implements Initializable {
    
    @FXML
    private MediaView mediaView;
    
    private File file;
    private Media media;
    private MediaPlayer mediaPlayer;
    @FXML
    private ImageView imageBack;
    private Text winner;
    
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        media = new Media(getClass().getResource("Win.mp4").toExternalForm());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        mediaView.setMediaPlayer(mediaPlayer);
        
    } 
    public void DisplayNames(String win) {
        winner.setText(win);
    }
    
    @FXML
    private void goBack(MouseEvent event) {
        mediaPlayer.pause();
        SceneNavigator.navigate("/views/OnlinePlayers.fxml");

    }
}
