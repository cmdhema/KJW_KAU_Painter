package HandleShape;

import java.awt.*;
import java.awt.geom.*;
import java.io.*;


public class HandleOval extends Handling implements Serializable 
{
	private static final long serialVersionUID = 1L;
	private int left, top;          	// start point(left,top).
	private int width, height;      	// width and height.
	private Ellipse2D.Double ellipse;	// Declare the shape that we are going to draw.
	private boolean FillPaint;		// Boolean for Fillpaint.


	// Constructor of this class.
	public HandleOval(int left, int top, int width, int height, Color color)
	{
		super(color);		// Call mother class's constructor.
		this.left = left;
		this.top = top;
		this.width = width;
		this.height = height;
	} // End of Constructor.


	// This method will draw the shape.////////////////////////////////////////////////////////////////
	public void draw(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;	//Convert to Graphics2D
		g2d.setColor(getColor());			// Set Color

		ellipse = new Ellipse2D.Double(left, top, width, height);	// Make a new Ellipse object.
		g2d.draw(ellipse);		// Draw Ellipse


		if (FillPaint == true)	// When the fill order comes in
			g2d.fill(ellipse);			// Fill the Ellipse


		if (super.getSelect() == true) // When the shape is selected, 
		{							   // It will make 4 little black squares around corners.				
			g.setColor(Color.BLACK);
			g.fillRect(left-5,	   top-5,	   5,5 );	// Left,Top  Corner
			g.fillRect(left-5,	   top+height, 5,5 );	// Left,Bottom  Corner
			g.fillRect(left+width, top-5,	   5,5 );	// Right,Top  Corner		
			g.fillRect(left+width, top+height, 5,5 );	// Right,Bottom  Corner
		} // End of if

	} // End draw Class/////////////////////////////////////////////////////////////////////////////////////



	//	This boolean type method will return true if the shape is selected
	public boolean FillPaint(Point p) // Type boolean should return whether the result if true or not.
	{
		if (p.x >= left && p.x <= left + width && p.y >= top && p.y <= top + height)
			return this.FillPaint = true;
		else
			return this.FillPaint = false;
	}


	// Check if the selected point is in the square or not.
	public boolean containsPoint(Point p)
	{
		if (p.x >= left && p.x <= left + width && p.y >= top && p.y <= top + height)
			return true;
		else
			return false;
	}


	// Move and Resize the Shape method. ///////////////////////////////////////////////////////
	public void move(int addX, int addY) 
	{
		left = left + addX;	 // Move X point to (X+addX)
		top  = top  + addY;	 // Move Y point to (Y+addY)
	}
	public void resize(int addX, int addY) 
	{
		width  = width  + addX;	// Change X side to (X+addX)
		height = height + addY; 	// Change Y side to (Y+addY)
	}
	////////////////////////////////////////////////////////////////////////////////////////////

}