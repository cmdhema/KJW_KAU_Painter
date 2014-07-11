package MakeShape;

import java.awt.*;

import Control.MouseAction;
import HandleShape.HandleArrow;

public class MakeArrow extends MouseAction 
{	
	private Point start;	// Starting point
  	
  	public void mousePressExe(Point p, Making Layer)
  	{
    		start = p;	//Set start point
    		HandleArrow arr = new HandleArrow(p.x, p.y, 0, 0, Layer.getColor());	// Create Shape object
    		Layer.add(arr);	// Save the Shape
  	}
 
  
  	public void mouseDragExe(Point p, Making Layer)	// When the mouse is dragged.
  	{
    		HandleArrow arr = new HandleArrow(Math.min(p.x, start.x), Math.min(p.y, start.y), 
    									  Math.abs(p.x - start.x), Math.abs(p.y - start.y), Layer.getColor());
    		Layer.remainEndshape(arr);	// Show the shape base on last point
  	}
  	
  	
   	public void mouseDragShiftExe(Point p, Making Layer)	// When the mouse is dragged with Shift key.
   	{
     		HandleArrow arr = new HandleArrow(Math.min(p.x, start.x), Math.min(p.y, start.y),
     									  Math.abs(p.x - start.x), Math.abs(p.x - start.x), Layer.getColor());
     		Layer.remainEndshape(arr);	// Show the shape base on last point	
   	}
  	
}
