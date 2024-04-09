package hw4;

import api.Crossable;
import api.Point;
import api.PositionVector;
/** @author Aden Koziol */
public abstract class AbstractLink extends java.lang.Object implements Crossable 
{
	public AbstractLink()
	{
		
	}
	
	/**
	 * Shift the location of the given positionVector to be between the next pair of points.
	 * @param positionVector given positionVector
	 */
	public void shiftPoints(PositionVector positionVector) 
	{
		positionVector.setPointA(getConnectedPoint(positionVector.getPointB()));
		if(positionVector.getPointA().getPath().getHighpoint() == positionVector.getPointA())
			positionVector.setPointB(positionVector.getPointA().getPath().getPointByIndex(positionVector.getPointA().getPointIndex() - 1));
		if(positionVector.getPointA().getPath().getLowpoint() == positionVector.getPointA())
			positionVector.setPointB(positionVector.getPointA().getPath().getPointByIndex(positionVector.getPointA().getPointIndex() + 1));
	}
}
