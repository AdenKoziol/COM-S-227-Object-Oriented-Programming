package hw4;

import api.Point;
import api.PositionVector;
/** @author Aden Koziol */
public class StraightLink extends AbstractLink
{
	private Point endpointA; /**holds one of the given endpoints in constructor*/
	private Point endpointB; /**holds one of the given endpoints in constructor*/
	private Point endpointC; /**holds one of the given endpoints in constructor*/
	public StraightLink(Point endpointA, Point endpointB, Point endpointC)
	{
		this.endpointA = endpointA;
		this.endpointB = endpointB;
		this.endpointC = endpointC;
	}
	
	/**
	 * Gets the point that is connected to the given point by the link. Returns null if no point is connected to the given point.
	 * @param point point which needs connection
	 * @return the connected point or null
	 */
	@Override
	public Point getConnectedPoint(Point point) 
	{
		if(point == endpointA)
			return endpointB;
		else if(point == endpointB)
			return endpointA;
		else if(point == endpointC)
			return endpointA;
		else
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
		return 3;
	}

	@Override
	public void shiftPoints(PositionVector positionVector) 
	{
		super.shiftPoints(positionVector);
	}

}
