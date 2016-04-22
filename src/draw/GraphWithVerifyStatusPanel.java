package draw;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JToolBar;

import automata.Automata;

public class GraphWithVerifyStatusPanel extends GraphDecorator {
	private JToolBar toolbox;
	private LabelStatusMessage label;
	public GraphWithVerifyStatusPanel(Graph decorateComponent) {
		super(decorateComponent);
		// TODO Auto-generated constructor stub
		toolbox = new JToolBar();
		toolbox.setLayout(new GridLayout(1,1));
		label = LabelStatusMessage.getInstance();
		label.setPreferredSize(new Dimension(30,15));
		toolbox.add(label);
	}

	@Override
	public void addComponent(Component comp) {
		// TODO Auto-generated method stub
		this.decorateComponent.addComponent(comp);
	}

	@Override
	public void setShapeColor(Color color) {
		// TODO Auto-generated method stub
		this.decorateComponent.setShapeColor(color);
	}

	@Override
	public Component getComponent() {
		// TODO Auto-generated method stub
		return toolbox;
	}

	@Override
	public void setAutomata(Automata automata) {
		automata.setStatusPanel(label);
	}

	@Override
	public void setAutomataChanges() {
		// TODO Auto-generated method stub
		
	}

}
