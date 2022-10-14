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
    private static int gameOver = -2;
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
    private static int miniMax(String[][] board, int depth, boolean isMax) {
        int boardVal = evaluateBoard(board, depth);

        // Terminal node (win/lose/draw) or max depth reached.
        if (Math.abs(boardVal) > 0 || depth == 0 || boardVal == -1) {
            return boardVal;
        }
        // Maximising player, find the maximum attainable value.
        if (isMax) {
            int highestVal = Integer.MIN_VALUE;
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    if (board[row][col].equals("")) {
                        board[row][col] =  "O";
                        highestVal = Math.max(highestVal, miniMax(board,depth - 1, false));
                        board[row][col] = "";
                    }
                }
            }
            return highestVal;
            // Minimising player, find the minimum attainable value;
        } else {
            int lowestVal = Integer.MAX_VALUE;
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    if (board[row][col].equals("")) {
                        board[row][col] = "X";
                        lowestVal = Math.min(lowestVal, miniMax(board,depth - 1, true));
                        board[row][col] = "";
                    }
                }
            }
            return lowestVal;
        }
    }

    /**
     * Evaluate every legal move on the board and return the best one.
     * @param board Board to evaluate
     * @return Coordinates of best move
     */
    public int[] getBestMove(String[][] board) {
        int[] bestMove = new int[]{-1, -1};
        int bestValue = Integer.MIN_VALUE;

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col].equals("")) {
                    board[row][col] = "O";
                    int moveValue = miniMax(board, 7, false);
                    //System.out.println(moveValue);
                    //System.out.println(row + " " + col);
                    board[row][col] = "";
                    if (moveValue > bestValue) {
                        bestMove[0] = row;
                        bestMove[1] = col;
                        bestValue = moveValue;
                    }
                }
            }
        }
        return bestMove;
    }

    /**
     * Evaluate the given board from the perspective of the O player, return
     * 10 if a winning board configuration is found, -10 for a losing one and 0
     * for a draw.
     * @param board Board to evaluate
     * @return value of the board
     */
    private static int evaluateBoard(String[][] board, int depth) {
        int rowSum = 0;
        int bWidth = 3;
        int Xwin = 'X' * bWidth;
        int Owin = 'O' * bWidth;

        // Check rows for winner.
        for (int row = 0; row < bWidth; row++) {
            for (int col = 0; col < bWidth; col++) {
                rowSum += board[row][col].equals("") ? ' ' : board[row][col].charAt(0);
            }
            if (rowSum == Xwin) {
                return -10 - depth;
            } else if (rowSum == Owin) {
                return 10 + depth;
            }
            rowSum = 0;
        }
        // Check columns for winner.
        for (int col = 0; col < bWidth; col++) {
            for (int row = 0; row < bWidth; row++) {
                rowSum += board[row][col].equals("") ? ' ' : board[row][col].charAt(0);
            }
            if (rowSum == Xwin) {
                return -10 - depth;
            } else if (rowSum == Owin) {
                return 10 + depth;

            }
            rowSum = 0;
        }
        // Check diagonals for winner.
        // Top-left to bottom-right diagonal.
        for (int i = 0; i < bWidth; i++) {
            rowSum += board[i][i].equals("") ? ' ' : board[i][i].charAt(0);
        }
        if (rowSum == Xwin) {
            return -10 - depth;
        } else if (rowSum == Owin) {
            return 10 + depth;

        }

        // Top-right to bottom-left diagonal.
        rowSum = 0;
        int indexMax = bWidth - 1;
        for (int i = 0; i <= indexMax; i++) {
            rowSum += board[i][indexMax-i].equals("") ? ' ' : board[i][indexMax-i].charAt(0);
        }
        if (rowSum == Xwin) {
            return -10 - depth;
        } else if (rowSum == Owin) {
            return 10 + depth;
        }
        // draw
        int drawSum = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!board[i][j].equals("") )
                    drawSum++;
            }
        }
        if (drawSum == 9){
            return -1;
        }
        return 0;
    }
}
