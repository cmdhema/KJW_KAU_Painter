package HandleShape;

import java.awt.*;
import java.io.*;

public class HandleArrow extends Handling implements Serializable 
{
	private static final long serialVersionUID = 1L;
	private int left, top;          // start point(left,top).
	private int width, height;      // width and height.
	private Polygon arr;			// Declare the shape that we are going to draw.
	private boolean FillPaint;	// Boolean for Fillpaint.


	// Constructor of this class.
	public HandleArrow(int left, int top, int width, int height, Color color)
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

		//X and Y Points for Polygon, generated with left,top,width,height
		int xp[] = new int[] { left, left+width/2, left+width/2, left+width, left+width/2, left+width/2, left, left };		
		int yp[] = new int[] { top+height/3, top+height/3, top, top+height/2, top+height, top+2*height/3, top+2*height/3, top+height/3 };	
		arr = new Polygon( xp, yp, 7);		// Make a new polygon object
		g2d.draw(arr);		// Draw polygon


		if (FillPaint == true)	// When the fill order comes in
			g2d.fill(arr);			// Fill the Polygon


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

