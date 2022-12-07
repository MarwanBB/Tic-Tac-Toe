/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.net.URL;
import java.io.File;
import java.util.ResourceBundle;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;


/**
 *
 * @author Yomna
 */
public class WinnerController implements Initializable {
    
    @FXML
    private MediaView mediaView;
    
    @FXML 
    private File file;
    private Media media;
    private MediaPlayer mediaPlayer;
    
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        file = new File("src/media/win.mp4");
        media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        mediaView.setMediaPlayer(mediaPlayer);
        
        
    }    
    public void playMedia(){
        mediaPlayer.play();
    }
}
