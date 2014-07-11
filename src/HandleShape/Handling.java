package HandleShape;

import java.awt.*;
import java.io.Serializable;


public abstract class Handling implements Cloneable, Serializable
{

	private static final long serialVersionUID = 1L;
	private Color color;        // Color of the shape
    private boolean Selected; // Selected?

  	// Constructor, set the color.
  	public Handling(Color initColor)  { color = initColor; }
  	
    // Draw the Shape.
  	public abstract void draw(Graphics g);           
    
  	// If the point p is in the shape return true, else false.  
 	public abstract boolean containsPoint(Point p); 
  	
  	// Move the shape.
  	public abstract void move(int addX, int addY);
  	
  	// Resize the shape.
	public abstract void resize(int addX, int addY);

	// User wants to fill the shape?
	public abstract boolean FillPaint(Point p);
	
   	// Get the color.
   	public Color getColor()  { return color; }
   	
  	// Set the color.
  	public void setColor(Color newColor)  { color = newColor; }
   
  	// Whether the shape is selected. 
	public boolean getSelect()  { return Selected; }
    
	// Set the Selection.
	public void setSelect(boolean b)  { Selected = b; }
	
}

  