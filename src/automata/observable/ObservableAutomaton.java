package automata.observable;

import java.util.Observable;
import java.util.Observer;

import automata.automaton.DeterministicAutomaton;
import automata.automaton.NoDeterministException;
import automata.automaton.NotDeterministInitialStateException;
import automata.automaton.NotDeterministTransitionException;
import automata.automaton.State;
import automata.automaton.Transition;
import automata.automaton.UnknownInitialStateException;
import automata.automaton.UnknownTerminalStateException;

public class ObservableAutomaton<T> extends DeterministicAutomaton<T> {

	public ObservableAutomaton(Transition<T>[] transitions) throws NotDeterministTransitionException,
			UnknownInitialStateException, NotDeterministInitialStateException, 
			NoDeterministException, UnknownTerminalStateException {
		super(transitions);
		// TODO Auto-generated constructor stub
	}

	private Observable observable = new Observable() {
		@Override
		public void notifyObservers(Object arg) {
			setChanged();
			super.notifyObservers(arg);
		}
	};

	@Override
	protected State<?> changeCurrentState(Transition<T> t) {
		observable.notifyObservers(t);
		return super.changeCurrentState(t);
	}

	public void addObserver(Observer o) {
		observable.addObserver(o);
	}
}
