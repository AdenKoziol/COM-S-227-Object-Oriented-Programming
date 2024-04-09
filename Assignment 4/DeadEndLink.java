package hw4;

import api.Point;
import api.PositionVector;
/** @author Aden Koziol */
public class DeadEndLink extends AbstractLink 
{
	public DeadEndLink()
	{
		
	}
			
	
	@Override
	public void shiftPoints(PositionVector positionVector) 
	{
		
	}

	/**
	 * Gets the point that is connected to the given point by the link. Returns null if no point is connected to the given point.
	 * @param point point which needs connection
	 * @return the connected point or null
	 */
	@Override
	public Point getConnectedPoint(Point point) 
	{
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
		return 1;
	}
}
