package MakeShape;

import java.awt.*;

import Control.MouseAction;
import HandleShape.HandleTri;

public class MakeTri extends MouseAction
{	
	private Point start;	// Starting point
	
	public void mousePressExe(Point p, Making Layer)
	{
		start = p;	//Set start point
		HandleTri tri = new HandleTri(p.x, p.y, 0, 0, Layer.getColor());	// Create Shape object
		Layer.add(tri);	// Save the Shape
	}
	
	public void mouseDragExe(Point p, Making Layer)	// When the mouse is dragged.
	{
		HandleTri tri = new HandleTri(Math.min(p.x, start.x), Math.min(p.y, start.y), 
									  Math.abs(p.x - start.x), Math.abs(p.y - start.y), Layer.getColor());
		Layer.remainEndshape(tri);	// Show the shape base on last point
	}
	
	
	public void mouseDragShiftExe(Point p, Making Layer)	// When the mouse is dragged with Shift key.
	{
		HandleTri tri = new HandleTri(Math.min(p.x, start.x), Math.min(p.y, start.y),
									  Math.abs(p.x - start.x), Math.abs(p.x - start.x), Layer.getColor());
		Layer.remainEndshape(tri);	// Show the shape base on last point	
	}
}
