/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.bll;

import java.util.Arrays;

/**
 *
 * @author Stegger
 */
public class GameBoard implements IGameModel
{
    private int gameOver = -2;
    private int moves = 0;
    /**
        * Attempts to let the current player play at the given coordinates. It the
     * attempt is succesfull the current player has ended his turn and it is the
     * next players turn.
        * this method will return an integer that we use to identify game state and the winner.
            *  1 is X, 0 is O, -1 if it is a draw, and -2 simply shows the game is still running.
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
        if (moves == Math.pow(gameSize, 2)){
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
    public String getNextPlayer(String lastPlayer)
    {
        if(lastPlayer.charAt(0) == 'X')
            return "O";
        return "X";
    }



    public boolean isGameOver()
    {
        return gameOver != -2;
    }

    /**
     * Resets the game to a new game state.
     */
    public void newGame()
    {
        gameOver = -2;
        moves = 0;
    }

    public int[] PCPlayer(String[][] buttonArray){
        int[] coordinates = new int[2];

        String[][] testArrayO = new String[3][3];
        String[][] testArrayX = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                testArrayO[i][j] = "";
                testArrayX[i][j] = "";
            }
        }

        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                if (buttonArray[x][y].equals("")){
                    testArrayO[x][y] = "O";
                    testArrayX[x][y] = "X";
                    //System.out.println(Arrays.deepToString(testArrayO));
                    int AIState = getBestMoveAI(x, y, 3, "O", "X", testArrayO, testArrayX);
                    if (AIState == 1){
                        System.out.println("AI found winning solution");
                        coordinates[0] = x;
                        coordinates[1] = y;
                        return coordinates;
                    } else if (AIState == -1) {
                        System.out.println("AI found not losing solution");
                        coordinates[0] = x;
                        coordinates[1] = y;
                        return coordinates;
                    } else {
                        testArrayO[x][y] = "";
                        testArrayX[x][y] = "";
                    }
                } else {
                    testArrayO[x][y] = buttonArray[x][y];
                    testArrayX[x][y] = buttonArray[x][y];
                }
            }
        }
        return coordinates;
    }
    private int getBestMoveAI(int x, int y, int gameSize, String playerString, String opponentString, String[][] testArrayO, String[][] testArrayX) {
        //Winning condition
        // check column
        //System.out.println(Arrays.deepToString(testArrayO));
        for (int i = 0; true; i++) {
            if (!testArrayO[x][i].equals(playerString)) {
                break;
            }
            if (i == gameSize - 1) {
                return 1;
            }
        }

        // check row
        for (int i = 0; true; i++) {
            if (!testArrayO[i][y].equals(playerString)) {
                break;
            }
            if (i == gameSize - 1) {
                return 1;
            }
        }

        // check diagonal
        if (x == y) {
            for (int i = 0; true; i++) {
                if (!testArrayO[i][i].equals(playerString)) {
                    break;
                }
                if (i == gameSize - 1) {
                    return 1;
                }
            }
        }
        // check anti-diagonal
        if (x + y == gameSize - 1) {
            for (int i = 0; true; i++) {
                if (!testArrayO[i][(gameSize - 1) - i].equals(playerString)) {
                    break;
                }
                if (i == gameSize - 1) {
                    return 1;
                }
            }
        }

        // not losing
        //check column
        for (int i = 0; true; i++) {
            if (!testArrayX[x][i].equals(opponentString)) {
                break;
            }
            if (i == gameSize - 1) {
                return -1;
            }
        }

        // check row
        for (int i = 0; true; i++) {
            if (!testArrayX[i][y].equals(opponentString)) {
                break;
            }
            if (i == gameSize - 1) {
                return -1;
            }
        }

        // check diagonal
        if (x == y) {
            for (int i = 0; true; i++) {
                if (!testArrayX[i][i].equals(opponentString)) {
                    break;
                }
                if (i == gameSize - 1) {
                    return -1;
                }
            }
        }
        // check anti-diagonal
        if (x + y == gameSize - 1) {
            for (int i = 0; true; i++) {
                if (!testArrayX[i][(gameSize - 1) - i].equals(opponentString)) {
                    break;
                }
                if (i == gameSize - 1) {
                    return -1;
                }
            }
        }
        return 0;
    }
}
