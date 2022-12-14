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
import models.SceneNavigator;


/**
 *
 * @author Yomna
 */
public class WinnerController implements Initializable {
    
    @FXML
    private MediaView mediaView;
    
    private File file;
    private Media media;
    private MediaPlayer mediaPlayer;
    @FXML
    private Button backBtn;
    @FXML
    private ImageView imageBack;
    
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        file = new File("src/Videos/Win.mp4");
        media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        mediaView.setMediaPlayer(mediaPlayer);
        
        
    }    
    public void playMedia(){
        mediaPlayer.play();
    }

    @FXML
    private void goBack(MouseEvent event) {
        try {
            SceneNavigator sceneNavigator = new SceneNavigator();
            sceneNavigator.navigateImg(event, "/views/GamePVP.fxml");
        } catch (IOException ex) {
            Logger.getLogger(WinnerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
