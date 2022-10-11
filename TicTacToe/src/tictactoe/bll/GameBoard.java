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
     * Gets the id of the winner, -1 if its a draw.
     *
     * @return int id of winner, or -1 if draw.
     */
    public int getWinner()
    {
        //TODO Implement this method
        return (1);
    }

    /**
     * Resets the game to a new game state.
     */
    public void newGame()
    {
        //TODO Implement this method
    }

}
