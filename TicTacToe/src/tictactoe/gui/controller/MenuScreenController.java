package tictactoe.gui.controller;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;


public class MenuScreenController extends Application {
    @FXML
    Button btnPVE;

    @FXML
    Button btnPVP;

    @FXML
    private void actionPVP(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("views/MenuScreen.fxml"));
        Stage stage = new Stage();
        stage.setTitle("PVP Tic Tack Toe");
        stage.setScene(new Scene(root));
        //stage.centerOnScreen();
        //stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void actionPVE()throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("views/TicTacView.fxml"));
        Stage window = (Stage) btnPVE.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }
}




