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
        if (moves == 1) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (!buttonArray[i][j].equals("")){
                        if (i == 1 && j == 1){
                            coordinates[0] = 0;
                            coordinates[1] = 2;
                            return coordinates;
                        }
                        else if (i == 1){
                            coordinates[0] = 2;
                            coordinates[1] = j;
                            return coordinates;
                        } else if (j == 1){
                            coordinates[0] = i;
                            coordinates[1] = 2;
                            return coordinates;
                        } else if (i == 0 || i == 2 || j == 0 || j == 2){
                            coordinates[0] = 1;
                            coordinates[1] = 1;
                            return coordinates;
                        }
                    }
                }
            }
        } else {
            int AIState = 0;
            String[][] testArrayO = new String[3][3];
            String[][] testArrayX = new String[3][3];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    testArrayO[i][j] = buttonArray[i][j];
                    testArrayX[i][j] = buttonArray[i][j];
                }
            }

            for (int x = 0; x < 3; x++) {
                for (int y = 0; y < 3; y++) {
                    if (buttonArray[x][y].equals("")) {
                        testArrayO[x][y] = "O";
                        testArrayX[x][y] = "X";
                        //System.out.println(Arrays.deepToString(testArrayO));
                        if (canWin(x, y, 3, "O", testArrayO)) {
                            AIState = 1;
                        } else if (canDefend(x, y, 3, "X", testArrayX)) {
                            AIState = -1;
                        }

                        if (AIState == 1) {
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
        }
        return coordinates;
    }
    private boolean canWin(int x, int y, int gameSize, String playerString, String[][] testArray) {
        //Winning condition
        // check column
        //System.out.println(Arrays.deepToString(testArrayO));
        for (int i = 0; true; i++) {
            if (!testArray[x][i].equals(playerString)) {
                break;
            }
            if (i == gameSize - 1) {
                return true;
            }
        }

        // check row
        for (int i = 0; true; i++) {
            if (!testArray[i][y].equals(playerString)) {
                break;
            }
            if (i == gameSize - 1) {
                return true;
            }
        }

        // check diagonal
        if (x == y) {
            for (int i = 0; true; i++) {
                if (!testArray[i][i].equals(playerString)) {
                    break;
                }
                if (i == gameSize - 1) {
                    return true;
                }
            }
        }
        // check anti-diagonal
        if (x + y == gameSize - 1) {
            for (int i = 0; true; i++) {
                if (!testArray[i][(gameSize - 1) - i].equals(playerString)) {
                    break;
                }
                if (i == gameSize - 1) {
                    return true;
                }
            }
        }
        return false;
    }
    private boolean canDefend(int x, int y, int gameSize, String playerString, String[][] testArray) {
        // Check if you have to defend against a winning move
        // check column
        //System.out.println(Arrays.deepToString(testArrayO));
        for (int i = 0; true; i++) {
            if (!testArray[x][i].equals(playerString)) {
                break;
            }
            if (i == gameSize - 1) {
                return true;
            }
        }

        // check row
        for (int i = 0; true; i++) {
            if (!testArray[i][y].equals(playerString)) {
                break;
            }
            if (i == gameSize - 1) {
                return true;
            }
        }

        // check diagonal
        if (x == y) {
            for (int i = 0; true; i++) {
                if (!testArray[i][i].equals(playerString)) {
                    break;
                }
                if (i == gameSize - 1) {
                    return true;
                }
            }
        }
        // check anti-diagonal
        if (x + y == gameSize - 1) {
            for (int i = 0; true; i++) {
                if (!testArray[i][(gameSize - 1) - i].equals(playerString)) {
                    break;
                }
                if (i == gameSize - 1) {
                    return true;
                }
            }
        }
        return false;
    }
}
