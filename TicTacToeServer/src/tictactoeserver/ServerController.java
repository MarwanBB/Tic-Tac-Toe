package tictactoeserver;



/**
 * FXML Controller class
 *
 * @author admin
 */
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class ServerController implements Initializable {

    @FXML
    private Button buttonTurnOnServer;
    @FXML
    private Button buttonTurnOffServer;
    boolean isNotOpenedYet = true;
    ObservableList<PieChart.Data> pieChartData;
    @FXML
    private PieChart chart;
   

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("online", DatabaseAccessLayer.getNumberOfOnlinePlayers()),
                new PieChart.Data("offline", DatabaseAccessLayer.getNumberOfOFFlinePlayers())
        );
       

        chart.setData(pieChartData);
        
         chart.setVisible(false);

    }

    @FXML
    private void turnOnServer(ActionEvent event) {
        if (isNotOpenedYet) {
            Server.getInstance().start();
            buttonTurnOnServer.setDisable(true);
            buttonTurnOffServer.setDisable(false);
            isNotOpenedYet = false;
            chart.setVisible(true);
           

        } else {
            Server.getInstance().resume();
            buttonTurnOnServer.setDisable(true);
            buttonTurnOffServer.setDisable(false);

        }

    }

    @FXML
    private void turnOffServer(ActionEvent event) {
        buttonTurnOnServer.setDisable(false);
        buttonTurnOffServer.setDisable(true);
        Server.getInstance().suspend();

    }

 
}
