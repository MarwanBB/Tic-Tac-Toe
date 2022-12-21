package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.MenuScreen;
import models.SceneNavigator;

public class SnakesController implements Initializable {

    private Game game;

    @FXML
    private Canvas canvas;
    @FXML
    private Text text;

    private Timeline timeline;
    @FXML
    private ImageView imageScore;
    @FXML
    private ImageView backButton;

    @FXML
    private void exitAction(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void newAction(ActionEvent event) {
        game = new Game();
        repaint();
    }

    @FXML
    private void showColorPicker(ActionEvent event) {
        VBox box = new VBox();
        ColorPicker colorPicker = new ColorPicker();
        Button ok = new Button("Ok");

        box.getChildren().addAll(colorPicker, ok);
        Scene scene = new Scene(box);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        ok.setOnMouseClicked((MouseEvent event1) -> {
            SnakePart.setColor(colorPicker.getValue());
            stage.close();
        });

    }

    @FXML
    private void keyHandler(KeyEvent e) {
        if (e.getCode() == KeyCode.RIGHT) {
            game.snake.setDirection(0);
        } else if (e.getCode() == KeyCode.UP) {
            game.snake.setDirection(1);
        } else if (e.getCode() == KeyCode.LEFT) {
            game.snake.setDirection(2);
        } else if (e.getCode() == KeyCode.DOWN) {
            game.snake.setDirection(3);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imageScore.setVisible(false);
    }

    private void drawBackground(GraphicsContext gc) {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if ((i + j) % 2 == 0) {
                    gc.setFill(Color.web("#D6EAF8"));
                } else {
                    gc.setFill(Color.web("AED6F1"));
                }
                gc.fillRect(i * 400 / 20, j * 400 / 20, 400 / 20, 400 / 20);
            }

        }
    }

    public void repaint() {

        Duration duration = Duration.millis(250);
        timeline = new Timeline(new KeyFrame(duration, (ActionEvent event) -> {
            text.setText(String.valueOf(game.getScore()));
            imageScore.setVisible(true);
            GraphicsContext context = canvas.getGraphicsContext2D();
            drawBackground(context);
            if (!game.isGameOver()) {

                game.snake.getSnake().stream().forEach((part) -> {
                    context.setFill(SnakePart.getColor());
                    context.fillRect(part.getX(), part.getY(), SnakePart.getWidth(), SnakePart.getHeight());
                });
                context.setFill(Food.getColor());
                context.fillRect(game.food.getX(), game.food.getY(), SnakePart.getWidth(), SnakePart.getHeight());
            } else {
                context.setFill(Color.web("#EEFBE9"));
                context.fillRect(0, 0, 400, 400);
                context.setFill(Color.web("#9E2E28"));
                context.setFont(new Font(24));
                context.setTextAlign(TextAlignment.CENTER);
                context.fillText("Game Over!", 200, 200);
                timeline.stop();
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    @FXML
    private void backButtonClicked(MouseEvent event) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Stage stage = MenuScreen.getStage();

                    stage.setResizable(false);
                    Parent root = FXMLLoader.load(getClass().getResource("/views/ExtraGames.fxml"));

                    Scene scene = new Scene(root);

                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(SceneNavigator.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

}
