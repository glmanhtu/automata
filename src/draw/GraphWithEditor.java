package draw;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Point2D;
import java.util.Vector;

import javax.swing.JComponent;
import javax.swing.event.MouseInputListener;

import automata.Automata;
import shape.Edge;
import shape.EdgeLabel;
import shape.JoinPoint;
import shape.Shape;
import shape.ShapeFactory;

public class GraphWithEditor extends JComponent implements MouseInputListener, Graph, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Vector<Shape> shapes;
	
	/**
	 * Shape factory to procedure shape
	 */
	private ShapeFactory shapeFactory;
	
	/**
	 * Determine which shape is currently
	 */
	private Shape currentShape;
	
	/**
	 * Determine which shape will be draw
	 * This value must be one of ShapeFactory type number
	 */
	private int shapeType;
	
	/**
	 * Determine if user hold ALT key or not
	 * True: holding ALT key
	 */
	private boolean holdAltKey = false;
	
	/**
	 * Determine which color is currently using 
	 */
	private Color currentColor;
	
	/**
	 * Object to verify automata
	 */
	private Automata automata;
	
	/**
	 * Determine which point is currently mouse in
	 * Change when mouse drag. see function mouseDragged
	 */
	private Point2D currentPoint;
	public GraphWithEditor() {
		this.shapes = new Vector<>();
		shapeFactory = new ShapeFactory();
		addMouseListener(this);
		addMouseMotionListener(this);
		this.currentShape = null;
		setPreferredSize(new Dimension(2000, 2000));
		addKeyListener(this);
	}
	public void addShape(Shape shape) {
		this.shapes.add(shape);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D ga = (Graphics2D)g;
		ga.setColor(currentColor);
		for (Shape shape : shapes) {
			ga.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			shape.draw(ga);
		}
	}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mouseMoved(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		requestFocusInWindow();
		Point2D point = new Point2D.Double(e.getX(), e.getY());
		for (Shape shape: shapes) {
			/**
			 * For check if it have any shape here
			 */
			if (shape.verifyShapeExists(point)){
				/**
				 * If is right mouse is clicked, remove this shape
				 */
				if (e.getButton() == MouseEvent.BUTTON3) {
					if (shape instanceof JoinPoint) {
						removeJoinShape(shape);
					} else {
						removeShape(shape);
					}
					this.validate();
					this.repaint();
					setAutomataChanges();
				}
				/**
				 * Otherwise, this action is move shape
				 */
				else {
					setCurrentShape(shape);
				}
				return;
			}
		}
		/**
		 * If it doesn't have any shape here, create new shape
		 */
		Shape shape = shapeFactory.getShape(shapeType);
		shape.setPosition(point);
		addShape(shape);
		setCurrentShape(shape);
		this.validate();
		this.repaint();
		setAutomataChanges();
	}
	/**
	 * Remove only JointPoint
	 * Reconnect 2 edge between this JoinPoint
	 * @param shapeToRemove
	 */
	private void removeJoinShape(Shape shapeToRemove) {
		
		if (shapeToRemove instanceof JoinPoint) {
			
			/**
			 * source Edge mean edge go to this Shape
			 */
			Edge source = (Edge) ((JoinPoint) shapeToRemove).getSource();
			
			/**
			 * dest Edge mean edge go out this Shape
			 */
			Edge dest = (Edge) ((JoinPoint) shapeToRemove).getDest();
			
			
			shapes.remove(shapeToRemove);
			Edge edge = (Edge) createEdge();
			edge.setSource(source.getSource());
			if (source.getSource() instanceof JoinPoint) {
				((JoinPoint)(source.getSource())).setDest(edge);
			}
			
			edge.setDest(dest.getDest());
			
			if (dest.getDest() instanceof JoinPoint) {
				((JoinPoint)(dest.getDest())).setSource(edge);
			}
			
			addShape(edge);
			if (source != null) {
				removeEdge(source);
			}
			if (dest != null) {
				removeEdge(dest);
			}
			
		}
	}
	
	private void removeShape(Shape shapeToRemove) {
		Vector<Edge> sources = getEdgeSource(shapeToRemove, shapes);
		Vector<Edge> dests = getEdgeDest(shapeToRemove, shapes);
		shapes.remove(shapeToRemove);
		this.recursiveRmSource(sources);
		this.recursiveRmDest(dests);
	}
	
	public static Vector<Edge> getEdgeSource(Shape shapeToFind, Vector<Shape> shapes) {
		Vector<Edge> sources = new Vector<>();
		for (Shape shape : shapes) {
			if (shape instanceof Edge) {
				if ( ((Edge) shape).getDest().compare(shapeToFind)) {
					sources.add((Edge) shape);
				}
			}
		}
		return sources;
	}

	public static Vector<Edge> getEdgeDest(Shape shapeToFind, Vector<Shape> shapes) {
		Vector<Edge> sources = new Vector<>();
		for (Shape shape : shapes) {
			if (shape instanceof Edge) {
				if ( ((Edge) shape).getSource().compare(shapeToFind)) {
					sources.add((Edge) shape);
				}
			}
		}
		return sources;
	}
	
	private void recursiveRmDest(Vector<Edge> edges) {
		for (Edge e: edges) {
			
				Edge currentEdge = e;
				removeEdge(e);
				while (shapes.contains(currentEdge.getDest()) && currentEdge.getDest() instanceof JoinPoint) {
					JoinPoint join = (JoinPoint) currentEdge.getDest();
					shapes.remove(join);
					currentEdge = (Edge) join.getDest();
					removeEdge(currentEdge);
				}
		}
	}
	private void recursiveRmSource(Vector<Edge> edges) {
		for (Edge e: edges) {
			Edge currentEdge = e;
			removeEdge(e);
			
			while (shapes.contains(currentEdge.getSource()) && currentEdge.getSource() instanceof JoinPoint) {
				JoinPoint join = (JoinPoint) currentEdge.getSource();
				shapes.remove(join);
				currentEdge = (Edge) join.getSource();
				removeEdge(currentEdge);
			}
		}
	}
	
	private void setCurrentShape(Shape shape) {
		this.currentShape = shape;
		if (holdAltKey && !(shape instanceof JoinPoint)) {
			Shape edge = createEdge();
			((Edge)edge).setSource(currentShape);
			addShape(edge);
			this.currentShape = edge;
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		/**
		 * When mouse release & we are drawing edge
		 */
		if (this.currentShape instanceof Edge) {
			Point2D point = new Point2D.Double(e.getX(), e.getY());
			for (Shape shape: shapes) {
				if (shape.verifyShapeExists(point)){
					/**
					 * Ignore if current shape is JoinPoint or EdgeLabel
					 */
					if (shape instanceof JoinPoint || shape instanceof EdgeLabel) {
						continue;
					}
					/**
					 * Otherwise, set current edge point to this shape
					 */
					((Edge) currentShape).setDest(shape);
					currentShape = null;
					validate();
					repaint();
					/**
					 * Notify to automata that it have changes
					 */
					setAutomataChanges();
					return;
				}
			}
			/**
			 * If it doesn't have any shape, create a new one
			 */
			Shape shape = shapeFactory.getShape(shapeType);
			shape.setPosition(point);
			addShape(shape);
			((Edge) currentShape).setDest(shape);
			setAutomataChanges();
		}
		this.currentShape = null;
		this.validate();
		this.repaint();
	}



	@Override
	public void mouseDragged(MouseEvent e) {
		Point2D point = new Point2D.Double(e.getX(), e.getY());
		currentPoint = point;
		/**
		 * In case we hold Alt key & create connection edge  
		 */
		if (holdAltKey && !(currentShape instanceof Edge)) {
			setCurrentShape(currentShape);
		}
		/**
		 * Other wise
		 */
		if (this.currentShape != null) {
			/**
			 * If current shape is Edge
			 * We just move this shape to new ppoint
			 */
			if (currentShape instanceof Edge) {
				((Edge) currentShape).setDest(point);
			} else {
				currentShape.setPosition(point);
			}
		}
		this.validate();
		this.repaint();
	}

	@Override
	public Component getComponent() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public void addComponent(Component comp) {
		add(comp);
		revalidate();
		repaint();
	}

	@Override
	public void setPaintType(int type) {
		// TODO Auto-generated method stub
		this.shapeType = type;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.isAltDown()) {
			holdAltKey = true;
		}
		
		/**
		 * If key pressed is space and we are drawing edge
		 * So, create join point here
		 */
		if (e.getKeyCode() == 32 && currentShape instanceof Edge) {
			JoinPoint shape = (JoinPoint) shapeFactory.getShape(ShapeFactory.JOIN_POINT);
			shape.setPosition(currentPoint);
			shape.setSource(currentShape);
			((Edge) currentShape).setDest(shape);
			Shape edge = createEdge();
			((Edge)edge).setSource(shape);
			shape.setDest(edge);
			addShape(shape);
			addShape(edge);
			currentShape = edge;
			revalidate();
			repaint();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		holdAltKey = false;
	}
	
	private Shape createEdge() {
		Edge edge = (Edge)shapeFactory.getShape(ShapeFactory.EDGE);
		EdgeLabel edgeLabel = (EdgeLabel) shapeFactory.getShape(ShapeFactory.EDGE_LABEL);
		edgeLabel.setEdge(edge);
		edge.setEdgeLabel(edgeLabel);
		shapes.add(edgeLabel);
		add(edgeLabel);
		return edge;
	}
	private void removeEdge(Edge edge) {
		shapes.remove(edge);
		shapes.remove(edge.getEdgeLabel());
		remove(edge.getEdgeLabel());
	}
	@Override
	public void setShapeColor(Color color) {
		// TODO Auto-generated method stub
		this.currentColor = color;
	}
	@Override
	public void setAutomata(Automata automata) {
		// TODO Auto-generated method stub
		this.automata = automata;
	}
	
	public static Shape findSourceShape(Edge edge) {
		Shape shape = null;
		Edge currentEdge = edge;
		while (shape == null) {
			if (currentEdge.getSource() instanceof JoinPoint) {
				currentEdge = (Edge) ((JoinPoint)currentEdge.getSource()).getSource();
			} else {
				shape = currentEdge.getSource();
			}
		}
		return shape;
	}
	
	public static void showPath(Shape start, Shape end, Vector<Shape> shapes, String label) {
		Vector<Edge> paths = getEdgeDest(start, shapes);
		for (Edge edge : paths) {
			if (edge.getEdgeLabel().getLabel() == label) {
				recursiveFindPath(edge, end);
			}
		}
	}
	
	public static boolean recursiveFindPath(Edge edge, Shape end) {
		if (edge.getDest() instanceof JoinPoint) {
			if (recursiveFindPath((Edge) ((JoinPoint)edge.getDest()).getDest(), end)) {
				edge.setColor(Color.green);
				return true;
			}
		} else if (edge.getDest().equals(end)) {
			edge.setColor(Color.green);
			return true;
		}
		return false;
	}
	
	public static Shape findDestShape(Edge edge) {
		Shape shape = null;
		Edge currentEdge = edge;
		while (shape == null) {
			if (currentEdge.getDest() instanceof JoinPoint) {
				currentEdge = (Edge) ((JoinPoint)currentEdge.getDest()).getDest();
			} else {
				shape = currentEdge.getDest();
			}
		}
		return shape;
	}
	@Override
	public void setAutomataChanges() {
		// TODO Auto-generated method stub
		automata.transformShapeToState(shapes);
		automata.verifyAutomata();
	}
	
	public void recognizeAutomata(String word) {
		automata.recognizeAutomata(word, shapes);
		revalidate();
		repaint();
	}
	
	public void requestFocus() {
		requestFocusInWindow();
	}
	@Override
	public void setShapes(Vector<Shape> shapes) {
		// TODO Auto-generated method stub
		this.shapes = shapes;
		for (Shape shape : shapes) {
			if (shape instanceof EdgeLabel) {
				add((EdgeLabel)shape);
			}
		}
		this.revalidate();
		this.repaint();
	}
	@Override
	public Vector<Shape> getShapes() {
		// TODO Auto-generated method stub
		return this.shapes;
	}
}
