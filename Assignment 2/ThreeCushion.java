package hw2;

import api.PlayerPosition;
import api.BallType;
import static api.PlayerPosition.*;
import static api.BallType.*;

/**
 * Class that models the game of three-cushion billiards.
 * 
 * @author Aden Koziol
 */
public class ThreeCushion {
	/**Holds the current inning */
	private int inning = 1;
	/**Holds the current ball */
	private BallType currentBall;
	/**Holds player A's ball */
	private BallType cueBall1;
	/** Holds player B's Ball */
	private BallType cueBall2;
	/**Holds the current player */
	private PlayerPosition currentPlayer;
	/**Holds the amount of points it takes to win */
	private int winningPoints;
	/**Tells whether it is the break shot or not*/
	private boolean isBreak = true;
	/** Tells whether or not the inning is started */
	private boolean inningStarted;
	/**Tells whether or not the shot is started */
	private boolean shotStarted;
	/**Holds whether or not the shot is valid*/
	private boolean isValid = false;
	/**Holds the amount of times the ball has hit the wall */
	private int impacts;
	/** Holds the amount of times the cue ball has struck another ball*/
	private int ballsStruck;
	/**Temporary ball so I know whether or not the ball has already been hit on the shot*/
	private BallType ballTemp;
	/**Player A's Score */
	private int playerAScore = 0;
	/**Player B's Score */
	private int playerBScore = 0;
	/**Holds whether or not the shot was a bank shot*/
	private boolean bankShot;
	/**Holds whether or not the game is over*/
	private boolean gameOver = false;
	/**Holds whether or not the lag winner chose their ball*/
	private boolean lagWinnerChose = false;
	
	/**
	 * Returns a one-line string representation of the current game state. The
	 * format is:
	 * <p>
	 * <tt>Player A*: X Player B: Y, Inning: Z</tt>
	 * <p>
	 * The asterisks next to the player's name indicates which player is at the
	 * table this inning. The number after the player's name is their score. Z is
	 * the inning number. Other messages will appear at the end of the string.
	 * 
	 * @return one-line string representation of the game state
	 */
	public String toString() {
		String fmt = "Player A%s: %d, Player B%s: %d, Inning: %d %s%s";
		String playerATurn = "";
		String playerBTurn = "";
		String inningStatus = "";
		String gameStatus = "";
		if (getInningPlayer() == PLAYER_A) {
			playerATurn = "*";
		} else if (getInningPlayer() == PLAYER_B) {
			playerBTurn = "*";
		}
		if (isInningStarted()) {
			inningStatus = "started";
		} else {
			inningStatus = "not started";
		}
		if (isGameOver()) {
			gameStatus = ", game result final";
		}
		return String.format(fmt, playerATurn, getPlayerAScore(), playerBTurn, getPlayerBScore(), getInning(),
				inningStatus, gameStatus);
	}
	/**
	 * Creates a new game of three-cushion billiards with a given lag winner and the predetermined number of points required to win the game.
	 * @param lagWinner
	 * winner of the lag
	 * @param pointsToWin
	 * amount of points to win the game
	 */
	public ThreeCushion(PlayerPosition lagWinner, int pointsToWin)
	{
		currentPlayer = lagWinner;
		winningPoints = pointsToWin;
	}
	/**
	 * Indicates the given ball has impacted the given cushion.
	 */
	public void cueBallImpactCushion()
	{
		impacts++;
		if(impacts == 3 && ballsStruck == 0)
			bankShot = true;
	}
	/**
	 * Indicates the player's cue ball has struck the given ball.
	 * @param ball
	 * ball that has been struck by cue ball
	 */
	public void cueBallStrike(BallType ball)
	{
		if(isGameOver() == false) 
		{
			if(ballTemp != ball)
			{
				ballsStruck++;
				ballTemp = ball;
				if(ballsStruck > 1 && impacts >= 3)
				{
					if(currentPlayer == PLAYER_A)
						playerAScore++;
					if(currentPlayer == PLAYER_B)
						playerBScore++;
					isValid = true;
				}
			}
			if(playerAScore == winningPoints || playerBScore == winningPoints)
			{
				gameOver = true;
				inningStarted = false;
				shotStarted = false;
			}
		}
	}
	/**
	 * Indicates the cue stick has struck the given ball.
	 * @param ball
	 * ball that the cue has struck
	 */
	public void cueStickStrike(BallType ball)
	{
		if(isGameOver() == false)
		{
			bankShot = false;
			//ballTemp = null;
			if(currentBall == ball)
			{
				inningStarted = true;
				shotStarted = true;
			}
			if(currentBall != ball)
			{
				foul();
			}
		}
		else
		{
			inningStarted = false;
			shotStarted = false;
		}
	}
	/**
	 * 	
Indicates that all balls have stopped motion.
	 */
	public void endShot()
	{ 
		if(isBreakShot() == true && isShotStarted() == true)
		{
			inning++;
			if(currentPlayer == PLAYER_A)
			{
				currentPlayer = PLAYER_B;
				currentBall = cueBall2;
			}
			else
			{
				currentPlayer = PLAYER_A;
				currentBall = cueBall1;
			}
		}
		if(isValid == false)
			inningStarted = false;
		shotStarted = false;
		isBreak = false;
		impacts = 0;
		ballsStruck = 0;
		ballTemp = null;
		isValid = false;
	}
	/**
	 * 	
A foul immediately ends the player's inning, even if the current shot has not yet ended.
	 */
	public void foul()
	{
		
		if(isGameOver() == false)
		{
			shotStarted = false;
			inningStarted = false;
			if(lagWinnerChose == true)
				inning++;
			if(currentPlayer == PLAYER_A)
			{
				currentPlayer = PLAYER_B;
				currentBall = cueBall2;
			}
			else
			{
				currentPlayer = PLAYER_A;
				currentBall = cueBall1;
			}
			isValid = false;
		}
		if(isGameOver() == true)
			inning = 1;
	}
	/**
	 * 	
Gets the cue ball of the current player.
	 * @return currentBall
	 * ball of current player
	 */
	public BallType getCueBall()
	{
		return currentBall;
	}
	/**
	 * 	
Gets the inning number.
	 * @return inning
	 * current inning of the game
	 */
	public int getInning()
	{
		return inning;
	}
	/**
	 * 	
Gets the current player.
	 * @return currentPlayer
	 * the inning player
	 */
	public PlayerPosition getInningPlayer()
	{
		return currentPlayer;
	}
	/**
	 * 	
Gets the number of points scored by Player A.
	 * @return playerAScore
	 * the score of player a
	 */
	public int getPlayerAScore()
	{
		return playerAScore;
	}
	/**
	 * Gets the number of points scored by Player B.
	 * @return playerBScore
	 * Score of player B
	 */
	public int getPlayerBScore()
	{
		return playerBScore;
	}
	/**
	 * Returns true if and only if the most recently completed shot was a bank shot.
	 * @return bankShot
	 * if the shot was a bankShot
	 */
	public boolean isBankShot()
	{
		return bankShot;
	}
	/**
	 * 	
Returns true if and only if this is the break shot (i.e., the first shot of the game).
	 * @return isBreak
	 * if the shot was a break
	 */
	public boolean isBreakShot()
	{
		return isBreak;
	}
	/**
	 * 	
Returns true if the game is over (i.e., one of the players has reached the designated number of points to win).
	 * @return gameOver
	 * if the game is over
	 */
	public boolean isGameOver()
	{
		return gameOver;
	}
	/**
	 * 	
Returns true if the shooting player has taken their first shot of the inning.
	 * @return inningStarted
	 * if the inning is started
	 */
	public boolean isInningStarted()
	{
		return inningStarted;
	}
	/**
	 * Returns true if a shot has been taken (see cueStickStrike()), but not ended (see endShot()).
	 * @return shotStarted
	 * if the shot is started
	 */
	public boolean isShotStarted()
	{
		return shotStarted;
	}
	/**
	 * Sets whether the player that won the lag chooses to break (take first shot), or chooses the other player to break.
	 * @param selfBreak
	 * if the lag winner would like to break
	 * @param cueBall
	 * cue ball lag winner chose
	 */
	public void lagWinnerChooses(boolean selfBreak, BallType cueBall)
	{
		currentBall = cueBall;
		cueBall1 = cueBall;
		if(cueBall1 == WHITE)
			cueBall2 = YELLOW;
		else
			cueBall2 = WHITE;
		lagWinnerChose = true;
		if(selfBreak == false)
		{
			if(currentPlayer == PLAYER_A)
			{
				currentPlayer = PLAYER_B;
				currentBall = cueBall2;
			}
			else
			{
				currentPlayer = PLAYER_A;
				currentBall = cueBall2;
			}
		}
	}
}
