package automata;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import automata.automaton.State;
import automata.automaton.StateImpl;
import automata.automaton.Transition;
import automata.automaton.TransitionImpl;
import automata.observable.ObservableAutomaton;
import draw.GraphWithEditor;
import draw.LabelStatusMessage;
import shape.Edge;
import shape.EdgeLabel;
import shape.InitState;
import shape.JoinPoint;
import shape.Shape;
import shape.TerminalState;

public class Automata {
	/**
	 * Save list of states
	 */
	private List<State<Shape>> states;
	
	/**
	 * Save list of transition
	 */
	private List<Transition<String>> tranitions;
	
	/**
	 * Object to show label in bottom of editor
	 */
	private LabelStatusMessage statusPanel;
	
	/**
	 * List of shapes, which remove JoinPoints
	 */
	private List<Shape> stateShape;
	private ObservableAutomaton<String> observable;
	
	public void setStatusPanel(LabelStatusMessage statusPanel) {
		this.statusPanel = statusPanel;
	}

	public Automata() {
		this.states = new ArrayList<>();
		this.tranitions = new ArrayList<>();
		stateShape = new ArrayList<>();
	}
	
	public void addState(Shape shape)
	{
		State<Shape> state = null;
		if (shape instanceof InitState) {
			state = new StateImpl<Shape>(true, false, shape);
		} else if (shape instanceof TerminalState) {
			state = new StateImpl<Shape>(false, true, shape);
		} else {
			state = new StateImpl<Shape>(false, false, shape);
		}
		states.add(state);
		stateShape.add(shape);
	}
	
	public void addTransition(Shape stateFrom, Shape stateTo, String transition) {
		State<Shape> from = null, to = null;
		for (State<Shape> state : states) {
			if (state.getName().equals(stateFrom)) {
				from = state;
				if (to != null) break;
			}
			if (state.getName().equals(stateTo)) {
				to = state;
				if (from != null) break;
			}
		}
		if (from != null && to != null) {
			tranitions.add(new TransitionImpl<String>(from, to, transition));
		}				
	}
	
	public void transformShapeToState(Vector<Shape> shapes)
	{
		this.tranitions.clear();
		this.states.clear();
		this.stateShape.clear();
		for (Shape shape : shapes) {
			if (!(shape instanceof Edge)) {
				if (!(shape instanceof JoinPoint) && !(shape instanceof EdgeLabel)) {
					addState(shape);
				}
			} else {
				((Edge)shape).setColor(Color.black);
			}
		}
		for (Shape shape : stateShape) {
			findTransition(shape, shapes);
		}
	}
	
	/**
	 * find transition from state to other state
	 * @param shape	current shape
	 * @param shapes all shape
	 */
	public void findTransition(Shape shape, Vector<Shape> shapes) {
		/**
		 * Find Shape which current shape point to
		 */
		Vector<Edge> edgeDest = GraphWithEditor.getEdgeDest(shape, shapes);
		for (Edge edge : edgeDest) {
			Shape currentShape = GraphWithEditor.findDestShape(edge);
			addTransition(shape, currentShape, edge.getEdgeLabel().getLabel());
		}
	}
	
	public boolean verifyAutomata() {
		try {
			observable = new ObservableAutomaton<String>(convertTranitionsListToArray(tranitions));
			statusPanel.setStatus(true);
			return true;
		} catch (Exception e) {
			statusPanel.setError(e.getMessage());
			statusPanel.setStatus(false);
		}
		return false;
	}
	
	public static Transition<String>[] convertTranitionsListToArray(List<Transition<String>> list){
		@SuppressWarnings("unchecked")
		Transition<String>[] array = new Transition[list.size()];

        for(int i = 0; i<list.size(); i++){
            array[i] = list.get(i);
        }

        return array;
    }
	
	public void recognizeAutomata(String word, Vector<Shape> shapes) {
		transformShapeToState(shapes);
		if (word.length() < 1) {
			verifyAutomata();
			return;
		}
		if (this.verifyAutomata()) {
			observable.addObserver(new Observer() {
				public void update(Observable arg0, Object arg1) {
					//((Transition<String>) arg1).label().setColor(Color.green);
					@SuppressWarnings("unchecked")
					Transition<String> trans = ((Transition<String>) arg1);
					Shape from = (Shape) trans.source().getName();
					Shape to = (Shape) trans.target().getName();
					GraphWithEditor.showPath(from, to, shapes, trans.label());
				}
			});
			if (observable.recognize(arrayString(word))) {
				statusPanel.setRecognize(true, word);
			} else {
				statusPanel.setRecognize(false, word);
			}
		}
	}
	
	public String[] arrayString(String word) {
		char[] character = word.toCharArray();
		String[] string = new String[character.length];
		for (int i=0; i< character.length; i++) {
			string[i] = String.valueOf(character[i]);
		}
		return string;
	}
}
