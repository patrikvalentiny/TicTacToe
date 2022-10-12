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
public interface IGameModel
{
    public int getWinner(int x, int y, int gameSize, String playerString, String[][] buttonArray);
    /**
     * Returns 0 for player 0, 1 for player 1.
     *
     * @return int Id of the next player.
     */
    public String getNextPlayer(String lastPlayer);

    /**
     * Tells us if the game has ended either by draw or by meeting the winning
     * condition.
     *
     * @return true if the game is over, else it will retun false.
     */
    public boolean isGameOver();

    /**
     * Resets the game to a new game state.
     */
    public void newGame();
    public int[] PCPlayer(String[][] buttonArray);
}
