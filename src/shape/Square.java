package shape;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Square extends BaseShape {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Point2D point;
	
	private Rectangle2D.Double rectange;
	
	private double width;
	private double height;
	private String label = "";
	private Color currentColor;
	
	public Square()
	{
		this.setHeight(30);
		this.setWidth(30);
	}
	
	protected void setWidth(double width) {
		this.width = width;
	}
	
	protected void setHeight(double height) {
		this.height = height;
	}
	
	@Override
	public void draw(Graphics2D ga) {
		// TODO Auto-generated method stub
		Color current = ga.getColor();
		if (currentColor == null) {
			currentColor = current;
		}
		ga.setColor(currentColor);
		rectange = new Rectangle2D.Double(point.getX() - width, point.getY() - height, width*2, height*2);
		ga.fill(rectange);
		ga.setColor(getContrastColor(currentColor));
		ga.drawString(label, (int)(point.getX() -3) ,(int) (point.getY() + 5));
		ga.setColor(current);
	}

	@Override
	public void setPosition(Point2D point) {
		// TODO Auto-generated method stub
		this.point = point;
	}

	@Override
	public boolean verifyShapeExists(Point2D point) {
		// 	
		return rectange.contains(point);
	}

	@Override
	public Point2D getPosition() {
		// TODO Auto-generated method stub
		return this.point;
	}

	@Override
	public boolean compare(Shape shape) {
		if (shape instanceof Square) {
			if (this.getPosition().equals(shape.getPosition())) {
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
		this.setHeight(10);
		this.setWidth(10);
		this.label = "";
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return label;
	}
}
