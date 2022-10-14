package tictactoe.gui.controller;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import tictactoe.gui.TicTacToe;

import java.io.IOException;


public class MenuScreenController extends Application {
    @FXML
    Button btnPVE;

    @FXML
    Button btnPVP;

    @FXML
    private void actionPVE(ActionEvent actionEvent) throws IOException {
        Parent root = new FXMLLoader(TicTacToe.class.getResource("views/TicTacView.fxml")).load();
        Stage stage = ((Stage) btnPVE.getScene().getWindow());
        stage.setUserData("B");
        stage.setScene(new Scene(root));
        stage.setTitle("Player vs AI");
    }
    @FXML
    private void actionPVP(ActionEvent actionEvent) throws IOException {
        Parent root = new FXMLLoader(TicTacToe.class.getResource("views/TicTacView.fxml")).load();
        Stage stage = ((Stage) btnPVE.getScene().getWindow());
        stage.setUserData("P");
        stage.setScene(new Scene(root));
        stage.setTitle("Player vs Player");
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

    }
}




