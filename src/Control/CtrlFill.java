package Control;

import java.awt.*;

import HandleShape.Handling;
import MakeShape.Making;

public class CtrlFill extends MouseAction 
{
  	// Change the color of the shape.
	public void mouseClickExe(Point p, Making Layer)
  	{
    	Handling s = Layer.getTopShape(p);
		if(s != null)								
		{
			if(s.containsPoint(p))
    		{
				s.setColor(Layer.getColor());    
				s.FillPaint(p); 
    			}
		}      		
    }
}
