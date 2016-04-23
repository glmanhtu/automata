package shape;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class Edge extends JoinPointBase {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Point2D point;
	private Line2D line;
	private EdgeLabel render;
	private Color color = Color.black;
	private double distance, theta;
	
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
		
		if (getSource().equals(getDest()) || getSource().verifyShapeExists(point)) {
			if (isDrawing()) {
				distance = Math.sqrt(Math.pow((point.getX() - sourcePoint.getX()), 2) + Math.pow((point.getY() - sourcePoint.getY()), 2));
				theta = findTheta(new Line2D.Float(sourcePoint, point));
			}
			double phi = Math.toRadians(40);
            double rho = theta + phi;
        	Double x = sourcePoint.getX() - distance*4* Math.cos(rho);
	        Double y = sourcePoint.getY() - distance*4* Math.sin(rho);
	        line = new Line2D.Float(sourcePoint, new Point2D.Double(x,y));
	        drawArrow(ga, line);
            rho = theta - phi;
            
            Double x2 = sourcePoint.getX() - distance*4* Math.cos(rho);
	        Double y2 = sourcePoint.getY() - distance*4* Math.sin(rho);
	        line = new Line2D.Float(new Point2D.Double(x2,y2), sourcePoint);
	        drawArrow(ga, line);
            ga.draw(new Line2D.Float(sourcePoint, new Point2D.Double(x2,y2)));
            
            Double quar_x2 = ((x+x2)/2 +x2)/2;
            Double quar_y2 = ((y+y2)/2 + y2)/ 2;
           
            phi = Math.toRadians(0);
            quar_x2 = quar_x2 - distance* Math.cos(theta + phi);
	        quar_y2 = quar_y2 - distance* Math.sin(theta + phi);
            
            Double quar_x = ((x + x2)/2 + x)/2;
            Double quar_y = ((y + y2)/2 + y)/2;
            
            quar_x = quar_x - distance* Math.cos(theta + phi);
	        quar_y = quar_y - distance* Math.sin(theta + phi);
            
            ga.draw( new Line2D.Float( new Point2D.Double(x,y), new Point2D.Double(quar_x,quar_y)));
            ga.draw( new Line2D.Float( new Point2D.Double(x2, y2), new Point2D.Double(quar_x2,quar_y2)));
            line = new Line2D.Float( new Point2D.Double(quar_x, quar_y), new Point2D.Double(quar_x2,quar_y2));
            drawArrow(ga, line);
			
		} else if (sourcePoint != null && destPoint != null) {
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
		double theta2 = findTheta(line);
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
	
	public static double findTheta(Line2D line) {
		double midX = (line.getP2().getX() + line.getP1().getX())/2;
		double midY = (line.getP2().getY() + line.getP1().getY())/2;
		
		double d2x = midX - line.getP1().getX();
		double d2y = midY - line.getP1().getY(); 
		
		return Math.atan2(d2y, d2x);
		
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
