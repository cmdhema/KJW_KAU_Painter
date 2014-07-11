package MakeShape;

import java.awt.*;

import Control.MouseAction;
import HandleShape.HandleLine;

public class MakePen extends MouseAction 
{
	private int x1, y1;	 // Starting Point
	private Point LastP;
	
	public void mousePressExe(Point p, Making Layer)
	{
		x1 = p.x;
		y1 = p.y;

		HandleLine pen = new HandleLine(x1, y1, x1, y1, Layer.getColor());  // Create new object.
		Layer.add(pen);	// Save the shape.
		LastP=p;
	}

	public void mouseDragExe(Point p, Making Layer)
	{
		HandleLine pen = new HandleLine(LastP.x, LastP.y, p.x, p.y, Layer.getColor());
		LastP = p;
		Layer.add(pen);
	}

}