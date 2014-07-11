package Control;
import java.awt.*;

import HandleShape.Handling;
import MakeShape.Making;

public class CtrlMove extends MouseAction {
	
	private Point lastPoint;	
    private Handling s;			
 
  	public void mousePressExe(Point p, Making Layer)
  	{
	
		s = Layer.getTopShape(p);
    		if (s != null){		
			
      		lastPoint = p;		
      			
    		}
  	}
  
  	public void mouseDragExe(Point p, Making Layer)
  	{
    		if (s != null&&Layer.getEditIndex()==Layer.getCurrentIndex(s)) 
    		{															
		      		s.move(p.x - lastPoint.x, p.y - lastPoint.y); 	
		      		lastPoint = p;
    		}
			
  	}
	
}
