
package controllers;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import models.SceneNavigator;


public class TiedOnlineController implements Initializable {

    @FXML
    private MediaView mediaView;
    @FXML
    private ImageView imageBack;
    private File file;
    private Media media;
    private MediaPlayer mediaPlayer;


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
