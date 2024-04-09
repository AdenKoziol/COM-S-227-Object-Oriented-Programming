package hw4;

import api.Point;
import api.PositionVector;
import api.Path;
/** @author Aden Koziol */
public class CouplingLink extends AbstractLink
{
	private Point endpoint1; /**holds one of the given endpoints in constructor*/
	private Point endpoint2; /**holds one of the given endpoints in constructor*/
	
	/**
	 * Creates a new CouplingLink that connects the two given endpoints.
	 * @param endpoint1 point of one of the paths
	 * @param endpoint2 point on the other path
	 */
	public CouplingLink(Point endpoint1, Point endpoint2)
	{
		this.endpoint1 = endpoint1;
		this.endpoint2 = endpoint2;
	}
	
	@Override
	public void shiftPoints(PositionVector positionVector) 
	{
		super.shiftPoints(positionVector);
	}

	/**
	 * Gets the point that is connected to the given point by the link. Returns null if no point is connected to the given point.
	 * @param point point which needs connection
	 * @return the connected point or null
	 */
	@Override
	public Point getConnectedPoint(Point point) 
	{
		if(point == endpoint1)
			return endpoint2;
		else
			return endpoint1;
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
		return 2;
	}

}
