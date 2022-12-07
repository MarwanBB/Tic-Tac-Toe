

package models;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class SceneNavigator {
    
    public void navigateBtn(ActionEvent event , String str) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource(str));
            
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public void navigateImg(MouseEvent event , String str) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource(str));
            
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    


}
