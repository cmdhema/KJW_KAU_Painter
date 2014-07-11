package MakeShape;

import java.awt.*;

import Control.MouseAction;
import HandleShape.HandleErase;

public class MakeErase extends MouseAction 
{
	private int x1, y1;	 // Starting Point
	private Point LastP;


	public void mousePressExe(Point p, Making Layer)
	{
		x1 = p.x;
		y1 = p.y;

		HandleErase eraser = new HandleErase(x1, y1, x1, y1, Color.WHITE);  // Create new object.
		Layer.add(eraser);	// Save the shape.
		LastP=p;
	}

	public void mouseDragExe(Point p, Making Layer)
	{
		HandleErase eraser = new HandleErase(LastP.x, LastP.y, x1, y1, Color.WHITE);
		LastP = p;
		Layer.add(eraser);
	}

}