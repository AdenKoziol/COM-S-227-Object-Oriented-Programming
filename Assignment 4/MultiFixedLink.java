package hw4;

import api.Point;
import api.PointPair;
import api.PositionVector;
/** @author Aden Koziol */
public class MultiFixedLink extends AbstractLink
{
	private PointPair[] connections; /** array of connections in the link */
	
	/**
	 * 	Creates a new MultiFixedLink.	
	 * @param connections array of connections in the link
	 */
	public MultiFixedLink(PointPair[] connections)
	{
		this.connections = connections;
	}
	
	/**
	 * Gets the point that is connected to the given point by the link. Returns null if no point is connected to the given point.
	 * @param point point which needs connection
	 * @return the connected point or null
	 */
	@Override
	public Point getConnectedPoint(Point point) 
	{
		for(int i = 0; i < connections.length; i++)
		{
			if(connections[i].getPointA() == point)
				return connections[i].getPointB();
		}
		for(int i = 0; i < connections.length; i++)
		{
			if(connections[i].getPointB() == point)
				return connections[i].getPointA();
		}
		return null;
	}

	@Override
	public void trainEnteredCrossing() 
	{

	}

	@Override
	public void trainExitedCrossing() 
	{
		
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
