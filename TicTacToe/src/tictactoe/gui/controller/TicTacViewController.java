/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.gui.controller;

import java.io.FileInputStream;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
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
                else
                    btn.setGraphic(Oview);
                btn.setDisable(true);

                buttonArrayCreator(r, c, xOrO);
                int winner = game.getWinner(r, c, GAME_SIZE, xOrO, buttonArray);
                System.out.println(winner);
                setPlayer(game.getNextPlayer(xOrO));

                if (game.isGameOver()) {
                    displayWinner(winner);
                    disableButtons();
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
        generateBoard(3,gridPane);
        handleNewGame(new ActionEvent());
    }

    private void setPlayer(String player) {
    public void generateBoard(int number, GridPane grid)
    {
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

    private void displayWinner(int winner) {
        String message;
        if (winner == -1) {
            message = "It's a draw :-(";
        } else {
            message = "Player " + winner + " wins!!!";
        }
        lblPlayer.setText(message);
    }

    private void clearBoard() {
        for (Node n : gridPane.getChildren()) {
            Button btn = (Button) n;
            btn.setText("");
            btn.setGraphic(null);
            btn.setDisable(false);
        }
    }

    private void buttonArrayCreator(int r, int c, String player) {
        this.buttonArray[r][c] = player;
    }

    private void disableButtons() {
        for (Node n : gridPane.getChildren()) {
            Button btn = (Button) n;
            btn.setDisable(true);
        }
    }
    @FXML
    Button returnToMenuBtn;


    public void returnToMenu(ActionEvent actionEvent) throws IOException {
        Parent root = new FXMLLoader(TicTacToe.class.getResource("views/MenuScreen.fxml")).load();
        Stage stage = ((Stage) returnToMenuBtn.getScene().getWindow());
        stage.getIcons().add(new Image(TicTacToe.class.getResource("images/Ai.png").toExternalForm()));
        stage.setScene(new Scene(root));
        stage.setTitle("Menu");
    }
}
