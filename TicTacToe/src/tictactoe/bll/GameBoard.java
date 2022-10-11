/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.bll;

import tictactoe.gui.controller.TicTacViewController;

/**
 *
 * @author Stegger
 */
public class GameBoard implements IGameModel
{
    /**
     * Gets the id of the winner, -1 if its a draw.
     *
     * @return int id of winner, or -1 if draw.
     */
    public int getWinner(int x, int y, int gameSize, String buttonString, String[][] buttonArray) {
        // check row
        for (int i = 0; true; i++) {
            if (!buttonArray[x][i].equals(buttonString)) {
                break;
            }
            if (i == gameSize - 1) {
                return buttonString.equals("X") ? 1 : 0;
            }
        }
        // check column
        for (int i = 0; true; i++) {
            if (!buttonArray[i][y].equals(buttonString)) {
                break;
            }
            if (i == gameSize - 1) {
                return buttonString.equals("X") ? 1 : 0;
            }
        }
        // check diagonal
        if (x == y) {
            for (int i = 0; true; i++) {
                if (!buttonArray[i][i].equals(buttonString)) {
                    break;
                }
                if (i == gameSize - 1) {
                    return buttonString.equals("X") ? 1 : 0;
                }
            }
        }
        // check anti-diagonal
        if (x + y == gameSize - 1) {
            for (int i = 0; true; i++) {
                if (!buttonArray[i][(gameSize - 1) - i].equals(buttonString)) {
                    break;
                }
                if (i == gameSize - 1) {
                    return buttonString.equals("X") ? 1 : 0;
                }
            }
        }
        //check draw
        for (int i = 0; i<3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttonArray[i][j].equals("")) {
                    break;
                }
                if (i == 2 && j == 2) {
                    return -1;
                }
            }
        }
        return -2;
    }

    /**
     * Returns X for player X, O for player O.
     *
     * @return String Placer of the next player.
     */
    public String getNextPlayer(String lastPlayer)
    {
        if(lastPlayer.charAt(0) == 'X')
            return "O";
        return "X";
    }

    /**
     * Attempts to let the current player play at the given coordinates. It the
     * attempt is succesfull the current player has ended his turn and it is the
     * next players turn.
     *
     * @param col column to place a marker in.
     * @param row row to place a marker in.
     * @return true if the move is accepted, otherwise false. If gameOver == true
     * this method will always return false.
     */
    public boolean play(int col, int row)
    {
        //TODO Implement this method
        return true;
    }

    public boolean isGameOver()
    {
        int g = TicTacViewController.getGameState();
        if (g == 1)
        {
            return true;
        }
        {
            return false;
        }

    }
    /**
     * Resets the game to a new game state.
     */
    public void newGame()
    {
        //TODO Implement this method
    }

}
