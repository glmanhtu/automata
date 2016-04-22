package shape;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;

public class JoinPoint extends JoinPointBase {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JoinPoint()
	{
		delegate = new Square();
		delegate.setWidth(8);
		delegate.setHeight(8);
	}

	@Override
	public void draw(Graphics2D ga) {
		// TODO Auto-generated method stub
		delegate.draw(ga);
	}

	@Override
	public void setPosition(Point2D point) {
		// TODO Auto-generated method stub
		delegate.setPosition(point);
	}

	@Override
	public Point2D getPosition() {
		// TODO Auto-generated method stub
		return delegate.getPosition();
	}

	@Override
	public boolean verifyShapeExists(Point2D point) {
		// TODO Auto-generated method stub
		return delegate.verifyShapeExists(point);
	}

	@Override
	public boolean compare(Shape shape) {
		// TODO Auto-generated method stub
		if (shape instanceof JoinPoint) {
			return delegate.compare(((JoinPoint) shape).getDelegate());
		}
		return false;
	}

	@Override
	public void setLabel(String label) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return null;
	}
}
