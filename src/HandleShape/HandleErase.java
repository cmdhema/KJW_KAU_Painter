package HandleShape;

import java.awt.*;


public class HandleErase extends Handling 
{	
	private static final long serialVersionUID = 1L;
	private int x1, y1, x2, y2;
		
  	// Erase constructor
	public HandleErase(int x1, int y1, int x2, int y2, Color color)
  	{
  		super(color);
    	this.x1 = x1;
    	this.y1 = y1;
    	this.x2 = x2;
    	this.y2 = y2;
  	}
	  
	// Will draw filled oval with background color.
  	public void draw(Graphics g)
  	{
  		g.setColor(Color.WHITE);
		g.fillOval(this.x1,this.y1,15,15);
		g.fillOval(this.x2,this.y2,15,15);
	}


 	// Since this class extends shape, it should have these methods.
  	// Don't have to perform any action.
	public boolean FillPaint(Point p) {return true;}
	public boolean containsPoint(Point p) {return false;}
  	public void move(int addX, int addY){}
	public void resize(int addX, int addY){}

}

