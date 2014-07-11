package MakeShape;

import java.awt.*;

import Control.MouseAction;
import HandleShape.HandleLine;

public class MakeLine extends MouseAction 
{	
  	private int x1, y1;  // Starting Point  	
  	 	
  	public void mousePressExe(Point p, Making Layer)
  	{
    	x1 = p.x;
	    y1 = p.y;
	
	    HandleLine line = new HandleLine(x1, y1, x1, y1, Layer.getColor());  // Create new object.
	    Layer.add(line);  // Save the line.
  	}
  	
  	public void mouseDragExe(Point p, Making Layer)
  	{
    	HandleLine line = new HandleLine(x1, y1, p.x, p.y, Layer.getColor());
    	Layer.remainEndshape(line);
  	}
  	
}