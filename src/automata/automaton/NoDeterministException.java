package automata.automaton;

public class NoDeterministException extends Exception {
	private static final long serialVersionUID = 1L;

	public NoDeterministException() {
	}

	public String getMessage() {
		return "We doesn't have any transition in this automata, please add transition first";
	}
}
