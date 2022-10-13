/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.bll;


/**
 *
 * @author Stegger
 */
public class GameBoard implements IGameModel {
    private int gameOver = -2;
    private int moves = 0;

    /**
     * Attempts to let the current player play at the given coordinates. It the
     * attempt is succesfull the current player has ended his turn and it is the
     * next players turn.
     * this method will return an integer that we use to identify game state and the winner.
     * 1 is X, 0 is O, -1 if it is a draw, and -2 simply shows the game is still running.
     */
    public int getWinner(int x, int y, int gameSize, String playerString, String[][] buttonArray) {
        moves++;
        // check column
        for (int i = 0; true; i++) {
            if (!buttonArray[x][i].equals(playerString)) {
                break;
            }
            if (i == gameSize - 1) {
                gameOver = playerString.equals("X") ? 1 : 2;
                return gameOver;
            }
        }

        // check row
        for (int i = 0; true; i++) {
            if (!buttonArray[i][y].equals(playerString)) {
                break;
            }
            if (i == gameSize - 1) {
                gameOver = playerString.equals("X") ? 1 : 2;
                return gameOver;
            }
        }

        // check diagonal
        if (x == y) {
            for (int i = 0; true; i++) {
                if (!buttonArray[i][i].equals(playerString)) {
                    break;
                }
                if (i == gameSize - 1) {
                    gameOver = playerString.equals("X") ? 1 : 2;
                    return gameOver;
                }
            }
        }
        // check anti-diagonal
        if (x + y == gameSize - 1) {
            for (int i = 0; true; i++) {
                if (!buttonArray[i][(gameSize - 1) - i].equals(playerString)) {
                    break;
                }
                if (i == gameSize - 1) {
                    gameOver = playerString.equals("X") ? 1 : 2;
                    return gameOver;
                }
            }
        }
        //check draw
        if (moves == Math.pow(gameSize, 2)) {
            gameOver = -1;
            return -1;
        }
        return gameOver;
    }

    /**
     * Returns X for player X, O for player O.
     *
     * @return String Placer of the next player.
     */
    public String getNextPlayer(String lastPlayer) {
        if (lastPlayer.charAt(0) == 'X')
            return "O";
        return "X";
    }


    public boolean isGameOver() {
        return gameOver != -2;
    }

    /**
     * Resets the game to a new game state.
     */
    public void newGame() {
        gameOver = -2;
        moves = 0;
    }
}
