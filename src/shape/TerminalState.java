package shape;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;

public class TerminalState extends Circle {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void draw(Graphics2D ga) {
		
		super.draw(ga);
		int radius = getRadius() + 5;
		Point2D point = this.getPosition();
		ga.drawOval((int)point.getX() - radius, (int)point.getY() - radius ,radius*2, radius*2);
	}
}
