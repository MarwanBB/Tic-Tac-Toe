package tictactoeserver;

/**
 * FXML Controller class
 *
 * @author admin
 */
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
    @FXML
    private Button refreshButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        DatabaseAccessLayer.setAllToOffline();
        
        // TODO
        buttonTurnOffServer.setVisible(false);
        refreshButton.setVisible(false);

        pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("online: "+DatabaseAccessLayer.getNumberOfOnlinePlayers(), DatabaseAccessLayer.getNumberOfOnlinePlayers()),
                new PieChart.Data("offline: "+DatabaseAccessLayer.getNumberOfOFFlinePlayers(), DatabaseAccessLayer.getNumberOfOFFlinePlayers())
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
            buttonTurnOffServer.setVisible(true);
            refreshButton.setVisible(true);
            chart.setVisible(true);
            isNotOpenedYet = false;
        } else {
            Server.getInstance().resume();
            buttonTurnOnServer.setDisable(true);
            buttonTurnOffServer.setDisable(false);
            chart.setVisible(true);

        }

    }

    @FXML
    private void turnOffServer(ActionEvent event) {
        try {
            buttonTurnOnServer.setDisable(false);
            buttonTurnOffServer.setDisable(true);

            Server.getInstance().stopAllClients();
            Server.getInstance().suspend();
            chart.setVisible(false);
            refreshButton.setVisible(false);

        } catch (IOException ex) {
            Logger.getLogger(ServerController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void refreshButtonClicked(ActionEvent event) {
         pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("online: "+DatabaseAccessLayer.getNumberOfOnlinePlayers(), DatabaseAccessLayer.getNumberOfOnlinePlayers()),
                new PieChart.Data("offline: "+DatabaseAccessLayer.getNumberOfOFFlinePlayers(), DatabaseAccessLayer.getNumberOfOFFlinePlayers())
        );
         chart.setData(pieChartData);
        
    }

}
