package HandleShape;

import java.awt.*;
import java.awt.geom.*;
import java.io.Serializable;


public class HandleLine extends Handling implements Serializable
{	
	private static final long serialVersionUID = 1L;
	private int x1, y1, x2, y2;	// This line will start from (x1,y1) to (x2,y2).
	private Line2D.Double line;
	

	// Line Constructor
	public HandleLine(int x1, int y1, int x2, int y2, Color color)
	{
		super(color);	// Call mother class's constructor
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}

	// This method will draw line.
	public void draw(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;	// Convert to Graphics2D.
		line = new Line2D.Double(x1, y1, x2, y2);	// Make a new line object.
		g2d.setColor(getColor());			// Set color
		g2d.draw(line);						// Draw line


		if (super.getSelect() == true) // When the shape is selected, 
		{							   // It will make 4 little black squares around corners.				
			g.setColor(Color.BLACK);
			g.fillRect(x1, y1, 5,5);	// Left,Top  Corner
			g.fillRect(x1, y2, 5,5);	// Left,Bottom  Corner
			g.fillRect(x2, y2, 5,5);	// Right,Top  Corner		
			g.fillRect(x2, y1, 5,5);	// Right,Bottom  Corner
		} // End of if

	}

	
	// Check if the Point p is in the shape.
	public boolean containsPoint(Point p)
	{
		int left   = Math.min(x1, x2);
		int top    = Math.min(y1, y2);
		int width  = Math.abs(x1 - x2);
		int height = Math.abs(y1 - y2);

		if (p.x >= left && p.x <= left + width && p.y >= top && p.y <= top + height)
			return true;		
		else
			return false;		
	}

	// Move and Resize the line based on add
	public void move(int addX, int addY)
	{
		x1 = x1 + addX;
		y1 = y1 + addY;
		x2 = x2 + addX;
		y2 = y2 + addY;
	}
	public void resize(int addX, int addY)
	{
		x2 = x2 + addX;
		y2 = y2 + addY; 
	}
	
	// Unnecessary method.
	public boolean FillPaint(Point p) {return true;}
	
}

