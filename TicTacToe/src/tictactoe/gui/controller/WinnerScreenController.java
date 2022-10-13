package tictactoe.gui.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import tictactoe.gui.TicTacToe;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WinnerScreenController implements Initializable {
    @FXML
    public Label win;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(()->{
            int winner = Integer.parseInt(((Stage) returnBtn.getScene().getWindow()).getUserData().toString());
            switch (winner) {
                case (-1):
                    win.setText("The game is a draw");
                    break;
                case (1):
                    win.setText("Player X is the winner");
                    break;
                case (2):
                    win.setText("Player O is the winner");
                    break;
            }
        });
    }
    @FXML
    public Button returnBtn;
    public void returnToMenu(ActionEvent actionEvent) throws IOException {
        Parent root = new FXMLLoader(TicTacToe.class.getResource("views/MenuScreen.fxml")).load();
        Stage stage = ((Stage) returnBtn.getScene().getWindow());
        stage.setScene(new Scene(root));
        stage.setTitle("Menu");
    }

    @FXML
    public Button btnNewGame;


    public void handleNewGame(ActionEvent actionEvent) throws IOException {
        Parent root = new FXMLLoader(TicTacToe.class.getResource("views/TicTacView.fxml")).load();
        Stage stage = ((Stage) btnNewGame.getScene().getWindow());
        stage.setScene(new Scene(root));
        stage.setTitle("Menu");
    }
}
