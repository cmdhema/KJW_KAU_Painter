package Control;
import java.awt.*;

import HandleShape.Handling;
import MakeShape.Making;

public class CtrlSelect extends MouseAction {
			
    private Handling s;			
 
  	public void mouseClickExe(Point p, Making Layer)
  	{
		s = Layer.getTopShape(p);
		if(Layer.isEditing()==true){ 
			((Handling) Layer.getDrawing().get(Layer.getEditIndex())).setSelect(false); 
			}
		if (s != null&&Layer.isEditing()==false){	 
			s.setSelect(true);
			}
  	}
}
