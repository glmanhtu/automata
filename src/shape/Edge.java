package shape;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class Edge extends JoinPointBase {
	
	private Point2D point;
	private Line2D line;
	private EdgeLabel render;
	private Color color = Color.black;
	
	@Override
	public void draw(Graphics2D ga) {
		// TODO Auto-generated method stub
		Point2D sourcePoint = getSource().getPosition();
		Point2D destPoint = null;
		Color current = ga.getColor();
		ga.setColor(color);
		if (getDest() != null) {
			destPoint = getDest().getPosition();
		}
		if (sourcePoint != null && destPoint != null) {
			line = new Line2D.Float(sourcePoint, destPoint);
			drawArrow(ga, line);
		} else if (sourcePoint != null && point != null) {
			line = new Line2D.Float(sourcePoint, point);
			drawArrow(ga, line);
		}
		ga.setColor(current);
	}
	
	public void setColor(Color color) {
		this.color = color;
	}

	public static void drawArrow(Graphics2D ga, Line2D line)
	{	
		double phi = Math.toRadians(40);
		int barb = 12;
		double midX = (line.getP2().getX() + line.getP1().getX())/2;
		double midY = (line.getP2().getY() + line.getP1().getY())/2;
		
		double quarX = (midX + line.getP1().getX())/2;
		double quarY = (midY + line.getP1().getY())/2;
		
		double d2x = quarX - line.getP1().getX();
		double d2y = quarY - line.getP1().getY(); 
		
		double theta2 = Math.atan2(d2y, d2x);
		double x, y, rho = theta2 + phi;
        for(int j = 0; j < 2; j++)
        {
            x = quarX - barb * Math.cos(rho);
            y = quarY - barb * Math.sin(rho);
            ga.draw(new Line2D.Double(quarX, quarY, x, y));
            rho = theta2 - phi;
        }
        
		ga.draw(line);
	}
	
	public Line2D getLine()
	{
		return line;
	}

	@Override
	public void setPosition(Point2D point) {

	}

	@Override
	public boolean verifyShapeExists(Point2D point) {
		// TODO Auto-generated method stub
		if (line.contains(point)) {
			return true;
		}
		return false;
	}

	@Override
	public Point2D getPosition() {
		return null;
	}
	
	public void setDest(Point2D dest) {
		this.point = dest;
	}
	
	@Override
	public boolean compare(Shape shape) {
		// TODO Auto-generated method stub
		if (shape instanceof Edge) {
			if (((Edge) shape).getSource().compare(this.getSource())) {
				if (((Edge) shape).getDest().compare(this.getDest())) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void setLabel(String label) {
	}
	
	public void setEdgeLabel(EdgeLabel label) {
		render = label;
	}
	
	public EdgeLabel getEdgeLabel() {
		return render;
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
