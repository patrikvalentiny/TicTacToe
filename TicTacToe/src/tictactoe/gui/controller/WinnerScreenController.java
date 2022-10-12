package tictactoe.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import tictactoe.gui.TicTacToe;

import java.io.IOException;

public class WinnerScreenController {
    @FXML
    public Button returnBtn;
    public void returnToMenu(ActionEvent actionEvent) throws IOException {
        Parent root = new FXMLLoader(TicTacToe.class.getResource("views/MenuScreen.fxml")).load();
        Stage stage = ((Stage) returnBtn.getScene().getWindow());
        stage.getIcons().add(new Image(TicTacToe.class.getResource("images/Ai.png").toExternalForm()));
        stage.setScene(new Scene(root));
        stage.setTitle("Menu");
    }


    public void handleNewGame(ActionEvent actionEvent) {
    }
}
