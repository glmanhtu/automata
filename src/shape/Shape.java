package shape;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;

public interface Shape {
	void draw(Graphics2D ga);
	void setPosition(Point2D point);
	Point2D getPosition();
	boolean verifyShapeExists(Point2D point);
	boolean compare(Shape shape);
	void setLabel(String label);
	String getLabel();
	void setAsIcon();
	String toString();
}
