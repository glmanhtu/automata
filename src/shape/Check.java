package shape;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class Check extends BaseShape {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Point2D point;
	
	@Override
	public void draw(Graphics2D ga) {
		// TODO Auto-generated method stub
		Color current = ga.getColor();
		ga.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		ga.setColor(getContrastColor(current));
		Stroke stroke = ga.getStroke();
		ga.setStroke(new BasicStroke(3));
		Line2D line = new Line2D.Double(point.getX() - 8, point.getY() - 8, point.getX(), point.getY());
		ga.draw(line);
		line = new Line2D.Double(point.getX() + 8, point.getY() - 18, point.getX(), point.getY());
		ga.draw(line);
		ga.setStroke(stroke);
		ga.setColor(current);
	}

	@Override
	public void setPosition(Point2D point) {
		// TODO Auto-generated method stub
		this.point = point;
	}

	@Override
	public Point2D getPosition() {
		// TODO Auto-generated method stub
		return point;
	}

	@Override
	public boolean verifyShapeExists(Point2D point) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean compare(Shape shape) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setLabel(String label) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setAsIcon() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return null;
	}
}
