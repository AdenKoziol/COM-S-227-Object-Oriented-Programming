package hw4;

import api.Point;
import api.PositionVector;
/** @author Aden Koziol */
public class SwitchLink extends AbstractLink
{
	private boolean entered = false; /*whether or not the train has entered the crossing*/
	private boolean turn; /*turn for setTurn method*/
	private TurnLink turnLink; /**TurnLink object so I didn't have to repeat code*/
	private Point endpointA; /**holds one of the given endpoints in constructor*/
	private Point endpointB; /**holds one of the given endpoints in constructor*/
	private Point endpointC; /**holds one of the given endpoints in constructor*/
	
	/**
	 * The given endpoints correspond to the paths as labeled below.
	 * @param endpointA endpoint in link
	 * @param endpointB endpoint in link
	 * @param endpointC endpoint in link
	 */
	public SwitchLink(Point endpointA, Point endpointB, Point endpointC)
	{
		turnLink = new TurnLink(endpointA, endpointB, endpointC);
		this.endpointA = endpointA;
		this.endpointB = endpointB;
		this.endpointC = endpointC;
	}
	/**
	 * Updates the link to turn or not turn. The turn cannot be modified (do nothing) when the train is currently in (entered but not exited) the crossing.
	 * @param turn true for turn and false for not turn
	 */
	public void setTurn(boolean turn)
	{
		if(entered == false)
			this.turn = turn;
	}
	
	/**
	 * Gets the point that is connected to the given point by the link. Returns null if no point is connected to the given point.
	 * @param point point which needs connection
	 * @return the connected point or null
	 */
	@Override
	public Point getConnectedPoint(Point point) 
	{
		if(turn == true)
			return turnLink.getConnectedPoint(point);
		else if(turn == false)
		{
			if(point == endpointA)
				return endpointB;
			if(point == endpointB)
				return endpointA;
			if(point == endpointC)
				return endpointA;
		}
		return null;
	}

	/**
	 * This method is called by the simulation to indicate a train has entered the crossing.
	 */
	@Override
	public void trainEnteredCrossing() 
	{
		entered = true;
	}

	/**
	 * This method is called by the simulation to indicate a train has exited the crossing.
	 */
	@Override
	public void trainExitedCrossing() 
	{
		entered = false;
	}

	/**
	 * Gets the total number of paths connected by the link.
	 * @return the total number of paths
	 */
	@Override
	public int getNumPaths() 
	{
		return 3;
	}

	@Override
	public void shiftPoints(PositionVector positionVector) 
	{
		super.shiftPoints(positionVector);
	}
}
