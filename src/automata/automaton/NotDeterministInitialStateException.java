package automata.automaton;

public class NotDeterministInitialStateException extends Exception {
	private static final long serialVersionUID = 1L;

	private State<?> e1, e2;

	public NotDeterministInitialStateException(State<?> e1, State<?> e2) {
		this.e1 = e1;
		this.e2 = e2;
	}

	public String getMessage() {
		return "Two initial states " + e1.getName() + " and " + e2.getName();
	}
}
