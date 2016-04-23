package shape;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.event.MouseInputListener;

import draw.GraphWithEditor;

public class EdgeLabel extends JLabel implements Shape, MouseInputListener, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Edge edge;
	private double phi = Math.toRadians(10);
	private int barb = 12;
	private double x, y;
	private String label = "e";
	
	public EdgeLabel() {
		// TODO Auto-generated constructor stub
		super("Edge");
		setForeground(Color.BLUE);
		addMouseListener(this);
	}
	
	@Override
	public void draw(Graphics2D ga) {
		// TODO Auto-generated method stub
		setText(label);
		Line2D line = edge.getLine();
		if (line == null) return;
		double midX = (line.getP2().getX() + line.getP1().getX())/2;
		double midY = (line.getP2().getY() + line.getP1().getY())/2;
		double dx = midX - line.getP1().getX();
		double dy = midY - line.getP1().getY();
		double theta = Math.atan2(dy, dx);
		double rho = theta + phi;
		Color current = ga.getColor();
    	ga.setColor(Color.BLUE);
    	x = midX - barb * Math.cos(rho);
        y = midY - barb * Math.sin(rho);
        
        setBounds((int)x, (int)y, 31, 16);
    	ga.setColor(current);
	}
	

	@Override
	public void setPosition(Point2D point) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Point2D getPosition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean verifyShapeExists(Point2D point) {
		// TODO Auto-generated method stub
		Rectangle2D.Double rec = new Rectangle2D.Double(x, y, 61, 16);
		if( rec.contains((int)point.getX(),(int) point.getY())) {
			return true;
		}
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
		this.label = label;
	}
	public void setEdge(Edge edge) {
		this.edge = edge;
	}
	
	public Edge getEdge() {
		return edge;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getButton() == MouseEvent.BUTTON1) {
			String nlabel = JOptionPane.showInputDialog(this,
	                "Set your edge label: ", null);
			if (nlabel != null && nlabel.length() > 0) {
				label = nlabel;
				changeRelatedLabel(label);
				validate();
				repaint();
				((GraphWithEditor)this.getParent()).setAutomataChanges();
			}
		}
	}
	
	private void changeRelatedLabel(String label) {
		Edge currentEdge = edge;
		while (currentEdge.getSource() instanceof JoinPoint) {
			JoinPoint jp = (JoinPoint) currentEdge.getSource();
			currentEdge = (Edge) jp.getSource();
			currentEdge.getEdgeLabel().setLabel(label);
			currentEdge.getEdgeLabel().revalidate();
			currentEdge.getEdgeLabel().repaint();
		}
		currentEdge = edge;
		while (currentEdge.getDest() instanceof JoinPoint) {
			JoinPoint jp = (JoinPoint) currentEdge.getDest();
			currentEdge = (Edge) jp.getDest();
			currentEdge.getEdgeLabel().setLabel(label);
			currentEdge.getEdgeLabel().revalidate();
			currentEdge.getEdgeLabel().repaint();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setAsIcon() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return label;
	}

	@Override
	public boolean isDrawing() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setDrawing(boolean draw) {
		// TODO Auto-generated method stub
		
	}
}
