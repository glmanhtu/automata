package draw;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.FocusManager;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import automata.Automata;
import shape.Shape;
import shape.ShapeFactory;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class GraphWithColorPanel extends GraphDecorator {
	private JToolBar toolbox;
	private List<Color> colors;
	private Color currentColor;
	private ShapeFactory factory;
	public GraphWithColorPanel(Graph decorateComponent) {
		super(decorateComponent);
		setColors();
		factory = new ShapeFactory();
		currentColor = colors.get(1);
		setShapeColor(currentColor);
		toolbox = new JToolBar();
		FlowLayout ly = new FlowLayout();
		ly.setAlignment(FlowLayout.LEFT);
		ly.setVgap(0);
		ly.setHgap(0);
		toolbox.setLayout(ly);
		for (int i=0; i<colors.size(); i++) {
			Color color = colors.get(i);
			ButtonWithCheck button = new ButtonWithCheck(color);
			button.setPreferredSize(new Dimension(50, 30));
			button.setBackground(color);
			button.setOpaque(true);
			button.setBorderPainted(false);
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setShapeColor(color);
					currentColor = color;
					toolbox.validate();
					toolbox.repaint();
					/**
					 * We lost focus on editor when select this panel
					 * So, we need to request focus in editor
					 * For prevent lost key request like hold ALT key
					 */
					requestFocus();
				}
			});
			toolbox.add(button);
		}
		addSpace(35 - colors.size());
		JTextField test = new JTextField() {
			
			/**
			 * Add PlaceHolder to JTextField
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(java.awt.Graphics g) {
			    super.paintComponent(g);

			    if(getText().isEmpty() && ! (FocusManager.getCurrentKeyboardFocusManager().getFocusOwner() == this)){
			        Graphics2D g2 = (Graphics2D)g.create();
			        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			        g2.setBackground(Color.gray);
			        g2.drawString("Enter word here", 10, 20);
			        g2.dispose();
			    }
			  }
		};
		test.setPreferredSize(new Dimension(200, 30));
		test.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				JTextField textField = (JTextField) e.getSource();
				String text = textField.getText();
				recognizeAutomata(text);
	        }

	        public void keyTyped(KeyEvent e) {
	        }

	        public void keyPressed(KeyEvent e) {
	        }
		});
		toolbox.add(test);
	}
	
	private void addSpace(int space)
	{
		JButton button = new JButton();
		button.setOpaque(true);
		button.setBorderPainted(false);
		button.setPreferredSize(new Dimension(space * 11, 30));
		toolbox.add(button);
	}
	
	private void setColors()
	{
		colors = new ArrayList<Color>();
		colors.add(Color.BLACK);
		colors.add(Color.BLUE);
		colors.add(Color.CYAN);
		colors.add(Color.DARK_GRAY);
		colors.add(Color.GRAY);
		colors.add(Color.GREEN);
		colors.add(Color.LIGHT_GRAY);
		colors.add(Color.MAGENTA);
		colors.add(Color.ORANGE);
		colors.add(Color.PINK);
		colors.add(Color.RED);
		colors.add(Color.YELLOW);
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
	
	private class ButtonWithCheck extends JButton {
		
		/**
		 * Add ability to check 
		 */
		private static final long serialVersionUID = 1L;
		private Color color;
		private Point2D point;
		public ButtonWithCheck(Color color) {
			super(" ");
			this.color = color;
			point = getLocation();
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			// TODO Auto-generated method stub
			super.paintComponent(g);
			Graphics2D ga = (Graphics2D) g; 
			if (currentColor == color) {
				Shape check = factory.getShape(ShapeFactory.CHECK);
				check.setPosition(new Point2D.Double(point.getX() + getPreferredSize().width/2 - 7, point.getY()+ 25));
				ga.setColor(currentColor);
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
