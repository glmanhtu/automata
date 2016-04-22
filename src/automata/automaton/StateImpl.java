package automata.automaton;

public class StateImpl<T> implements State<T> {
	private boolean initial, terminal;
	private T name;

	public StateImpl(boolean initial, boolean terminal, T name) {
		this.initial = initial;
		this.terminal = terminal;
		this.name = name;
	}

	@Override
	public boolean initial() {
		// TODO Auto-generated method stub
		return initial;
	}

	@Override
	public boolean terminal() {
		// TODO Auto-generated method stub
		return terminal;
	}

	@Override
	public T getName() {
		// TODO Auto-generated method stub
		return name;
	}

}
