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
    private void actionPVP(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(TicTacToe.class.getResource("views/TicTacView.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        ((Stage) btnPVP.getScene().getWindow()).close();
        stage.show();
    }

    @FXML
    private void actionPVE(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(TicTacToe.class.getResource("views/TicTacView.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        ((Stage) btnPVE.getScene().getWindow()).close();
        stage.show();

    }

    @Override
    public void start(Stage primaryStage) throws IOException {

    }
}




