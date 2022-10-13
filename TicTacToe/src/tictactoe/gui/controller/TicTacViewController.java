/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.gui.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import tictactoe.bll.GameBoard;
import tictactoe.bll.IGameModel;
import tictactoe.gui.TicTacToe;


/**
 *
 * @author Stegger
 */
public class TicTacViewController implements Initializable {
    @FXML
    private Label lblPlayer;

    @FXML
    private GridPane gridPane;

    private static final String TXT_PLAYER = "Player: ";
    private IGameModel game;
    private static final int GAME_SIZE = 3;
    private String[][] buttonArray = new String[3][3];

    @FXML
    private void handleButtonAction(ActionEvent event) {
        try {
            if (!game.isGameOver()) {
                Integer row = GridPane.getRowIndex((Node) event.getSource());
                Integer col = GridPane.getColumnIndex((Node) event.getSource());
                int r = (row == null) ? 0 : row;
                int c = (col == null) ? 0 : col;

                Image imageX = new Image(new FileInputStream("TicTacToe/src/tictactoe/gui/images/X.png"));
                Image imageO = new Image(new FileInputStream("TicTacToe/src/tictactoe/gui/images/O.png"));
                ImageView Xview = new ImageView(imageX);
                ImageView Oview = new ImageView(imageO);

                Button btn = (Button) event.getSource();
                String xOrO = lblPlayer.getText().split(" ")[1];

                if (xOrO.charAt(0) == 'X')
                    btn.setGraphic(Xview);
                btn.setDisable(true);
                buttonArrayCreator(r, c, xOrO);
                int winner = game.getWinner(r, c, GAME_SIZE, xOrO, buttonArray);

                setPlayer(game.getNextPlayer(xOrO));

                if (game.isGameOver()) {
                    gameOverWindow(winner);
                }

                // playPC
                int [] arr = game.getBestMove(buttonArray);
                r = arr[0];
                c = arr[1];
                xOrO = lblPlayer.getText().split(" ")[1];
                //System.out.println("X = " + r + " Y = " + c);
                btn = ((Button) gridPane.getChildren().get(r*3 + c));
                btn.setGraphic(Oview);
                btn.setDisable(true);
                buttonArrayCreator(c, r, xOrO);
                winner = game.getWinner(c, r, GAME_SIZE, xOrO, buttonArray);
                setPlayer(game.getNextPlayer(xOrO));
                if (game.isGameOver()) {
                    gameOverWindow(winner);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void handleNewGame(ActionEvent event) {
        game.newGame();
        setPlayer("X");
        // makes an array of empty strings
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttonArray[i][j] = "";
            }
        }
        clearBoard();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        game = new GameBoard();
        generateBoard(GAME_SIZE,gridPane);
        handleNewGame(new ActionEvent());
    }

    public void generateBoard(int number, GridPane grid){
        int minSize = 10, pref = 100;
        for (int i = 0; i < number; i++) {
            var colC = new ColumnConstraints();
            colC.setMinWidth(minSize);
            colC.setPrefWidth(pref);
            colC.setHgrow(Priority.SOMETIMES);
            grid.getColumnConstraints().add(colC);
            var rowC = new RowConstraints();
            rowC.setMinHeight(minSize);
            rowC.setPrefHeight(pref);
            rowC.setVgrow(Priority.SOMETIMES);
            grid.getRowConstraints().add(rowC);
        }
        for (int i = 0; i < number; i++) {
            for (int j = 0; j < number; j++) {
                var btn = new Button("");
                btn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                btn.setOnAction(event -> handleButtonAction(event));
                btn.setId("btn");
                grid.add(btn, i, j);
            }
        }
    }
    private void setPlayer(String player)
    {
        lblPlayer.setText(TXT_PLAYER + player);
    }

    private void clearBoard() {
        for (Node n : gridPane.getChildren()) {
            Button btn = (Button) n;
            btn.setGraphic(null);
            btn.setDisable(false);
        }
    }

    private void buttonArrayCreator(int r, int c, String player) {
        this.buttonArray[r][c] = player;
    }
    @FXML
    private Button returnToMenuBtn;
    public void returnToMenu(ActionEvent actionEvent) throws IOException {
        Parent root = new FXMLLoader(TicTacToe.class.getResource("views/MenuScreen.fxml")).load();
        Stage stage = ((Stage) returnToMenuBtn.getScene().getWindow());
        stage.setScene(new Scene(root));
        stage.setTitle("Menu");
    }
    @FXML
    private Button btnNewGame;
    public void gameOverWindow(int winner) throws Exception
    {
        Parent root = new FXMLLoader(TicTacToe.class.getResource("views/WinnerScreen.fxml")).load();
        Stage stage = ((Stage) btnNewGame.getScene().getWindow());
        stage.setUserData(winner);
        stage.setScene(new Scene(root));
        stage.setTitle("Menu");

    }
}
