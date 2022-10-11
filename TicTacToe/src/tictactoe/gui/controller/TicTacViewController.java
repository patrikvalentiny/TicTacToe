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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import tictactoe.bll.GameBoard;
import tictactoe.bll.IGameModel;


/**
 *
 * @author Stegger
 */
public class TicTacViewController implements Initializable
{
    @FXML
    private Label lblPlayer;

    @FXML
    private Button btnNewGame;

    @FXML
    private GridPane gridPane;
    
    private static final String TXT_PLAYER = "Player: ";
    private IGameModel game;
    private static final int gameSize = 3;
    private int moveCount = 0;
    // gameState is 0 for game running, 1 for found a winner, -1 for a draw
    private static int gameState = 0;
    private String[][] buttonArray = new String[3][3];

    @FXML
    private void handleButtonAction(ActionEvent event)
    {
        try
        {
            Integer row = GridPane.getRowIndex((Node) event.getSource());
            Integer col = GridPane.getColumnIndex((Node) event.getSource());
            int r = (row == null) ? 0 : row;
            int c = (col == null) ? 0 : col;
            if (game.play(c, r))
            {
                Image imageX = new Image(new FileInputStream("TicTacToe/src/tictactoe/gui/images/X.png"));
                Image imageO = new Image(new FileInputStream("TicTacToe/src/tictactoe/gui/images/O.png"));
                ImageView Xview = new ImageView(imageX);
                ImageView Oview = new ImageView(imageO);
                Button btn = (Button) event.getSource();
                String xOrO = lblPlayer.getText().split(" ")[1];
                if(xOrO.charAt(0) == 'X')
                    btn.setGraphic(Xview);
                else
                    btn.setGraphic(Oview);
                //btn.setText(xOrO);
                btn.setDisable(true);
                buttonArrayCreator(r, c, xOrO);
                int winner = game.getWinner(r, c, gameSize, xOrO, buttonArray);
                setGameState(winner);
                System.out.println(gameState);
                setPlayer(game.getNextPlayer(xOrO));

                if (game.isGameOver())
                {
                    displayWinner(winner);
                }
            }

        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void handleNewGame(ActionEvent event)
    {
        game.newGame(); // this
        setPlayer("X");
        clearBoard();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        game = new GameBoard();
        setPlayer("X");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttonArray[i][j] = "";
            }
        }
    }

    private void setPlayer(String player)
    {
        lblPlayer.setText(TXT_PLAYER + player);
    }

    private void displayWinner(int winner) // Make sure it works
    {
        String message;
        switch (winner)
        {
            case -1:
                message = "It's a draw :-(";
                break;
            default:
                message = "Player " + winner + " wins!!!";
                break;
        }
        lblPlayer.setText(message);
    }

    private void clearBoard()
    {
        for(Node n : gridPane.getChildren())
        {
            Button btn = (Button) n;
            btn.setText("");
            btn.setGraphic(null);
            btn.setDisable(false);
        }
    }

    public static int getGameState() {
        return gameState;
    }

    public void setGameState(int gameState) {
        TicTacViewController.gameState = gameState;
    }
    private void buttonArrayCreator(int r, int c, String player){
        this.buttonArray[r][c] = player;
    }
}
