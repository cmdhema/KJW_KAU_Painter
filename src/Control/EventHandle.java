package Control;
import java.awt.event.*;

import MakeShape.MakeArrow;
import MakeShape.MakeErase;
import MakeShape.MakeLine;
import MakeShape.MakeOval;
import MakeShape.MakePen;
import MakeShape.MakeRect;
import MakeShape.MakeStar;
import MakeShape.MakeTri;

public class EventHandle implements ActionListener 
{
	public MouseAction ordering; 
  
	public void actionPerformed(ActionEvent event)
	{
		int i = Integer.parseInt(event.getActionCommand()); 
		switch(i) 
		{		
		case 11 :
			ordering = new MakeLine();
			break;
		case 12 :
			ordering = new MakeOval();
			break;
		case 14 :
			ordering = new MakeRect();
			break;
		case 15 :
			ordering = new MakeTri();
			break;
		case 16 :
			ordering = new MakeStar();
			break;
		case 17 :
			ordering = new MakeErase();
			break;
		case 18 :
			ordering = new MakePen();
			break;
		case 19 :
			ordering = new MakeArrow();
			break;
		case 21 :
			ordering = new CtrlFill();
			break;
		case 22 :
			ordering = new CtrlResize();
			break;
		case 23 :
			ordering = new CtrlMove();
		    break;
		case 25 :
			ordering = new CtrlSelect();
			break;
		default: break;
		}  		
	}		
}
