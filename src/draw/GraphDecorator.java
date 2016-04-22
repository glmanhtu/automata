package draw;

import java.awt.Component;
import java.util.Vector;

import shape.Shape;

public abstract class GraphDecorator implements Graph {
	
	protected Graph decorateComponent;
	
	public GraphDecorator(Graph decorateComponent) {
		this.decorateComponent = decorateComponent;
	}
	
	abstract public Component getComponent();
	
	public void setPaintType(int type) {
		this.decorateComponent.setPaintType(type);
	}
	
	public void recognizeAutomata(String word) {
		this.decorateComponent.recognizeAutomata(word);
	}
	
	public void requestFocus()
	{
		decorateComponent.requestFocus();
	}
	
	public Vector<Shape> getShapes()
	{
		return decorateComponent.getShapes();
	}
	
	public void setShapes(Vector<Shape> shapes)
	{
		decorateComponent.setShapes(shapes);
	}
}
