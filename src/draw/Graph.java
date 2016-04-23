package draw;

import java.awt.Color;
import java.awt.Component;
import java.util.Vector;

import automata.Automata;
import shape.Shape;

public interface Graph {
	Component getComponent();
	void addComponent(Component comp);
	
	/**
	 * Set Type of Shape to be painted
	 * @param type one of type in ShapeFactory
	 */
	void setPaintType(int type);
	
	/**
	 * Set color for any shape will be paint
	 * @param color
	 */
	void setShapeColor(Color color);
	
	/**
	 * Assign automata algorithm to Graph
	 * This allow Graph can call automata to check if Automata is correct
	 * Or verify how Automata recognize word
	 * @param automata
	 */
	void setAutomata(Automata automata);
	
	/**
	 * This will notify Automata that we have change in Graph
	 * Such as add shape, add edge, change edge label, etc
	 * For re-check Automata is correct or not
	 */
	void setAutomataChanges();
	
	/**
	 * This will ask Automata if it can recognize a given String word or not
	 * @param word
	 */
	void recognizeAutomata(String word);
	
	/**
	 * When you change Shape Type or Shape Color, a focus in Shape Editor is lost
	 * So, if user hold ALT key, Shape Editor can't recognize it
	 * Therefore, whenever we change focus to any panel except Shape Editor
	 * We need to request focus again.
	 */
	void requestFocus();
	
	/**
	 * Set Shapes to Graph Editor
	 * This useful when we import file 
	 * @param shapes
	 */
	void setShapes(Vector<Shape> shapes);
	
	/**
	 * Get all Shapes which we painted. 
	 * This useful when we export Graphs into file
	 */
	Vector<Shape> getShapes();
}
