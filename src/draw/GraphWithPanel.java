package draw;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;

import javax.swing.JButton;
import javax.swing.JToolBar;

import automata.Automata;
import shape.Shape;
import shape.ShapeFactory;

import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class GraphWithPanel extends GraphDecorator {
	private JToolBar toolbox;
	private List<JButton> buttons;
	private ShapeFactory factory;
	private int width = 75;
	private int shapeTypeSelected = ShapeFactory.INIT_STATE;
	public GraphWithPanel(Graph decorateComponent) {
		super(decorateComponent);
		buttons = new ArrayList<>();
		toolbox = new JToolBar();
		factory = new ShapeFactory();
		addShapeType("Init State", ShapeFactory.INIT_STATE);
		addShapeType("Terminal State", ShapeFactory.TERMINAL_STATE);
		addShapeType("Circle", ShapeFactory.CIRCLE);
		addShapeType("Big Circle", ShapeFactory.BIG_CIRCLE);
		addShapeType("Square", ShapeFactory.SQUARE);
		addShapeType("Small Square", ShapeFactory.SMALL_SQUARE);
		decorateComponent.setPaintType(shapeTypeSelected);
		toolbox.setLayout(new GridLayout(buttons.size(),1));
		for (JButton button : buttons) {
			toolbox.add(button);
		}
	    toolbox.setPreferredSize(new Dimension(width, toolbox.getPreferredSize().height));
	}
	
	private void addShapeType(String name, int type) {
		ButtonWithIcon button = new ButtonWithIcon(centerBreakLine(name), type);
	    button.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		decorateComponent.setPaintType(type);
	    		shapeTypeSelected = type;
	    		toolbox.revalidate();
	    		toolbox.repaint();
	    		requestFocus();
	    	}
	    });
	    buttons.add(button);
	}
	
	@Override
	public Component getComponent() {
		// TODO Auto-generated method stub
		return toolbox;
	}
	@Override
	public void addComponent(Component comp) {
		this.decorateComponent.addComponent(comp);
	}
	@Override
	public void setShapeColor(Color color) {
		// TODO Auto-generated method stub
		this.decorateComponent.setShapeColor(color);
	}
	private String centerBreakLine(String string) {
		String result = string.replace(" ", "<br />");
		return "<html><br /><br /><center>" + result + "</center></html>";
	}
	
	private class ButtonWithIcon extends JButton
	{
		private Shape shape;
		private int type;
		private Point2D point;
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public ButtonWithIcon(String name, int type) {
			super(name);
			this.type = type;
			shape = factory.getShape(type);
			point = getLocation();
			shape.setPosition(new Point2D.Double(point.getX() + width/2 - 7, point.getY()+ 30));
			shape.setAsIcon();
		}

		@Override
		protected void paintComponent(Graphics g) {
			// TODO Auto-generated method stub
			super.paintComponent(g);
			Graphics2D ga = (Graphics2D) g;
			ga.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			shape.draw(ga);
			if (shapeTypeSelected == type) {
				Shape check = factory.getShape(ShapeFactory.CHECK);
				check.setPosition(new Point2D.Double(point.getX() + width/2 - 7, point.getY()+ 30));
				check.draw(ga);
			}
		}
		
		
	}

	@Override
	public void setAutomata(Automata automata) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setAutomataChanges() {
		// TODO Auto-generated method stub
		
	}
}
