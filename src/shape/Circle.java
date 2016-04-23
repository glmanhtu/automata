package shape;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

public class Circle extends BaseShape {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Point2D point;
	private int radius = 25;
	private String label = "";
	protected Color currentColor;
	
	@Override
	public void draw(Graphics2D ga) {
		Color current = ga.getColor();
		if (currentColor == null) {
			currentColor = current;
		}
		ga.setColor(currentColor);
	    ga.fillOval((int)point.getX() - radius, (int)point.getY() - radius ,radius*2, radius*2);
	    ga.setColor(getContrastColor(currentColor));
	    int width = ga.getFontMetrics().stringWidth(label);
	    ga.drawString(label, (int)(point.getX() - width/2) ,(int) (point.getY() + 5));
	    ga.setColor(current);
	}
	
	protected void setRadius(int radius) {
		this.radius = radius;
	}
	
	protected int getRadius() {
		return this.radius;
	}
	
	@Override
	public boolean verifyShapeExists(Point2D point) {
		if (point == null) return false;
		if (this.point.getX() + radius > point.getX() && this.point.getX() - radius < point.getX()) {
			if (this.point.getY() + radius > point.getY() && this.point.getY() - radius < point.getY()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void setPosition(Point2D point) {
		// TODO Auto-generated method stub
		this.point = point;
	}

	@Override
	public Point2D getPosition() {
		// TODO Auto-generated method stub
		return this.point;
	}

	@Override
	public boolean compare(Shape shape) {
		// TODO Auto-generated method stub
		if (shape instanceof Circle) {
			if (this.point.equals(shape.getPosition())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void setLabel(String label) {
		// TODO Auto-generated method stub
		this.label = label;
	}

	@Override
	public void setAsIcon() {
		// TODO Auto-generated method stub
		this.radius = 10;
		this.label = "";
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return label;
	}
}
