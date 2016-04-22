package automata.automaton;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

public class DeterministicAutomaton<T> {

	private State<?> initialState = null;
	private boolean haveTerminal = false;

	/*
	 * In the map transitions, at each state s we associate a map m where the
	 * values are the transitions having s as source and the corresponding key
	 * the labels of the transitions.
	 * 
	 */
	private final Map<State<?>, Map<T, Transition<T>>> transitions;

	public DeterministicAutomaton(@SuppressWarnings("unchecked") Transition<T>... transitions) throws NotDeterministTransitionException,
			UnknownInitialStateException, NotDeterministInitialStateException, NoDeterministException, UnknownTerminalStateException {
		this.transitions = new HashMap<State<?>, Map<T, Transition<T>>>();
		if (transitions.length < 1) {
			throw new NoDeterministException();
		}
		for (Transition<T> t : transitions) {
			addState(t.source());
			addState(t.target());

			Map<T, Transition<T>> map = this.transitions.get(t.source());
			if (map.containsKey(t.label())) {
				throw new NotDeterministTransitionException(t, map.get(t.label()));
			} else {
				map.put(t.label(), t);
			}
		}
		if (!haveTerminal) {
			throw new UnknownTerminalStateException();
		}
		if (initialState == null) {
			throw new UnknownInitialStateException();
		}
	}

	protected final void addState(State<?> s) throws NotDeterministInitialStateException {
		if (!transitions.containsKey(s)) {
			transitions.put(s, new HashMap<T, Transition<T>>());
			if (s.initial()) {
				if (initialState == null) {
					initialState = s;
				} else {
					throw new NotDeterministInitialStateException(s, initialState);
				}
			}
			if (s.terminal()) {
				haveTerminal = true;
			}
		}
	}

	public State<?> initialState() {
		return initialState;
	}

	public Transition<T> transition(State<?> s, T label) {
		if (!transitions.containsKey(s)) {
			throw new NoSuchElementException();
		}
		return transitions.get(s).get(label);
	}

	public boolean recognize(@SuppressWarnings("unchecked") T... word) {
		return recognize(Arrays.asList(word).iterator());
	}

	public boolean recognize(Iterator<T> word) {
		State<?> s = initialState;
		while (word.hasNext()) {
			Transition<T> t = transition(s, word.next());
			if (t == null) {
				return false;
			} else {
				s = changeCurrentState(t);
			}
		}
		return s.terminal();
	}

	protected State<?> changeCurrentState(Transition<T> t) {
		return t.target();
	}
}