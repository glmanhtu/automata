package automata.automaton;

public class NotDeterministTransitionException extends Exception {
	private static final long serialVersionUID = 1L;

	private Transition<?> t1, t2;

	public NotDeterministTransitionException(Transition<?> t1, Transition<?> t2) {
		this.t1 = t1;
		this.t2 = t2;
	}

	public String getMessage() {
		return "Duplicated transition " + t1.label() 
			+ "(State "+ t1.source().getName() +" -> "
			+ t1.target().getName()+ ") and " + t2.label()+ "(State "+t2.source().getName()+ " -> "
			+ t2.target().getName()+ ")";
	}
}
