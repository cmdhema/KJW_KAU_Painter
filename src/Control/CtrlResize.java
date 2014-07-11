package Control;

import java.awt.*;

import HandleShape.Handling;
import MakeShape.Making;

public class CtrlResize extends MouseAction {
	
	private int x1, y1; 
	private Handling temp; 

	
	public void mousePressExe(Point p, Making Layer)
	{
		Handling s = Layer.getTopShape(p);
		temp = s; 
		x1 = p.x;
		y1 = p.y;
	}
	public void mouseDragExe(Point p, Making Layer)
	{	
		if (temp != null&&Layer.getEditIndex()==Layer.getCurrentIndex(temp)) 
		{																
			temp.resize(p.x - x1, p.y - y1);		
			x1 = p.x;
			y1 = p.y;
		}      		
	}
}
