package MakeShape;

import java.util.*;
import java.awt.*;
import java.io.*;

import HandleShape.Handling;

public class Making implements Serializable 
{
	private static final long serialVersionUID = 1L;
	private Vector<Handling> saveSpace; // Vector to save shape
	private Vector<Handling> temp1, temp2;
	private Color currentColor;			 // Current color
	private Handling s;

	// Constructor for this class.
	public Making(Color color) 
	{
		saveSpace = new Vector<Handling>(); // Create new Vector.
		currentColor = color;			 // Set current color.
	}

	// Get shape base on index.
	public Handling getShape(int index) { return saveSpace.get(index); }

	// Delete selected shape.
	public void delete() { saveSpace.remove(getEditIndex()); }

	// Save the shape to vector.
	public void add(Handling shape) { saveSpace.add(shape); }

	// Get the index of saved space and remove the shape from vector.
	public void remove(Handling shape) {saveSpace.remove(saveSpace.indexOf(shape)); }

	// Empty the vector.
	public void newShape() {saveSpace.removeAllElements(); }

	// Delete the last element from vector.
	public void undo() 
	{
		if(saveSpace.size() > 0)
		{
			s = saveSpace.lastElement();
			saveSpace.remove(saveSpace.lastElement());
		}
	}
	
	
	public void reundo()
	{
		if( s != null)
			saveSpace.add(s);
	}

	// Set,Get method for color.
	public void  setColor(Color color) { currentColor = color; }
	public Color getColor() { return currentColor; }

	// Get,Set method for drawing
	public Vector<Handling> getDrawing() 					{ return saveSpace; }
	public void  setDrawing(Vector<Handling> save) { saveSpace = save; }

	
	
	
	
	
	
/*
	public void nextPage()
	{
		for(int i=1; i < saveSpace.size(); i++)
		{
			temp1.add(saveSpace.elementAt(i));
		}
		saveSpace.removeAllElements();
		for(int i=1; i<temp2.size(); i++)
		{
			saveSpace.add(temp2.elementAt(i));
		}
	}

	public void prePage()
	{
		for(int i=1; i < saveSpace.size(); i++)
		{
			temp2.add(saveSpace.elementAt(i));
		}
		saveSpace.removeAllElements();
		for(int i=1; i < temp1.size(); i++)
		{
			saveSpace.add(temp1.elementAt(i));
		}
	}
*/
	
	
	public void nextPage()
	{
		temp1 = saveSpace;
		saveSpace.removeAllElements();
		saveSpace = temp2;
	}

	public void prePage()
	{
		temp2 = saveSpace;
		saveSpace.removeAllElements();
		saveSpace = temp1;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	// Move the selected shape to back.
	public void moveToBack(Handling shape)
	{
		saveSpace.remove(saveSpace.indexOf(shape));
		saveSpace.add(0, shape);
	}

	
	// Only remain the last shape.
	public void remainEndshape(Handling shape) 
	{
		saveSpace.remove(saveSpace.lastElement());
		saveSpace.add(shape);
	}


	// Draw all the shape in the vector.
	public void draw(Graphics g) 
	{
		for(int i=0; i<saveSpace.size(); i++)
			(saveSpace.get(i)).draw(g);
	}

	
	// Get the index of selected shape.
	public int getEditIndex()
	{
		for( int i=0; i<=saveSpace.size() - 1; i++)
			if ((saveSpace.get(i)).getSelect()==true) 
				return i;
		return 0;
	}


	// Get the index of current shape.
	public int getCurrentIndex(Handling shape)
	{
		for( int i=0; i<=saveSpace.size() - 1; i++)
			if(i==saveSpace.indexOf(shape))
				return i;
		return 0;
	}	
	
	
	// Return whether the shape is selected or not.
	public boolean isEditing()
	{
		if(saveSpace.isEmpty()==true || (saveSpace.get(getEditIndex())).getSelect()==false )
			return false;
		return true;
	}


	// Search the vector through latest index and return the shape that mouse is currently positioning.
	public Handling getTopShape(Point p) 
	{
		for (int index=saveSpace.size() - 1; index >= 0; index--)  // Reverse for loop.
			if ((saveSpace.get(index)).containsPoint(p)) 
				return saveSpace.get(index);  // Return the shape.
		return null;  // Return null if no match.
	}

}

