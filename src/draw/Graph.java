package draw;

import java.awt.Color;
import java.awt.Component;
import java.util.Vector;

import automata.Automata;
import shape.Shape;

public interface Graph {
	Component getComponent();
	void addComponent(Component comp);
	void setPaintType(int type);
	void setShapeColor(Color color);
	void setAutomata(Automata automata);
	void setAutomataChanges();
	void recognizeAutomata(String word);
	void requestFocus();
	void setShapes(Vector<Shape> shapes);
	Vector<Shape> getShapes();
}
