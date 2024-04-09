package hw4;

import api.Point;
import api.PointPair;
import api.PositionVector;
/** @author Aden Koziol */
public class MultiSwitchLink extends AbstractLink
{
	private boolean entered = false; /**variable to see if train has entered crossing*/
	private PointPair[] connections; /** array of connections in the link */
	private MultiFixedLink multiFixedLink; /**multiFixedLink variable so I didn't have to write extra lines of code*/
	
	/**
	 * Creates a new MultiSwitchLink. The given array of point pairs describes the connection. Each PointPair indicates where the train comes from and goes to.
	 * @param connections array of connections in the link
	 */
	public MultiSwitchLink(PointPair[] connections)
	{
		this.connections = connections;
	}
	/**
	 * Updates the connection point pair at the given index. The connection cannot be modified (method does nothing) when the train is currently in (entered but not exited) the crossing.
	 * @param pointPar given point pair
	 * @param index where to change in the array
	 */
	public void switchConnection(PointPair pointPar, int index)
	{
		if(entered == false)
			connections[index] = pointPar;
	}
	
	/**
	 * Gets the point that is connected to the given point by the link. Returns null if no point is connected to the given point.
	 * @param point point which needs connection
	 * @return the connected point or null
	 */
	@Override
	public Point getConnectedPoint(Point point) 
	{
		multiFixedLink = new MultiFixedLink(connections);
		return multiFixedLink.getConnectedPoint(point);
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
		return connections.length;
	}

	@Override
	public void shiftPoints(PositionVector positionVector) 
	{
		super.shiftPoints(positionVector);
	}

}
